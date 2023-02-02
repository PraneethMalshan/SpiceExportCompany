package lk.ijse.spiceExportCompany.to;

public class Vehicle {
    private String vehNo;
    private String vehType;

    public Vehicle() {
    }

    public Vehicle(String vehNo, String vehType) {
        this.vehNo = vehNo;
        this.vehType = vehType;
    }

    public String getVehNo() {
        return vehNo;
    }

    public void setVehNo(String vehNo) {
        this.vehNo = vehNo;
    }

    public String getVehType() {
        return vehType;
    }

    public void setVehType(String vehType) {
        this.vehType = vehType;
    }
}
