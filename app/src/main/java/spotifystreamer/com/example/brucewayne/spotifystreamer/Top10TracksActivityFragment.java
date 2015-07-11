package spotifystreamer.com.example.brucewayne.spotifystreamer;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

/**
 * Created by Bruce Wayne on 05/07/2015.
 */
public class Top10TracksActivityFragment {


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //adapter

        View rootView = inflater.inflate(R.layout.fragment_artist_query, container, false);
        // Creation de la listeView
        ListView resultsTop10Tracks = (ListView) rootView.findViewById(R.id.top10_listView);
        //resultsListView.setAdapter(adapter);
        resultsTop10Tracks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        return rootView;
    }
}