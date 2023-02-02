package lk.ijse.spiceExportCompany.model;

import lk.ijse.spiceExportCompany.to.Customer;
import lk.ijse.spiceExportCompany.to.Employee;
import lk.ijse.spiceExportCompany.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerModel {
    public static boolean add(Customer customer) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO Customer VALUES(?,?,?,?,?)";
        return CrudUtil.execute(sql, customer.getCusId(),customer.getCusName(),customer.getAddress(),customer.getEmail(),customer.getTell());


    }

    public static Customer search(String cusId) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM Customer WHERE cusId = ?";
        ResultSet result = CrudUtil.execute(sql, cusId);

        if (result.next()){
            return new Customer(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4),
                    result.getString(5)
            );
        }
        return null;
    }

//    private String cusId;
//    private String cusName;
//    private String address;
//    private String email;
//    private String tell;
    public static boolean customerUpdate(Customer customer) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE customer SET cusName=?,address=?,email=?,tell=?   WHERE cusId=?",
                customer.getCusName(),
                customer.getAddress(),
                customer.getEmail(),
                customer.getTell(),
                customer.getCusId()

        );
    }

    public static boolean delete(String text) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM customer WHERE cusId=?",text);

    }

    public static ResultSet getAllData() throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("SELECT * FROM customer");

    }

    public static ArrayList<String> loadCustomerIds() throws SQLException, ClassNotFoundException {
        String sql = "SELECT cusId FROM Customer";
        ResultSet result = CrudUtil.execute(sql);

        ArrayList<String> cusIdList = new ArrayList<>();

        while (result.next()) {
            cusIdList.add(result.getString(1));
        }
        return cusIdList;
    }

   /* public static ArrayList<String> loadCustomerName() throws SQLException, ClassNotFoundException {
        String sql = "SELECT cusName FROM Customer";
        ResultSet result = CrudUtil.execute(sql);

        ArrayList<String> cusNameList = new ArrayList<>();

        while (result.next()) {
            cusNameList.add(result.getString(2));
        }
        return cusNameList;
    }*/
}
