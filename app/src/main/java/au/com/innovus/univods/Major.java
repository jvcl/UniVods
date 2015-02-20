package au.com.innovus.univods;

/**
 * Created by jorge on 20/02/15.
 */
public class Major {

    private String code;
    private String name;
    private int id;

    public Major(){

    }

    public Major(String code, String name, int id) {
        this.code = code;
        this.name = name;
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return code + " " + name;
    }
}
