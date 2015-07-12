package spotifystreamer.com.example.brucewayne.spotifystreamer.Fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kaaes.spotify.webapi.android.SpotifyApi;
import kaaes.spotify.webapi.android.SpotifyService;
import kaaes.spotify.webapi.android.models.Artist;
import kaaes.spotify.webapi.android.models.Image;
import kaaes.spotify.webapi.android.models.Track;
import kaaes.spotify.webapi.android.models.Tracks;
import kaaes.spotify.webapi.android.models.TracksPager;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import spotifystreamer.com.example.brucewayne.spotifystreamer.Fragments.Adaptors.TopTracksAdapter;
import spotifystreamer.com.example.brucewayne.spotifystreamer.Models.MyOwn10TrackParcelable;
import spotifystreamer.com.example.brucewayne.spotifystreamer.Models.MyOwnArtistParcelable;
import spotifystreamer.com.example.brucewayne.spotifystreamer.R;

/**
 * Created by Bruce Wayne on 05/07/2015.
 */
public class Top10TracksActivityFragment extends Fragment{
    TopTracksAdapter adapter;
    ArrayList<MyOwn10TrackParcelable> arrayList;
    final String COUNTRY_KEY = "country";
    final String COUNTRY_VALUE = "FR";

    final static String MY_OWN_10TRACK_PARCELABLE_STRING="MyOwn10TrackParcelable";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter=new TopTracksAdapter(getActivity().getBaseContext(), new ArrayList<MyOwn10TrackParcelable>());

        // Rotation case
        if (savedInstanceState != null && savedInstanceState.containsKey(Top10TracksActivityFragment.MY_OWN_10TRACK_PARCELABLE_STRING)) {
            arrayList = savedInstanceState.getParcelableArrayList(Top10TracksActivityFragment.MY_OWN_10TRACK_PARCELABLE_STRING);
            for (MyOwn10TrackParcelable track: arrayList){
                adapter.add(track);
            }
        }

        Intent intent = getActivity().getIntent();
        if (intent!=null){

             MyOwnArtistParcelable artistFromIntent = intent.getExtras().getParcelable(ArtistQueryActivityFragment.MY_OWN_ARTIST_PARCELABLE_STRING);

            if (artistFromIntent!=null){
                SpotifyApi api = new SpotifyApi();
                SpotifyService spotify = api.getService();
                if (spotify!=null){
                    Map<String, Object> countryMap = new HashMap<>();
                    countryMap.put(COUNTRY_KEY, COUNTRY_VALUE);
                    spotify.getArtistTopTrack(artistFromIntent.id, countryMap, new Callback<Tracks>() {
                        @Override
                        public void success(Tracks tracks, Response response) {
                            if (arrayList != null){
                                arrayList.clear();
                            }
                            adapter.clear();
                            arrayList = new ArrayList <MyOwn10TrackParcelable>();
                            for (Track trackFound : tracks.tracks) {
                                arrayList.add(makeTracksMyOwn10TrackParcelable(trackFound));
                            }
                            for (MyOwn10TrackParcelable track: arrayList){
                                adapter.add(track);
                            }
                        }
                        @Override
                        public void failure(RetrofitError error) {
                            Toast.makeText(getActivity(), R.string.Connection_issue, Toast.LENGTH_SHORT).show();
                        }
                    }) ;                }
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_top10, container, false);
        ListView resultsTop10Tracks = (ListView) rootView.findViewById(R.id.top10_listView);
        if (resultsTop10Tracks != null) {
            resultsTop10Tracks.setAdapter(adapter);
            resultsTop10Tracks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                }
            });
        }
        return rootView;
    }

    public MyOwn10TrackParcelable makeTracksMyOwn10TrackParcelable(Track track){
        MyOwn10TrackParcelable myTracks;
        String albumName=track.album.name;
        String trackName=track.name;
        String albumImage="";
        for (Image i:track.album.images){
            if (i.width==200){
                albumImage=i.url;
                break;
            }
            if (i.width<200){
                albumImage=i.url;
                continue;
            }
        }
        myTracks=new MyOwn10TrackParcelable(albumName,albumImage, trackName);
        return myTracks;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (outState != null) {
            outState.putParcelableArrayList(MY_OWN_10TRACK_PARCELABLE_STRING, arrayList);
        }
        super.onSaveInstanceState(outState);
    }
}