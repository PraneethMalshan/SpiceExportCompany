package lk.ijse.spiceExportCompany.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.spiceExportCompany.model.CustomerModel;
import lk.ijse.spiceExportCompany.model.OrderModel;
import lk.ijse.spiceExportCompany.model.ProductModel;
import lk.ijse.spiceExportCompany.to.Customer;
import lk.ijse.spiceExportCompany.to.Order;
import lk.ijse.spiceExportCompany.to.PlaceOrder;
import lk.ijse.spiceExportCompany.to.Product;
import lk.ijse.spiceExportCompany.view.tm.PlaceOrderTM;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class CustomerPlaceOrderFormController implements Initializable {
    public Label lblProductUnitPrice;
    @FXML
    private Label lblOrderId;

    @FXML
    private Label lblOrderDate;

    @FXML
    private JFXComboBox<String> cmbCustomerId;

    @FXML
    private Label lblCustomerName;

    @FXML
    private JFXComboBox<String> cmbProductCode;

    @FXML
    private Label lblProductName;

    @FXML
    private Label txtProductUnitPrice;

    @FXML
    private TextField txtQty;

    @FXML
    private Label lblUnitWeight;

    @FXML
    private Label lblProductQuantity;

    @FXML
    private Label lblOrderTime;

    @FXML
    private TableView<PlaceOrderTM> tblOrderCart;

    @FXML
    private TableColumn<PlaceOrder, String> colProductCode;

    @FXML
    private TableColumn<PlaceOrder, String> colProductName;

    @FXML
    private TableColumn<PlaceOrder, Integer> colUnitWeight;

    @FXML
    private TableColumn<PlaceOrder, Integer> colQty;

    @FXML
    private TableColumn<PlaceOrder, String> colUnitPrice;

    @FXML
    private TableColumn<PlaceOrder, Double> colTotal;

    @FXML
    private TableColumn<?, ?> colAction;
    private final ObservableList<PlaceOrderTM> obList = FXCollections.observableArrayList();

    @FXML
    void btnAddToCartOnAction(ActionEvent event) {
        String productCode = String.valueOf(cmbProductCode.getValue());
        int quantity = Integer.parseInt(txtQty.getText());
        String productName = lblProductName.getText();
        double productUnitPrice = Double.parseDouble(lblProductUnitPrice.getText());
        double total = productUnitPrice * quantity;
        Button btnDelete = new Button("Delete");

        txtQty.setText("");
        if (!obList.isEmpty()) {
            L1:
            /* check same item has been in table. If so, update that row instead of adding new row to the table */
            for (int i = 0; i < tblOrderCart.getItems().size(); i++) {
                if (colProductCode.getCellData(i).equals(productCode)) {
                    quantity += colQty.getCellData(i);
                    total = productUnitPrice * quantity;

                    obList.get(i).setQuantity(quantity);
                    obList.get(i).setTotal(total);
                    tblOrderCart.refresh();
                    return;
                }
            }
        }
        /* set delete button to some action before it put on obList */
        btnDelete.setOnAction((e) -> {
            ButtonType ok = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("NO", ButtonBar.ButtonData.CANCEL_CLOSE);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure ?", ok, no);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.orElse(no) == ok) {
                PlaceOrderTM tm = tblOrderCart.getSelectionModel().getSelectedItem();
                /*
                netTot = Double.parseDouble(txtNetTot.getText());
                netTot = netTot - tm.getTotalPrice();
                */

                tblOrderCart.getItems().removeAll(tblOrderCart.getSelectionModel().getSelectedItem());
            }
        });
        obList.add(new PlaceOrderTM(productCode, productName, quantity, productUnitPrice, total, btnDelete));
        tblOrderCart.setItems(obList);
    }

    @FXML
    void btnNewCustomerOnAction(ActionEvent event) {
    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {
        try {
            if (OrderModel.setOrder(new Order(
                    lblOrderId.getText(),
                    LocalTime.parse(lblOrderTime.getText()),
                    LocalDate.parse(lblOrderDate.getText()),
                    String.valueOf(cmbCustomerId.getValue())
            ), obList)) {
                new Alert(Alert.AlertType.CONFIRMATION, "Ok").show();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    private void loadNextOrderId() {
        try {
            String cusOrderId = OrderModel.generateNextOrderId();
            lblOrderId.setText(cusOrderId);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadOrderDate() {
        lblOrderDate.setText(String.valueOf(LocalDate.now()));
    }

    private void loadOrderTime() {
        lblOrderTime.setText(String.valueOf(LocalTime.now()));
    }


    private void loadCustomerIds() {
        try {
            ObservableList<String> observableList = FXCollections.observableArrayList();
            ArrayList<String> cusIdList = CustomerModel.loadCustomerIds();

            for (String cusId : cusIdList) {
                observableList.add(cusId);
            }
            cmbCustomerId.setItems(observableList);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void cmbCustomerIdOnAction(ActionEvent event) {
        String cusId = String.valueOf(cmbCustomerId.getValue());
        try {
            Customer customer = CustomerModel.search(cusId);
            fillItemFields(customer);
            lblCustomerName.requestFocus();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void fillItemFields(Customer customer) {
        lblCustomerName.setText(customer.getCusName());
    }


    private void loadProductCodes() {
        try {
            ObservableList<String> observableList = FXCollections.observableArrayList();
            ArrayList<String> productList = ProductModel.loadProductCodes();

            for (String productCode : productList) {
                observableList.add(productCode);
            }
            cmbProductCode.setItems(observableList);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colProductCode.setCellValueFactory(new PropertyValueFactory("productCode"));
        colProductName.setCellValueFactory(new PropertyValueFactory("productName"));
        colQty.setCellValueFactory(new PropertyValueFactory("quantity"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory("price"));
        colTotal.setCellValueFactory(new PropertyValueFactory("total"));
        colAction.setCellValueFactory(new PropertyValueFactory("btnDelete"));
    }


    @FXML
    void cmbProductOnAction() {
        String productCode = String.valueOf(cmbProductCode.getValue());
        try {
            Product product = ProductModel.search(productCode);
            fillItemFields(product);
            txtQty.requestFocus();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void fillItemFields(Product product) {
        lblProductName.setText(product.getProductName());
        lblProductUnitPrice.setText(product.getProductUnitPrice());
        lblProductQuantity.setText(product.getProductQuantity());
    }

    @FXML
    void txtQtyOnAction(ActionEvent event) {
        btnAddToCartOnAction(event);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadNextOrderId();

        loadOrderDate();
        loadOrderTime();
        loadCustomerIds();
        loadProductCodes();
        setCellValueFactory();
    }
}
