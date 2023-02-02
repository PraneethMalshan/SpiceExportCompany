package lk.ijse.spiceExportCompany.view.tm;

public class CustomerTM {
    private String CusId;
    private String CusName;
    private String CusAddress;
    private String Email;
    private String Tell;

    public CustomerTM() {
    }

    public CustomerTM(String cusId, String cusName, String cusAddress, String email, String tell) {
        CusId = cusId;
        CusName = cusName;
        CusAddress = cusAddress;
        Email = email;
        Tell = tell;
    }

    public String getCusId() {
        return CusId;
    }

    public void setCusId(String cusId) {
        CusId = cusId;
    }

    public String getCusName() {
        return CusName;
    }

    public void setCusName(String cusName) {
        CusName = cusName;
    }

    public String getCusAddress() {
        return CusAddress;
    }

    public void setCusAddress(String cusAddress) {
        CusAddress = cusAddress;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getTell() {
        return Tell;
    }

    public void setTell(String tell) {
        Tell = tell;
    }
}
