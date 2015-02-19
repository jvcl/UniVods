package au.com.innovus.univods;

/**
 * Created by jorge on 19/02/15.
 */
public class Topic {

    private String URL;
    private String major;
    private String code;
    private String name;

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
}
