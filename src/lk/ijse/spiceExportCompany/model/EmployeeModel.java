package lk.ijse.spiceExportCompany.model;

import lk.ijse.spiceExportCompany.to.Employee;
import lk.ijse.spiceExportCompany.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeModel {
    public static boolean add(Employee employee) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO Employee VALUES(?,?,?,?,?,?,?,?)";
        return CrudUtil.execute(sql, employee.geteId(),employee.getNationalId(),employee.getName(),employee.getDob(),employee.getGender(),employee.getAddress(),employee.getTell(),employee.getEmail());


       /* PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement("INSERT INTO Employee VALUES (?,?,?,?,?,?,?,?)");
        pstm.setString(1, employee.geteId());
        pstm.setString(2, employee.getNationalId());
        pstm.setString(3, employee.getName());
        pstm.setString(4, employee.getDob());
        pstm.setString(5, employee.getGender());
        pstm.setString(6, employee.getAddress());
        pstm.setString(7, employee.getTell());
        pstm.setString(8, employee.getEmail());

        return pstm.executeUpdate() > 0; */

    }

    public static Employee search(String eId) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM Employee WHERE eId = ?";
        ResultSet result = CrudUtil.execute(sql, eId);

        if (result.next()){
            return new Employee(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4),
                    result.getString(5),
                    result.getString(6),
                    result.getString(7),
                    result.getString(8)
            );
        }
        return null;
    }

    public static boolean employeeUpdate(Employee employee) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE employee SET nationalId=?,name=?,dob=?,gender=?,address=?,tell=?,email=? WHERE eId=?",
                employee.getNationalId(),
                employee.getName(),
                employee.getDob(),
                employee.getGender(),
                employee.getAddress(),
                employee.getTell(),
                employee.getEmail(),
                employee.geteId()

                );
    }

    public static boolean delete(String text) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM employee WHERE eId=?",text);
    }

    public static ResultSet getAllData() throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("SELECT * FROM employee");
    }
}
