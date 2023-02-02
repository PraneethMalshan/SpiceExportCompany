package lk.ijse.spiceExportCompany.view.tm;

public class EmployeeTM {
    private String Eid;
    private String Nic;
    private String name;
    private String dob;
    private String gender;
    private String address;
    private String tel;
    private String email;

    public EmployeeTM() {
    }

    public EmployeeTM(String eid, String nic, String name, String dob, String gender, String address, String tel, String email) {
        Eid = eid;
        Nic = nic;
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.address = address;
        this.tel = tel;
        this.email = email;
    }

    public String getEid() {
        return Eid;
    }

    public void setEid(String eid) {
        Eid = eid;
    }

    public String getNic() {
        return Nic;
    }

    public void setNic(String nic) {
        Nic = nic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "EmployeeTM{" +
                "Eid='" + Eid + '\'' +
                ", Nic='" + Nic + '\'' +
                ", name='" + name + '\'' +
                ", dob='" + dob + '\'' +
                ", gender='" + gender + '\'' +
                ", address='" + address + '\'' +
                ", tel='" + tel + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
