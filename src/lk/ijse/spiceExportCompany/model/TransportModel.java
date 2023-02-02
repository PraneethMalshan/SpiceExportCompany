package lk.ijse.spiceExportCompany.model;

import lk.ijse.spiceExportCompany.to.Employee;
import lk.ijse.spiceExportCompany.to.Transport;
import lk.ijse.spiceExportCompany.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TransportModel {
    public static boolean add(Transport transport) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO Transport VALUES(?,?,?,?)";
        return CrudUtil.execute(sql, transport.getTransportId(),transport.getDate(),transport.getTime(),transport.getCost());

    }

    public static Transport search(String transportId) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM Transport WHERE transportId = ?";
        ResultSet result = CrudUtil.execute(sql, transportId);

        if (result.next()){
            return new Transport(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4)
            );
        }
        return null;
    }

    public static boolean transportUpdate(Transport transport) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE transport SET date=?,time=?,cost=?   WHERE transportId=?",
                transport.getDate(),
                transport.getTime(),
                transport.getCost(),
                transport.getTransportId()

        );
    }

    public static boolean delete(String text) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM transport WHERE transportId=?",text);

    }

    public static ResultSet getAllData() throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("SELECT * FROM transport");

    }
}
