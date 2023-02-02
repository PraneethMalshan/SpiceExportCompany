package lk.ijse.spiceExportCompany.model;

import lk.ijse.spiceExportCompany.to.Employee;
import lk.ijse.spiceExportCompany.to.FarmerPayments;
import lk.ijse.spiceExportCompany.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FarmerPaymentsModel {
    public static boolean add(FarmerPayments farmerPayments) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO farmerpayments VALUES(?,?,?,?)";
        return CrudUtil.execute(sql, farmerPayments.getfId(),farmerPayments.getToFarmerPaymentId(),farmerPayments.getDate(),farmerPayments.getBalance());

    }


    public static boolean farmerPaymentsUpdate(FarmerPayments farmerPayments) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE farmerPayments SET toFarmerPaymentId=?,date=?,balance=? WHERE fId=?",
                farmerPayments.getToFarmerPaymentId(),
                farmerPayments.getDate(),
                farmerPayments.getBalance(),
                farmerPayments.getfId()


        );
    }

    public static FarmerPayments search(String toFarmerPaymentId) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM FarmerPayments WHERE toFarmerPaymentId = ?";
        ResultSet result = CrudUtil.execute(sql, toFarmerPaymentId);

        if (result.next()){
            return new FarmerPayments(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4)

            );
        }
        return null;

    }

    public static boolean delete(String text) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM FarmerPayments WHERE toFarmerPaymentId=?",text);

    }

    public static ResultSet getAllData() throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("SELECT * FROM FarmerPayments");

    }
}
