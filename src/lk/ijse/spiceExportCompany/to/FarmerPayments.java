package lk.ijse.spiceExportCompany.to;

public class FarmerPayments {
    private String fId;
    private String toFarmerPaymentId;
    private String date;
    private String balance;

    public FarmerPayments() {
    }

    public FarmerPayments(String fId, String toFarmerPaymentId, String date, String balance) {
        this.fId = fId;
        this.toFarmerPaymentId = toFarmerPaymentId;
        this.date = date;
        this.balance = balance;
    }

    public String getfId() {
        return fId;
    }

    public void setfId(String fId) {
        this.fId = fId;
    }

    public String getToFarmerPaymentId() {
        return toFarmerPaymentId;
    }

    public void setToFarmerPaymentId(String toFarmerPaymentId) {
        this.toFarmerPaymentId = toFarmerPaymentId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "FarmerPayments{" +
                "fId='" + fId + '\'' +
                ", toFarmerPaymentId='" + toFarmerPaymentId + '\'' +
                ", date='" + date + '\'' +
                ", balance='" + balance + '\'' +
                '}';
    }
}
