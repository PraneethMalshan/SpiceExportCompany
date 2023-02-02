package lk.ijse.spiceExportCompany.to;

public class Farmers {
    private String fId;
    private String name;
    private String address;
    private String tell;
    private String  email;

    public Farmers() {
    }

    public Farmers(String fId, String name, String address, String tell, String email) {
        this.fId = fId;
        this.name = name;
        this.address = address;
        this.tell = tell;
        this.email = email;
    }

    public String getfId() {
        return fId;
    }

    public void setfId(String fId) {
        this.fId = fId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return "Farmers{" +
                "fId='" + fId + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", tell='" + tell + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
