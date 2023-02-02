package lk.ijse.spiceExportCompany.to;

public class Employee {
    private String eId;
    private String nationalId;
    private String name;
    private String dob;
    private String gender;
    private String address;
    private String tell;
    private String email;

    public Employee() {
    }

    public Employee(String eId, String nationalId, String name, String dob, String gender, String address, String tell, String email) {
        this.eId = eId;
        this.nationalId = nationalId;
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.address = address;
        this.tell = tell;
        this.email = email;
    }

    public String geteId() {
        return eId;
    }

    public void seteId(String eId) {
        this.eId = eId;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
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

    public String getTell() {
        return tell;
    }

    public void setTell(String tell) {
        this.tell = tell;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "eId='" + eId + '\'' +
                ", nationalId='" + nationalId + '\'' +
                ", name='" + name + '\'' +
                ", dob='" + dob + '\'' +
                ", gender='" + gender + '\'' +
                ", address='" + address + '\'' +
                ", tell='" + tell + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
