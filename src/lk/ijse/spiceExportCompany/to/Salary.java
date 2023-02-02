package lk.ijse.spiceExportCompany.to;

public class Salary {
    private String eId;
    private String date;
    private String payment;

    public Salary() {

    }

    public Salary( String eId,String date, String payment ) {
        this.eId = eId;
        this.date = date;
        this.payment = payment;

    }





    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String geteId() {
        return eId;
    }

    public void seteId(String eId) {
        this.eId = eId;
    }

    @Override
    public String toString() {
        return "Salary{" +
                ", date='" + date + '\'' +
                ", payment='" + payment + '\'' +
                ", eId='" + eId + '\'' +
                '}';
    }
}
