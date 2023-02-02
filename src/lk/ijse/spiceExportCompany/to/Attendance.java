package lk.ijse.spiceExportCompany.to;

public class Attendance {

    private String eId;
    private String date;
    private String inTime;
    private String outTime;

    public Attendance() {
    }

    public Attendance(String eId, String date, String inTime, String outTime) {
        this.eId = eId;
        this.date = date;
        this.inTime = inTime;
        this.outTime = outTime;
    }

    public String geteId() {
        return eId;
    }

    public void seteId(String eId) {
        this.eId = eId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getInTime() {
        return inTime;
    }

    public void setInTime(String inTime) {
        this.inTime = inTime;
    }

    public String getOutTime() {
        return outTime;
    }

    public void setOutTime(String outTime) {
        this.outTime = outTime;
    }

    @Override
    public String toString() {
        return "Attendance{" +
                "eId='" + eId + '\'' +
                ", date='" + date + '\'' +
                ", inTime='" + inTime + '\'' +
                ", outTime='" + outTime + '\'' +
                '}';
    }
}
