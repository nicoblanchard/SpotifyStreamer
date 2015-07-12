package spotifystreamer.com.example.brucewayne.spotifystreamer.Fragments.Adaptors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import spotifystreamer.com.example.brucewayne.spotifystreamer.Models.MyOwnArtistParcelable;
import spotifystreamer.com.example.brucewayne.spotifystreamer.R;

/**
 * Created by Bruce Wayne on 08/07/2015.
 */
public class ArtistAdapter extends ArrayAdapter<MyOwnArtistParcelable> {
    Context context;
    List <MyOwnArtistParcelable> data;

    public ArtistAdapter(Context context, List <MyOwnArtistParcelable> data) {
        super(context, -1,data);
        this.context=context;
        this.data=data;
    }

    @Override

    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater myInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if (myInflater != null) {
                convertView = myInflater.inflate(R.layout.artist_results_listview, parent, false);
                if (convertView != null) {
                    TextView artistName = (TextView) convertView.findViewById(R.id.artist_name); // artist name
                    ImageView thumb_image=(ImageView)convertView.findViewById(R.id.artist_imageView); // thumb image

                    MyOwnArtistParcelable myArtist= data.get(position);

                    artistName.setText(myArtist.name);
                    if (myArtist.imageURL.isEmpty()) {
                        thumb_image.setImageResource(R.drawable.empty_image);
                    }else{
                        Picasso.with(context).load(myArtist.imageURL).into(thumb_image);
                    }
                }
            }
        }
        return convertView;
    }
}

