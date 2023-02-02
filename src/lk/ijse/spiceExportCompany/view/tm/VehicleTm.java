package lk.ijse.spiceExportCompany.view.tm;

public class VehicleTm {
    public VehicleTm() {
    }

    private String VehicleNo;
    private String VehicleType;

    public VehicleTm(String vehicleNo, String vehicleType) {
        VehicleNo = vehicleNo;
        VehicleType = vehicleType;
    }

    public String getVehicleNo() {
        return VehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        VehicleNo = vehicleNo;
    }

    public String getVehicleType() {
        return VehicleType;
    }

    public void setVehicleType(String vehicleType) {
        VehicleType = vehicleType;
    }

    @Override
    public String toString() {
        return "VehicleTm{" +
                "VehicleNo='" + VehicleNo + '\'' +
                ", VehicleType='" + VehicleType + '\'' +
                '}';
    }
}
