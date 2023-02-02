package lk.ijse.spiceExportCompany.util;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Navigation {
    private static AnchorPane pane;

    public static void navigate(Routes route, AnchorPane pane) throws IOException {
        Navigation.pane=pane;
        Navigation.pane.getChildren().clear();
        Stage window = (Stage)Navigation.pane.getScene().getWindow();

        switch (route){
            case MAIN:
                window.setTitle("Main Page");
                initUI("MainForm.fxml");
                break;
            case  LOGIN:
                window .setTitle("Login Page");
                initUI("LoginForm.fxml");
                break;

            case  EMPLOYEEMANAGEMENT:
                window .setTitle("EmployeeManagement Page");
                initUI("EmployeesManagement.fxml");
                break;

            case  MANAGERDASHBOARD:
                window .setTitle("ManagerDashboard Page");
                initUI("ManagerDashboardForm.fxml");
                break;
            case  FARMERMANAGEMENT:
                window .setTitle("FarmerManagement Page");
                initUI("FarmerManagementForm.fxml");
                break;
            case  FARMERS:
                window .setTitle("Farmers Page");
                initUI("FarmersForm.fxml");
                break;
            case  TYPESOFGOODSSUPPLIEDBYFARMER:
                window .setTitle("TypeOfGoodsSuppliedByFarmer Page");
                initUI("TypeOfGoodSuppliedByFarmerForm.fxml");
                break;
            case  FARMERSPAMENTS:
                window .setTitle("FarmerPayments Page");
                initUI("FarmerPaymerntsForm.fxml");
                break;
            case  STOCK:
                window .setTitle("StockForm Page");
                initUI("StockForm.fxml");
                break;
            case  TRANSPORTMANAGEMENT:
                window .setTitle("TransportManagement Page");
                initUI("TransportManagment.fxml");
                break;
            case  VEHICLE:
                window .setTitle("VehicleForm Page");
                initUI("VehicleForm.fxml");
                break;
            case  DRIVER:
                window .setTitle("DriverForm Page");
                initUI("DriverForm.fxml");
                break;
            case  CUSTOMERS:
                window .setTitle("CustomersForm Page");
                initUI("CustomersForm.fxml");
                break;
            case  ORDER:
                window .setTitle("OrderForm Page");
                initUI("OrderForm.fxml");
                break;


            case  PRODUCT:
                window .setTitle("ProductManagementForm Page");
                initUI("ProductManagementForm.fxml");
                break;
            case  PLACEORDERMANAGEMENT:
                window .setTitle("PlaceOrderFormsManagement Page");
                initUI("PlaceOrderFormsManagement.fxml");
                break;


            default:
                new Alert(Alert.AlertType.ERROR, "UI Not Found!").show();
        }

    }

    private static void initUI(String location) throws IOException {
        Navigation.pane.getChildren().add(FXMLLoader.load(Navigation.class.getResource("/lk/ijse/spiceExportCompany/view/" + location)));

    }
    public static void swatchNavigation(String link, ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(Navigation.class.getResource("/lk/ijse/spiceExportCompany/view/" + link));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
}

}
