package lk.ijse.spiceExportCompany.model;

import lk.ijse.spiceExportCompany.to.Attendance;
import lk.ijse.spiceExportCompany.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AttendanceModel {

    public static boolean add(Attendance attendance) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO attendance VALUES(?,?,?,?)";
        return CrudUtil.execute(sql, attendance.geteId(),attendance.getDate(),attendance.getInTime(),attendance.getOutTime());

    }

    public static ResultSet getAllData() throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("SELECT * FROM attendance");

    }
}
