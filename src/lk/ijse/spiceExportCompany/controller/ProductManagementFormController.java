package lk.ijse.spiceExportCompany.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.spiceExportCompany.db.DBConnection;
import lk.ijse.spiceExportCompany.model.EmployeeModel;
import lk.ijse.spiceExportCompany.model.ProductModel;
import lk.ijse.spiceExportCompany.to.Employee;
import lk.ijse.spiceExportCompany.to.Product;
import lk.ijse.spiceExportCompany.util.Navigation;
import lk.ijse.spiceExportCompany.util.Routes;
import lk.ijse.spiceExportCompany.view.tm.EmployeeTM;
import lk.ijse.spiceExportCompany.view.tm.ProductTM;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ProductManagementFormController implements Initializable {
    ObservableList<ProductTM> Product = FXCollections.observableArrayList();

    public AnchorPane pane;

    @FXML
    private TextField txtProCode;

    @FXML
    private TextField txtProductName;

    @FXML
    private TextField txtUnitWeight;

    @FXML
    private TextField txtProductUnitPrice;

    @FXML
    private TextField txtProductQuantity;

    @FXML
    private TableView<ProductTM> tblProducts;

    @FXML
    private TableColumn<Product, String> colProductCode;

    @FXML
    private TableColumn<Product, String> colProductName;

    @FXML
    private TableColumn<Product, String> colUnitWeight;

    @FXML
    private TableColumn<Product, String> colProductUnitPrice;

    @FXML
    private TableColumn<Product, String> colProductQuantity;

    @FXML
    private TextField txtProductSearch;

//    private String productCode;
//    private String productName;
//    private String unitWeight;
//    private String productQuantity;
//    private String productUnitPrice;
//
    @FXML
    void btnProductAddOnAction(ActionEvent event) {
        String productCode = txtProCode.getText();
        String productName = txtProductName.getText();
        String unitWeight = txtUnitWeight.getText();
        String productQuantity = txtProductQuantity.getText();
        String productUnitPrice = txtProductUnitPrice.getText();

        Product product = new Product(productCode, productName, unitWeight, productQuantity, productUnitPrice);

        try {
            boolean isAdded = ProductModel.add(product);

            if (isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "Product Added!").show();
                setTableData();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something Happened!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void txtProductCodeOnAction(ActionEvent event) {
        String productCode = txtProCode.getText();

        try {
            Product product = ProductModel.search(productCode);
            if (product != null) {
                fillData(product);
            }else {
                new Alert(Alert.AlertType.WARNING, "Something Happened!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void fillData(Product product) {
        txtProCode.setText(product.getProductCode());
        txtProductName.setText(product.getProductName());
        txtUnitWeight.setText(product.getUnitWeight());
        txtProductQuantity.setText(product.getProductQuantity());
        txtProductUnitPrice.setText(product.getProductUnitPrice());

    }
    @FXML
    void btnProductSearchOnActionM(MouseEvent event) {
        String productCode = txtProductSearch.getText();

        try {
            Product product = ProductModel.search(productCode);
            if (product != null) {
                fillData(product);
            }else {
                new Alert(Alert.AlertType.WARNING, "Something Happened!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void txtProductSearchOnActionM(ActionEvent event) {
    }

    @FXML
    void btnProductUpdateOnAction(ActionEvent event) {
        System.out.println("click");
        try {
            if (ProductModel.productUpdate(
                    new Product(txtProCode.getText(),
                            txtProductName.getText(),
                            txtUnitWeight.getText(),
                            txtProductQuantity.getText(),
                            txtProductUnitPrice.getText())
            )) {
                new Alert(Alert.AlertType.CONFIRMATION, "Updated").show();
                clearTextField();
                setTableData();

            } else {
                new Alert(Alert.AlertType.ERROR, "Something Happend").show();
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    private void clearTextField() {
        txtProCode.setText("");
        txtProductName.setText("");
        txtUnitWeight.setText("");
        txtProductQuantity.setText("");
        txtProductUnitPrice.setText("");
    }


    @FXML
    void btnProductDeleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        if (ProductModel.delete(txtProCode.getText())) {
            new Alert(Alert.AlertType.CONFIRMATION, "Data Deleted!").show();
            setTableData();
            clearTextField();
        } else {
            new Alert(Alert.AlertType.CONFIRMATION, "No data").show();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

//        private String ProductCode;
//        private String ProductName;
//        private String UnitWeigh;
//        private String ProductUnitPrice;
//        private String ProductQuantity;

        colProductCode.setCellValueFactory(new PropertyValueFactory<>("ProductCode"));
        colProductName.setCellValueFactory(new PropertyValueFactory<>("ProductName"));
        colUnitWeight.setCellValueFactory(new PropertyValueFactory<>("UnitWeigh"));
        colProductQuantity.setCellValueFactory(new PropertyValueFactory<>("ProductUnitPrice"));
        colProductUnitPrice.setCellValueFactory(new PropertyValueFactory<>("ProductQuantity"));
        setTableData();
    }

    private void setTableData() {
        tblProducts.getItems().clear();
        try {
            ResultSet set = ProductModel.getAllData();
            while (set.next()) {
                Product.add(new ProductTM(
                        set.getString(1),
                        set.getString(2),
                        set.getString(3),
                        set.getString(4),
                        set.getString(5)
                ));
            }
            tblProducts.setItems(Product);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

    }


    public void btnProductBackOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.MANAGERDASHBOARD, pane);

    }

    public void btnProductNextBackOnAction(ActionEvent event) {
    }


    public void btnProductReportOnAction(ActionEvent event) {
        InputStream resource = getClass().getResourceAsStream("/lk/ijse/spiceExportCompany/report/ProductReport.jrxml");
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(resource);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,null, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint,false);
        } catch (SQLException | ClassNotFoundException | JRException e) {
            throw new RuntimeException(e);

        }
    }
}
