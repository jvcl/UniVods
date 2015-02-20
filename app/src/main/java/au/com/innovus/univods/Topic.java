package au.com.innovus.univods;

/**
 * Created by jorge on 19/02/15.
 */
public class Topic {

    private String URL;
    private String major;
    private String code;
    private String name;
    private int id;

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
        return code +" "+name;
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
}
