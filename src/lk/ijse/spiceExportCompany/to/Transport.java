package lk.ijse.spiceExportCompany.to;

public class Transport {
    private String transportId;
    private String date;
    private String time;
    private String cost;

    public Transport() {
    }

    public Transport(String transportId, String date, String time, String cost) {
        this.transportId = transportId;
        this.date = date;
        this.time = time;
        this.cost = cost;
    }

    public String getTransportId() {
        return transportId;
    }

    public void setTransportId(String transportId) {
        this.transportId = transportId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Transport{" +
                "transportId='" + transportId + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", cost='" + cost + '\'' +
                '}';
    }
}
