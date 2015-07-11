package spotifystreamer.com.example.brucewayne.spotifystreamer;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
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
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


/**
 * A placeholder fragment containing a simple view.
 */
public class ArtistQueryActivityFragment extends Fragment {
//    List<Artist> arrayList = new ArrayList <Artist>();
    ArtistAdapter adapter;
    List<Artist> arrayList = new ArrayList <Artist>();

    public ArtistQueryActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //adapter

        View rootView = inflater.inflate(R.layout.fragment_artist_query, container, false);
        ListView resultsListView = (ListView) rootView.findViewById(R.id.artist_listView);

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
                                    Toast.makeText(getActivity(), "Selected artist can't be found", Toast.LENGTH_SHORT);
                                } else {
                                    for (Artist artistFound : artistsPager.artists.items) {
                                        arrayList.add(artistFound);
                                    }
                                }
                            }

                            @Override
                            public void failure(RetrofitError error) {
                                Toast.makeText(getActivity(), "Connection Problem", Toast.LENGTH_SHORT);

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
        adapter = new ArtistAdapter(getActivity().getBaseContext(), arrayList);
        // Creation de la listeView
        resultsListView.setAdapter(adapter);
     //   System.out.println(arrayList.get(0).name);

        resultsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), Top10TracksActivity.class);
                startActivity(intent);
            }
        });
        return rootView;
    }
}
