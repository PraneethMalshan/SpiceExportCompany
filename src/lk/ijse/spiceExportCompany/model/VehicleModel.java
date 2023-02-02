package lk.ijse.spiceExportCompany.model;

import lk.ijse.spiceExportCompany.to.Employee;
import lk.ijse.spiceExportCompany.to.Vehicle;
import lk.ijse.spiceExportCompany.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VehicleModel {
    public static boolean add(Vehicle vehicle) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO Vehicle VALUES(?,?)";
        return CrudUtil.execute(sql, vehicle.getVehNo(),vehicle.getVehType());

    }

    public static Vehicle search(String vehNo) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM Vehicle WHERE vehNo = ?";
        ResultSet result = CrudUtil.execute(sql, vehNo);

        if (result.next()){
            return new Vehicle(
                    result.getString(1),
                    result.getString(2)
            );
        }
        return null;

    }

    public static boolean vehicleUpdate(Vehicle vehicle) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE vehicle SET vehType=? WHERE vehNo=?",

                vehicle.getVehType(),
                vehicle.getVehNo()

        );
    }

    public static boolean delete(String text) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM vehicle WHERE vehNo=?",text);

    }

    public static ResultSet getAllData() throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("SELECT * FROM vehicle");

    }
}
