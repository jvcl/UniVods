package au.com.innovus.univods;

/**
 * Created by jorge on 19/02/15.
 */
public class Topic {

    private String URL;
    private String major;
    private String name;

    public Topic(String URL, String major, String name) {
        this.URL = URL;
        this.major = major;
        this.name = name;
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
}
