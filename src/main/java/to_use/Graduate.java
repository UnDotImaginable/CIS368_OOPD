package to_use;

public class Graduate extends StudentRecord {
    private String undergradInst;
    private double stipend;

    public Graduate(String idInput, PersonInfo personInfo, String undergradInst, double stipend) {
        super(idInput, personInfo);
        this.undergradInst = undergradInst;
        this.stipend = stipend;
    }

    public String getUndergradInst() {
        return undergradInst;
    }

    public void setUndergradInst(String undergradInst) {
        this.undergradInst = undergradInst;
    }

    public double getStipend() {
        return stipend;
    }

    public void setStipend(double stipend) {
        this.stipend = stipend;
    }
}