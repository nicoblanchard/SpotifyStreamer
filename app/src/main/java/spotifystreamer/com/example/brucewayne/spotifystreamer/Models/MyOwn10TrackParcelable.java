package spotifystreamer.com.example.brucewayne.spotifystreamer.Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Bruce Wayne on 12/07/2015.
 */
public class MyOwn10TrackParcelable implements Parcelable{
    public String albumName;
    public String albumImage;
    public String trackName;

    public MyOwn10TrackParcelable(String albumName, String albumImage, String trackName){
        this.albumName=albumName;
        this.albumImage=albumImage;
        this.trackName=trackName;
    }

    private MyOwn10TrackParcelable(Parcel in){
        albumName=in.readString();
        albumImage=in.readString();
        trackName=in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(albumName);
        dest.writeString(albumImage);
         dest.writeString(trackName);

    }

    public final Parcelable.Creator <MyOwn10TrackParcelable> CREATOR= new Parcelable.Creator<MyOwn10TrackParcelable>(){
        @Override
        public MyOwn10TrackParcelable createFromParcel(Parcel parcel) {
            return new MyOwn10TrackParcelable(parcel);
        }

        @Override
        public MyOwn10TrackParcelable[] newArray(int size) {
            return new MyOwn10TrackParcelable[size];
        }
    };























}
