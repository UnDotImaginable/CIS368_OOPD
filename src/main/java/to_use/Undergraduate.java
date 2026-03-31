package to_use;

public class Undergraduate extends StudentRecord {
    private String major;
    private String minor;

    public Undergraduate(String idInput, PersonInfo personInfo, String major, String minor) {
        super(idInput, personInfo);
        this.major = major;
        this.minor = minor;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getMinor() {
        return minor;
    }

    public void setMinor(String minor) {
        this.minor = minor;
    }
}