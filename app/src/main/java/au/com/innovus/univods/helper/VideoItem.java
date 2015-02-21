package au.com.innovus.univods.helper;

/**
 * Created by jorge on 21/02/15.
 */
public class VideoItem {

    private String title = "";
    private String pubDate = "";
    private String URL = "";

    public VideoItem(String title, String pubDate, String URL) {
        this.title = title;
        this.pubDate = pubDate;
        this.URL = URL;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }
}
