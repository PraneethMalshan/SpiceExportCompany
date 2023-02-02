package lk.ijse.spiceExportCompany.model;

import lk.ijse.spiceExportCompany.to.Employee;
import lk.ijse.spiceExportCompany.to.Stoke;
import lk.ijse.spiceExportCompany.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StokeModel {
    public static boolean add(Stoke stoke) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO Stoke VALUES(?,?,?,?)";
        return CrudUtil.execute(sql, stoke.getsId(),stoke.getGoodName(),stoke.getUnitPrice(),stoke.getSuppliedQuantity());

    }

    public static Stoke search(String sId) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM Stoke WHERE sId = ?";
        ResultSet result = CrudUtil.execute(sql, sId);

        if (result.next()){
            return new Stoke(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4)
            );
        }
        return null;

    }

    public static boolean stokeUpdate(Stoke stoke) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE stoke SET goodName=?,unitPrice=?,suppliedQuantity=? WHERE sId=?",
                stoke.getGoodName(),
                stoke.getUnitPrice(),
                stoke.getSuppliedQuantity(),
                stoke.getsId()

        );

    }

    public static boolean delete(String text) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM stoke WHERE sId=?",text);

    }

    public static ResultSet getAllData() throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("SELECT * FROM stoke");

    }
}
