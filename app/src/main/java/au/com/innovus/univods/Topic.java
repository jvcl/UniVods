package au.com.innovus.univods;

/**
 * Created by jorge on 19/02/15.
 */
public class Topic {

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
}
