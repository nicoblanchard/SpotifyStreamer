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

import spotifystreamer.com.example.brucewayne.spotifystreamer.Models.MyOwn10TrackParcelable;
import spotifystreamer.com.example.brucewayne.spotifystreamer.R;

/**
 * Created by Bruce Wayne on 12/07/2015.
 */
public class TopTracksAdapter extends ArrayAdapter<MyOwn10TrackParcelable> {
    Context context;
    List <MyOwn10TrackParcelable> data;

    public TopTracksAdapter(Context context, List<MyOwn10TrackParcelable> data){
        super(context,-1, data);
        this.context=context;
        this.data=data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewSaver saver = new ViewSaver();
        if (convertView == null) {
            LayoutInflater myInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if (myInflater != null) {
                convertView = myInflater.inflate(R.layout.top10_listview, parent, false);
                if (convertView != null) {
                    saver.albumName = (TextView) convertView.findViewById(R.id.album_name);
                    saver.albumImage = (ImageView) convertView.findViewById(R.id.album_image);
                    saver.trackName = (TextView) convertView.findViewById(R.id.track_name);
                    convertView.setTag(saver);
                }else {
                    saver=(ViewSaver) convertView.getTag();
                }
                if (saver!=null) {
                    MyOwn10TrackParcelable myTop10Tracks = data.get(position);
                    saver.albumName.setText(myTop10Tracks.albumName);
                    saver.trackName.setText(myTop10Tracks.trackName);
                    if (myTop10Tracks.albumName.isEmpty()) {
                        saver.albumImage.setImageResource(R.drawable.empty_image);
                    } else {
                        Picasso.with(context).load(myTop10Tracks.albumImage).into(saver.albumImage);
                    }
                }
            }
        }
        return convertView;
    }

    private class ViewSaver {
        TextView trackName;
        TextView albumName;
        ImageView albumImage;
    }
}

