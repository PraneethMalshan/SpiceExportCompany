package lk.ijse.spiceExportCompany.view.tm;

public class FarmerPaymentsTM {
     private String Fid;
     private String toFarmerPaymentId;
     private String date;
     private String balance;

    public FarmerPaymentsTM() {
    }

    public FarmerPaymentsTM(String fid, String toFarmerPaymentId, String date, String balance) {
        this.Fid = fid;
        this.toFarmerPaymentId = toFarmerPaymentId;
        this.date = date;
        this.balance = balance;
    }

    public String getFid() {
        return Fid;
    }

    public void setFid(String fid) {
        this.Fid = fid;
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
        return "FarmerPaymentsTM{" +
                "Fid='" + Fid + '\'' +
                ", toFarmerPaymentId='" + toFarmerPaymentId + '\'' +
                ", date='" + date + '\'' +
                ", balance='" + balance + '\'' +
                '}';
    }
}
