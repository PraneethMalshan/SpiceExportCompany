package lk.ijse.spiceExportCompany.view.tm;

public class TransportTM {
    private String Transportid;
    private String Date;
    private String Time;
    private String Cost;

    public TransportTM() {
    }

    public TransportTM(String transportid, String date, String time, String cost) {
        Transportid = transportid;
        Date = date;
        Time = time;
        Cost = cost;
    }

    public String getTransportid() {
        return Transportid;
    }

    public void setTransportid(String transportid) {
        Transportid = transportid;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getCost() {
        return Cost;
    }

    public void setCost(String cost) {
        Cost = cost;
    }

    @Override
    public String toString() {
        return "TransportTM{" +
                "Transportid='" + Transportid + '\'' +
                ", Date='" + Date + '\'' +
                ", Time='" + Time + '\'' +
                ", Cost='" + Cost + '\'' +
                '}';
    }
}
