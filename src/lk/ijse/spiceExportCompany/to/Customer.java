package lk.ijse.spiceExportCompany.to;

public class Customer {
    private String cusId;
    private String cusName;
    private String address;
    private String email;
    private String tell;

    public Customer() {
    }

    public Customer(String cusId, String cusName, String address, String email, String tell) {
        this.cusId = cusId;
        this.cusName = cusName;
        this.address = address;
        this.email = email;
        this.tell = tell;
    }

    public String getCusId() {
        return cusId;
    }

    public void setCusId(String cusId) {
        this.cusId = cusId;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTell() {
        return tell;
    }

    public void setTell(String tell) {
        this.tell = tell;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "cusId='" + cusId + '\'' +
                ", cusName='" + cusName + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", tell='" + tell + '\'' +
                '}';
    }
}
