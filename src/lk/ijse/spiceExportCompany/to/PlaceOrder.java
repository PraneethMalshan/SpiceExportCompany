package lk.ijse.spiceExportCompany.to;

import java.util.ArrayList;

public class PlaceOrder {
    private String cusId;
    private String cusOrderId;
    private ArrayList<CartDetail> orderdetails = new ArrayList<>();

    public PlaceOrder() {
    }

    public PlaceOrder(String cusId, String cusOrderId, ArrayList<CartDetail> orderdetails) {
        this.cusId = cusId;
        this.cusOrderId = cusOrderId;
        this.orderdetails = orderdetails;
    }

    public String getCusId() {
        return cusId;
    }

    public void setCusId(String cusId) {
        this.cusId = cusId;
    }

    public String getCusOrderId() {
        return cusOrderId;
    }

    public void setCusOrderId(String cusOrderId) {
        this.cusOrderId = cusOrderId;
    }

    public ArrayList<CartDetail> getOrderdetails() {
        return orderdetails;
    }

    public void setOrderdetails(ArrayList<CartDetail> orderdetails) {
        this.orderdetails = orderdetails;
    }

    @Override
    public String toString() {
        return "PlaceOrder{" +
                "cusId='" + cusId + '\'' +
                ", cusOrderId='" + cusOrderId + '\'' +
                ", orderdetails=" + orderdetails +
                '}';
    }
}
