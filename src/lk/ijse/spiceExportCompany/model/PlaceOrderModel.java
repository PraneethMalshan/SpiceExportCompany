package lk.ijse.spiceExportCompany.model;

import lk.ijse.spiceExportCompany.db.DBConnection;
import lk.ijse.spiceExportCompany.to.Order;
import lk.ijse.spiceExportCompany.to.PlaceOrder;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

public class PlaceOrderModel {

    public static boolean placeOrder(PlaceOrder placeOrder) throws SQLException, ClassNotFoundException {
        try {
            DBConnection.getInstance().getConnection().setAutoCommit(false);
            boolean isOrderAdded = OrderModel.add(new Order(placeOrder.getCusOrderId() , LocalTime.now(), LocalDate.now(), placeOrder.getCusOrderId()));
            if (isOrderAdded) {
                boolean isUpdated = ProductModel.updateQuantity(placeOrder.getOrderdetails());
                if (isUpdated) {
                    boolean isOrderDetailsAdded = OrderDetailsModel.addOrderDetails(placeOrder.getOrderdetails());
                    if (isOrderDetailsAdded) {
                        DBConnection.getInstance().getConnection().commit();
                        return true;
                    }
                }
            }
            DBConnection.getInstance().getConnection().rollback();
            return false;
        } finally {
            DBConnection.getInstance().getConnection().setAutoCommit(true);
        }
    }
}
