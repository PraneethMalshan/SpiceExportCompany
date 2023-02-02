package lk.ijse.spiceExportCompany.model;

import lk.ijse.spiceExportCompany.to.Salary;
import lk.ijse.spiceExportCompany.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SalaryModel {

    public static boolean add(Salary salary) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO Salary VALUES(?,?,?)";
        return CrudUtil.execute(sql, salary.geteId(),salary.getDate(),salary.getPayment());
    }

    public static ResultSet getAllData() throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("SELECT * FROM Salary");

    }
}
