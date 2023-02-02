package lk.ijse.spiceExportCompany.model;

import javafx.collections.ObservableList;
import lk.ijse.spiceExportCompany.to.CartDetail;
import lk.ijse.spiceExportCompany.to.Order;
import lk.ijse.spiceExportCompany.util.CrudUtil;
import lk.ijse.spiceExportCompany.view.tm.PlaceOrderTM;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailsModel {

    public static boolean addOrderDetails(ArrayList<CartDetail> cartDetails) throws SQLException, ClassNotFoundException {
        for (CartDetail cartDetail : cartDetails) {
            if (!add(cartDetail)) {
                return false;
            }
        }
        return true;
    }

    private static boolean add(CartDetail cartDetail) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO orderdetails VALLUES(?,?,?,?)";
        return CrudUtil.execute(sql, cartDetail.getCusOrderId(), cartDetail.getProductCode(), cartDetail.getQuantity(), cartDetail.getPrice());
    }

    public static boolean setDetails(Order order, ObservableList<PlaceOrderTM> obList) throws SQLException, ClassNotFoundException {
        for (int i = 0; i < obList.size(); i++) {
            if (CrudUtil.execute("INSERT INTO orderdetails VALUES(?,?,?,?)",
                    order.getCusOrderId(),
                    obList.get(i).getProductCode(),
                    obList.get(i).getQuantity(),
                    obList.get(i).getPrice()
            )) {
            } else {
                return false;
            }
        }
        return true;
    }
}
