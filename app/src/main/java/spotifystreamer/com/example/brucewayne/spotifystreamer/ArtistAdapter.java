package spotifystreamer.com.example.brucewayne.spotifystreamer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import kaaes.spotify.webapi.android.models.Artist;

/**
 * Created by Bruce Wayne on 08/07/2015.
 */
public class ArtistAdapter extends ArrayAdapter<Artist> {
    Context context;
    List <Artist> data;

    public ArtistAdapter(Context context, List <Artist> data) {
        super(context, R.layout.artist_results_listview,data);
        this.context=context;
        this.data=data;
    }

    @Override

    public View getView(int position, View convertView, ViewGroup parent) {

                TextView artistName = (TextView) convertView.findViewById(R.id.artist_name); // artist name
          //  ImageView thumb_image=(ImageView)convertView.findViewById(R.id.artist_imageView); // thumb image
                artistName.setText(data.get(position).name);

          return convertView;
    };

}

