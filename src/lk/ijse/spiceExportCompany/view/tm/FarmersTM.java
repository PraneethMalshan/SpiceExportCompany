package lk.ijse.spiceExportCompany.view.tm;

public class FarmersTM {
     private String Fid;
     private String name;
     private String address;
     private String tell;
     private String email;

    public FarmersTM() {
    }

    public FarmersTM(String fid, String name, String address, String tell, String email) {
        this.Fid  = fid;
        this.name = name;
        this.address = address;
        this.tell = tell;
        this.email = email;
    }

    public String getFid() {
        return Fid;
    }

    public void setFid(String fid) {
        this.Fid = fid;
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
        return "FarmersTM{" +
                "Fid='" + Fid + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", tell='" + tell + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
