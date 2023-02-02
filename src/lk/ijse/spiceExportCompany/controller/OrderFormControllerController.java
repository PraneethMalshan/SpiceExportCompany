package lk.ijse.spiceExportCompany.controller;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.spiceExportCompany.model.EmployeeModel;
import lk.ijse.spiceExportCompany.model.OrderModel;
import lk.ijse.spiceExportCompany.to.Employee;
import lk.ijse.spiceExportCompany.to.Order;
import lk.ijse.spiceExportCompany.util.Navigation;
import lk.ijse.spiceExportCompany.util.Routes;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

import static java.time.LocalTime.parse;

public class OrderFormControllerController {
    @FXML
    private AnchorPane pane;

    @FXML
    private TextField OrderId;

    @FXML
    private TextField OrderTime;

    @FXML
    private TextField OrderDate;

    @FXML
    private TextField OrderTotalCost;//
    @FXML
    private TextField txtcusId;


    @FXML
    private TableView<?> tblOrder;

    @FXML
    private TableColumn<?, ?> colOrderId;

    @FXML
    private TableColumn<?, ?> colOrderTime;

    @FXML
    private TableColumn<?, ?> colOrderDate;

    @FXML
    private TableColumn<?, ?> colOrderTotalCost;//
    @FXML
    private TableColumn<?, ?> colCusId;






//    @FXML
//    public AnchorPane pane;
//
//    @FXML
//    private TextField txtOrderId;
//
//    @FXML
//    private TextField txtOrderTotalCost;
//
//    @FXML
//    private JFXTimePicker txtOrderTime;
//
//    @FXML
//    private JFXDatePicker txtOrderDate;
//
//    @FXML
//    private TableView<?> tblOrder;
//
//    @FXML
//    private TableColumn<?, ?> colOrderId;
//
//    @FXML
//    private TableColumn<?, ?> colTime;
//
//    @FXML
//    private TableColumn<?, ?> colDate;
//
//    @FXML
//    private TableColumn<?, ?> colTotalCost;
//
//    @FXML
//    private TextField txtSearchOrderM;
//
////    private String cusOrderId;
////    private String time;
////    private String date;
////    private String totalCost;
//
//    @FXML
//    void btnOrderAddOnAction(ActionEvent event) {
//        String cusOrderId = txtOrderId.getText();
//        String time = txtOrderTime.getValue().toString();
//        String date = txtOrderDate.getValue().toString();
//        String totalCost = txtOrderTotalCost.getText();
//
//        Order order = new Order(cusOrderId, time, date, totalCost );
//
//        try {
//            boolean isAdded = OrderModel.add(order);
//
//            if (isAdded) {
//                new Alert(Alert.AlertType.CONFIRMATION, "Employee Added!").show();
//               // setTableData();
//            } else {
//                new Alert(Alert.AlertType.WARNING, "Something Happened!").show();
//            }
//        } catch (SQLException | ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//
//    }
//
//    @FXML
//    public void txtOrderIdOnAction(ActionEvent event) {
//        String cusOrderId = txtOrderId.getText();
//
//        try {
//            Order order = OrderModel.search(cusOrderId);
//            if (order != null) {
//                fillData(order);
//            }
//        } catch (SQLException | ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    private void fillData(Order order) {
//        txtOrderId.setText(order.getCusOrderId());
//     // txtTransportDate.setValue(LocalDate.parse(transport.getDate()));
//        txtOrderTime.setValue(parse(order.getTime()));
//        txtOrderDate.setValue(LocalDate.parse(order.getDate()));
//        txtOrderTotalCost.setText(order.getTotalCost());
//
//    }
//
//    @FXML
//    void btnSearchOrderMOnAction(MouseEvent event) {
//        String cusOrderId = txtSearchOrderM.getText();
//
//        try {
//            Order order = OrderModel.search(cusOrderId);
//            if (order != null) {
//                fillData(order);
//            }
//        } catch (SQLException | ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//    }
//    @FXML
//    void txtSearchOrderMOnAction(ActionEvent event) {
//    }
//
//    @FXML
//    void btnOrderUpdateOnAction(ActionEvent event) {
//        System.out.println("click");
//        try {
//            if (OrderModel.ordersUpdate(
//                    new Order(txtOrderId.getText(),
//                            txtOrderTime.getValue().toString(),
//                            txtOrderDate.getValue().toString(),
//                            txtOrderTotalCost.getText())
//            )) {
//                new Alert(Alert.AlertType.CONFIRMATION, "Updated").show();
//                clearTextField();
//               // setTableData();
//
//            } else {
//                new Alert(Alert.AlertType.ERROR, "Something Happend").show();
//            }
//        } catch (SQLException | ClassNotFoundException throwables) {
//            throwables.printStackTrace();
//        }
//    }
//
//    private void clearTextField() {
//        txtOrderId.setText("");
//        txtOrderTime.setValue(LocalTime.parse(""));
//        txtOrderDate.setValue(LocalDate.parse(""));
//        txtOrderTotalCost.setText("");
//
//    }
//
//
//    @FXML
//    void btnOrderDeleteOnAction(ActionEvent event) {
//
//    }
//
//
//
//
//
//
//
//    public void btnBackOrderOnAction(ActionEvent event) throws IOException {
//        Navigation.navigate(Routes.MANAGERDASHBOARD, pane);
//
//    }


}
