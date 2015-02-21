package au.com.innovus.univods;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jorge on 19/02/15.
 */
public class Topic implements Parcelable {

    private String major;
    private String code;
    private String name;
    private int id;
    private String URL;
    private boolean isSelected;

    public Topic() {
    }

    public String getURL() {
        return URL;
    }

    public String getMajor() {
        return major;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return code +" "+name +" " +isSelected;
    }
    public String getCode(){
        return code;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public void setCode(String code) {
        this.code = code;
        URL = "http://video.flinders.edu.au/lectureResources/vod/"+code+"_2014.xml";
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    protected Topic(Parcel in) {
        major = in.readString();
        code = in.readString();
        name = in.readString();
        id = in.readInt();
        URL = in.readString();
        isSelected = in.readByte() != 0x00;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(major);
        dest.writeString(code);
        dest.writeString(name);
        dest.writeInt(id);
        dest.writeString(URL);
        dest.writeByte((byte) (isSelected ? 0x01 : 0x00));
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Topic> CREATOR = new Parcelable.Creator<Topic>() {
        @Override
        public Topic createFromParcel(Parcel in) {
            return new Topic(in);
        }

        @Override
        public Topic[] newArray(int size) {
            return new Topic[size];
        }
    };
}
