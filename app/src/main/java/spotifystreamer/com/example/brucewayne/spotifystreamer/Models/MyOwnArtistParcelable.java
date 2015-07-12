package spotifystreamer.com.example.brucewayne.spotifystreamer.Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Bruce Wayne on 06/07/2015.
 */
public class MyOwnArtistParcelable implements Parcelable{
    public String name;
    public String imageURL;
    public String id;

    public MyOwnArtistParcelable(String name, String imageURL, String id){
        this.name=name;
        this.imageURL=imageURL;
        this.id=id;
    }

    private MyOwnArtistParcelable(Parcel in){
        name=in.readString();
        imageURL=in.readString();
        id=in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(imageURL);
        dest.writeString(id);
    }

    public static final Parcelable.Creator <MyOwnArtistParcelable> CREATOR= new Parcelable.Creator<MyOwnArtistParcelable>(){
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
