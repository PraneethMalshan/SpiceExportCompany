package lk.ijse.spiceExportCompany.to;

import java.time.LocalDate;
import java.time.LocalTime;

public class Order {
    private String cusOrderId;
    private LocalTime time;
    private LocalDate date;
    private String cusId;

    public Order() {
    }

    public Order(String cusOrderId, LocalTime time, LocalDate date, String cusId) {
        this.cusOrderId = cusOrderId;
        this.time = time;
        this.date = date;
        this.cusId = cusId;
    }

    public String getCusOrderId() {
        return cusOrderId;
    }

    public void setCusOrderId(String cusOrderId) {
        this.cusOrderId = cusOrderId;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCusId() {
        return cusId;
    }

    public void setCusId(String cusId) {
        this.cusId = cusId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "cusOrderId='" + cusOrderId + '\'' +
                ", time=" + time +
                ", date=" + date +
                ", cusId='" + cusId + '\'' +
                '}';
    }
}