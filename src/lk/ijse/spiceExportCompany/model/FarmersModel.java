package lk.ijse.spiceExportCompany.model;

import lk.ijse.spiceExportCompany.to.Employee;
import lk.ijse.spiceExportCompany.to.Farmers;
import lk.ijse.spiceExportCompany.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FarmersModel {

    public static boolean add(Farmers farmers) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO Farmer VALUES(?,?,?,?,?)";
        return CrudUtil.execute(sql, farmers.getfId(),farmers.getName(),farmers.getAddress(),farmers.getTell(),farmers.getEmail());


    }

    public static Farmers search(String fId) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM Farmer WHERE fId = ?";
        ResultSet result = CrudUtil.execute(sql, fId);

        if (result.next()){
            return new Farmers(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4),
                    result.getString(5)

            );
        }
        return null;

    }

    public static boolean farmersUpdate(Farmers farmers) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE farmer SET name=?,address=?,tell=?,email=? WHERE fId=?",
                farmers.getName(),
                farmers.getAddress(),
                farmers.getTell(),
                farmers.getEmail(),
                farmers.getfId()



                );
    }

    public static boolean delete(String text) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM farmer WHERE fId=?",text);

    }

    public static ResultSet getAllData() throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("SELECT * FROM farmer");

    }
}
