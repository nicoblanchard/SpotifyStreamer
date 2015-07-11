package spotifystreamer.com.example.brucewayne.spotifystreamer;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Bruce Wayne on 06/07/2015.
 */
public class MyOwnArtistParcelable implements Parcelable{
    String name;
    String imageURL;

    public MyOwnArtistParcelable(String name, String imageURL){
        this.name=name;
        this.imageURL=imageURL;
    }

    private MyOwnArtistParcelable(Parcel in){
        name=in.readString();
        imageURL=in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(imageURL);
    }

    public final Parcelable.Creator <MyOwnArtistParcelable> CREATOR= new Parcelable.Creator<MyOwnArtistParcelable>(){
        @Override
        public MyOwnArtistParcelable createFromParcel(Parcel parcel) {
            return new MyOwnArtistParcelable(parcel);
        }

        @Override
        public MyOwnArtistParcelable[] newArray(int size) {
            return new MyOwnArtistParcelable[size];
        }
    };

}
