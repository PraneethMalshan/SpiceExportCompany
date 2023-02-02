package lk.ijse.spiceExportCompany.model;

import javafx.collections.ObservableList;
import lk.ijse.spiceExportCompany.db.DBConnection;
import lk.ijse.spiceExportCompany.to.Order;
import lk.ijse.spiceExportCompany.util.CrudUtil;
import lk.ijse.spiceExportCompany.view.tm.PlaceOrderTM;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderModel {


    public static boolean add(Order order) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO orders VALUES(?,?,?,?)";
        return CrudUtil.execute(sql, order.getCusOrderId(), order.getTime(), order.getDate(), order.getCusId());
    }

    //=============================
    public static String generateNextOrderId() throws SQLException, ClassNotFoundException {
        String sql = "SELECT cusOrderId FROM Orders ORDER BY cusOrderId DESC LIMIT 1";
        ResultSet result = CrudUtil.execute(sql);

        if (result.next()) {
            return generateNextOrderId(result.getString(1));
        }
        return generateNextOrderId(result.getString(null));
    }

    private static String generateNextOrderId(String currentOrderId) {
        if (currentOrderId != null) {
            String[] split = currentOrderId.split("B0");
            int cusOrderId = Integer.parseInt(split[1]);
            cusOrderId += 1;
            return "B00" + cusOrderId;
        }
        return "O001";
    }

    public static boolean setOrder(Order order, ObservableList<PlaceOrderTM> obList) throws SQLException {
        Connection connection = null;

        try {
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);
            if (setOrder(order)) {
                if (OrderDetailsModel.setDetails(order, obList)) {
                    if (ProductModel.updateQty(obList)) {
                        connection.commit();
                        return true;
                    } else {
                        connection.rollback();
                    }
                } else {
                    connection.rollback();
                }
            } else {
                connection.rollback();
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }finally {
            connection.setAutoCommit(true);
        }
        return false;
    }

    private static boolean setOrder(Order order) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO orders VALUES (?,?,?,?)",
                order.getCusOrderId(),
                order.getTime(),
                order.getDate(),
                order.getCusId()
                );
    }
}


//    public static boolean add(Order order) throws SQLException, ClassNotFoundException {
//        String sql = "INSERT INTO orders VALUES(?,?,?,?)";
//        return CrudUtil.execute(sql, order.getCusOrderId(),order.getTime(),order.getDate(),order.getTotalCost());
//    }
//
//    public static Order search(String cusOrderId) throws SQLException, ClassNotFoundException {
//        String sql = "SELECT * FROM orders WHERE cusOrderId = ?";
//        ResultSet result = CrudUtil.execute(sql, cusOrderId);
//
//        if (result.next()){
//            return new Order(
//                    result.getString(1),
//                    result.getString(2),
//                    result.getString(3),
//                    result.getString(4)
//            );
//        }
//        return null;
//    }
//
////    private String cusOrderId;
////    private String time;
////    private String date;
////    private String totalCost;
//
////    public static boolean orderUpdate(Order order) throws SQLException, ClassNotFoundException {
////        return CrudUtil.execute("UPDATE orders SET time=?,date=?,totalCost=?   WHERE cusOrderId=?",
////                order.getTime(),
////                order.getDate(),
////                order.getTotalCost(),
////                order.getCusOrderId()
////
////        );
////    }
//
//        public static boolean ordersUpdate(Order order) throws SQLException, ClassNotFoundException {
//            return CrudUtil.execute("UPDATE Orders SET time=?, date=?, totalCost=?   WHERE cusOrderId=?",
//                    order.getTime(),
//                    order.getDate(),
//                    order.getTotalCost(),
//                    order.getCusOrderId()
//
//            );
//        }


