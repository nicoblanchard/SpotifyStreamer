package spotifystreamer.com.example.brucewayne.spotifystreamer;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import kaaes.spotify.webapi.android.SpotifyApi;
import kaaes.spotify.webapi.android.SpotifyService;
import kaaes.spotify.webapi.android.models.Artist;
import kaaes.spotify.webapi.android.models.ArtistsPager;


/**
 * A placeholder fragment containing a simple view.
 */
public class ArtistQueryActivityFragment extends Fragment {
    ArrayAdapter<Artist> adapter;
    List<Artist> arrayList = new ArrayList <Artist>();
    public ArtistQueryActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //adapter

        View rootView = inflater.inflate(R.layout.fragment_artist_query, container, false);



        // Bouton de recherche
        SearchView artistQuery= (SearchView) rootView.findViewById(R.id.artist_query_searchView);
        artistQuery.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (!query.isEmpty()){
                    SpotifyApi api=new SpotifyApi();
                    SpotifyService spotify = api.getService();
                    if (spotify!=null) {
                             ArtistsPager results = spotify.searchArtists(query);

  /*                          Toast.makeText(getActivity(),"Can't find this artist",Toast.LENGTH_SHORT);
                        }else{
                            for (Artist artistFound : results.artists.items) {
                                arrayList.add(artistFound);
                            }
                        }*/
                    }

               }
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });

        adapter = new ArrayAdapter<Artist>(getActivity(),
                R.layout.artist_results_listview,
                R.id.artist_listView,
                arrayList);
        // Creation de la listeView
        ListView resultsListView = (ListView) rootView.findViewById(R.id.artist_listView);
        resultsListView.setAdapter(adapter);
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
