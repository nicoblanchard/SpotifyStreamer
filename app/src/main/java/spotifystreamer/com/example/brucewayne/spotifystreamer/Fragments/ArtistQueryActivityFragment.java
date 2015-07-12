package spotifystreamer.com.example.brucewayne.spotifystreamer.Fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import kaaes.spotify.webapi.android.SpotifyApi;
import kaaes.spotify.webapi.android.SpotifyService;
import kaaes.spotify.webapi.android.models.Artist;
import kaaes.spotify.webapi.android.models.ArtistsPager;
import kaaes.spotify.webapi.android.models.Image;
import kaaes.spotify.webapi.android.models.TracksPager;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import spotifystreamer.com.example.brucewayne.spotifystreamer.Activities.Top10TracksActivity;
import spotifystreamer.com.example.brucewayne.spotifystreamer.Fragments.Adaptors.ArtistAdapter;
import spotifystreamer.com.example.brucewayne.spotifystreamer.Models.MyOwn10TrackParcelable;
import spotifystreamer.com.example.brucewayne.spotifystreamer.Models.MyOwnArtistParcelable;
import spotifystreamer.com.example.brucewayne.spotifystreamer.R;


/**
 * A placeholder fragment containing a simple view.
 */
public class ArtistQueryActivityFragment extends Fragment {
//    List<Artist> arrayList = new ArrayList <Artist>();
    ArtistAdapter adapter;
    ArrayList <MyOwnArtistParcelable> arrayList;
    private ArtistAdapter artistAdapter;
  //  private ArrayList<MyOwnArtistParcelable> tracksParcelable;

    public ArtistQueryActivityFragment() {
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter=new ArtistAdapter(getActivity().getBaseContext(), new ArrayList <MyOwnArtistParcelable>());
        arrayList = new ArrayList <MyOwnArtistParcelable>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //adapter
        View rootView = inflater.inflate(R.layout.fragment_artist_query, container, false);
        ListView resultsListView = (ListView) rootView.findViewById(R.id.artist_listView);
        if (resultsListView != null) {
            resultsListView.setAdapter(adapter);
            resultsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    MyOwnArtistParcelable artistParcelable = (MyOwnArtistParcelable) parent.getItemAtPosition(position);
                    Intent i = new Intent(getActivity(), Top10TracksActivity.class);
                    i.putExtra("MyOwnArtistParcelable", artistParcelable);
                    startActivity(i);
                }
            });
        }
        // Bouton de recherche
        SearchView artistQuery = (SearchView) rootView.findViewById(R.id.artist_query_searchView);
        artistQuery.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (!query.isEmpty()) {
                    SpotifyApi api = new SpotifyApi();
                    SpotifyService spotify = api.getService();
                    if (spotify != null) {

                        spotify.searchArtists(query, new Callback<ArtistsPager>() {
                            @Override
                            public void success(ArtistsPager artistsPager, Response response) {
                                if (artistsPager.artists.total == 0) {
                                    Toast.makeText(getActivity(), "Selected artist can't be found", Toast.LENGTH_SHORT).show();
                                } else {
                                    arrayList.clear();
                                    adapter.clear();
                                    for (Artist artistFound : artistsPager.artists.items) {
                                        arrayList.add(makeArtistMyOwnArtistParcelable(artistFound));
                                    }
                                    for (MyOwnArtistParcelable artiste : arrayList) {
                                        adapter.add(artiste);
                                    }
                                }
                            }

                            @Override
                            public void failure(RetrofitError error) {
                                Toast.makeText(getActivity(), "Connection Problem", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });
        return rootView;
    }
    public MyOwnArtistParcelable makeArtistMyOwnArtistParcelable(Artist artist){
        MyOwnArtistParcelable myArtist;
        String artistname=artist.name;
        String image_thumb="";
        String id=artist.id;
        for (Image i:artist.images){
            if (i.width<=200){
                image_thumb=i.url;
                break;
            }
        }
        myArtist=new MyOwnArtistParcelable(artistname,image_thumb, id);
        return myArtist;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (outState != null) {
            outState.putParcelableArrayList("MyOwnArtistParcelable", arrayList);
        }
        super.onSaveInstanceState(outState);
    }
}
