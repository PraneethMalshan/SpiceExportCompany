package lk.ijse.spiceExportCompany.model;

import javafx.collections.ObservableList;
import lk.ijse.spiceExportCompany.to.CartDetail;
import lk.ijse.spiceExportCompany.to.Product;
import lk.ijse.spiceExportCompany.util.CrudUtil;
import lk.ijse.spiceExportCompany.view.tm.PlaceOrderTM;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductModel {
    public static boolean add(Product product) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO Products VALUES(?,?,?,?,?)";
        return CrudUtil.execute(sql, product.getProductCode(), product.getProductName(), product.getUnitWeight(), product.getProductQuantity(), product.getProductUnitPrice());


    }

    public static Product search(String productCode) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM Products WHERE productCode = ?";
        ResultSet result = CrudUtil.execute(sql, productCode);

        if (result.next()) {
            return new Product(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4),
                    result.getString(5)
            );
        }
        return null;
    }
//    private String productCode;
//    private String productName;
//    private String unitWeight;
//    private String productQuantity;
//    private String productUnitPrice;

    public static boolean productUpdate(Product product) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE Products SET productName=?,unitWeight=?,productQuantity=?,productUnitPrice=?  WHERE productCode=?",
                product.getProductName(),
                product.getUnitWeight(),
                product.getProductQuantity(),
                product.getProductUnitPrice(),
                product.getProductCode()
        );
    }

    public static boolean delete(String text) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM Products WHERE productCode=?", text);

    }

    public static ResultSet getAllData() throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("SELECT * FROM Products");

    }
//===================================================================================
//    public static boolean productUpdate(ArrayList<CartDetail> orderdetails) {
//        for (CartDetail cartDetail : orderdetails) {
//           // if (!productUpdate(new Product(cartDetail.getCode(), cartDetail.getDescription(), cartDetail.getUnitPrice(), cartDetail.getQty()))) {
//            if (!productUpdate(new Product(cartDetail.getProductCode(), cartDetail.getProductName(), cartDetail.getUnitWeight(),cartDetail.getPrice(),cartDetail.getQuantity()))) {
//                return false;
//            }
//        }
//        return true;
//    }

    public static ArrayList<String> loadProductCodes() throws SQLException, ClassNotFoundException {
        String sql = "SELECT productCode FROM Products";
        ResultSet result = CrudUtil.execute(sql);

        ArrayList<String> productCodeList = new ArrayList<>();

        while (result.next()) {
            productCodeList.add(result.getString(1));
        }
        return productCodeList;
    }

    public static boolean updateQuantity(ArrayList<CartDetail> cartDetails) throws SQLException, ClassNotFoundException {
        for (CartDetail cartDetail : cartDetails) {
            if (!updateQuantity(new Product(cartDetail.getProductCode(), cartDetail.getProductName(), cartDetail.getPrice(), cartDetail.getQuantity()))) {
                return false;
            }
        }
        return true;
    }

    private static boolean updateQuantity(Product product) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE Products SET productQuantity = productQuantity - ? WHERE productCode = ?";
        return CrudUtil.execute(sql, product.getProductQuantity(), product.getProductCode());
    }

    public static boolean updateQty(ObservableList<PlaceOrderTM> obList) throws SQLException, ClassNotFoundException {
        for (int i = 0; i < obList.size(); i++) {
            if (CrudUtil.execute("UPDATE products SET productQuantity=productQuantity-? WHERE productCode=?",
                    obList.get(i).getQuantity(),
                    obList.get(i).getProductCode()
            )) {
            } else {
                return false;
            }
        }
        return true;
    }
}
 /*   public static boolean updateQuantity(ArrayList<CartDetail> orderdetails) {
        for (CartDetail cartDetail : orderdetails) {
           // if (!updateQuantity(new Product(cartDetail.getProductCode(), cartDetail.getProductName(), cartDetail.getUnitWeight(), cartDetail.getPrice(),cartDetail.getQuantity()))) {
            if (!updateQuantity(new Product(cartDetail.getProductCode(),cartDetail.getProductName(),cartDetail.getUnitWeight(),cartDetail.getQuantity(),cartDetail.getPrice()))) {
              //  cartDetails.add(new CartDetail(orderId, tm.getProductCode(), tm.getProductName(), tm.getQuantity(), tm.getUnitWeight(), tm.getPrice()));

                return false;
            }
        }
        return true;
    }*/

