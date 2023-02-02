package lk.ijse.spiceExportCompany.view.tm;

public class SalaryTM {
     private String Eid;
     private String date;
     private String payment;

    public SalaryTM() {
    }

    public SalaryTM(String eid, String date, String payment) {
        Eid = eid;
        this.date = date;
        this.payment = payment;
    }

    public String getEid() {
        return Eid;
    }

    public void setEid(String eid) {
        Eid = eid;
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

    @Override
    public String toString() {
        return "SalaryTM{" +
                "Eid='" + Eid + '\'' +
                ", date='" + date + '\'' +
                ", payment='" + payment + '\'' +
                '}';
    }
}
