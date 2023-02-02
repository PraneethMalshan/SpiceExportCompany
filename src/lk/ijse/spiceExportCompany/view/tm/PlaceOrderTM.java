package lk.ijse.spiceExportCompany.view.tm;

import javafx.scene.control.Button;

public class PlaceOrderTM {

     private String productCode;
     private String productName;
     private int quantity;
     private double price;
     private double total;
     private Button btnDelete;

    public PlaceOrderTM() {
    }

    public PlaceOrderTM(String productCode, String productName, int quantity, double price, double total, Button btnDelete) {
        this.productCode = productCode;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.total = total;
        this.btnDelete = btnDelete;
    }


    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Button getBtnDelete() {
        return btnDelete;
    }

    public void setBtnDelete(Button btnDelete) {
        this.btnDelete = btnDelete;
    }

    @Override
    public String toString() {
        return "PlaceOrderTM{" +
                "productCode='" + productCode + '\'' +
                ", productName='" + productName + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", total=" + total +
                ", btnDelete=" + btnDelete +
                '}';
    }
}
