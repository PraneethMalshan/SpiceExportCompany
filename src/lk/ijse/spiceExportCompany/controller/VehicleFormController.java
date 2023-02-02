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
import lk.ijse.spiceExportCompany.model.EmployeeModel;
import lk.ijse.spiceExportCompany.model.TransportModel;
import lk.ijse.spiceExportCompany.model.VehicleModel;
import lk.ijse.spiceExportCompany.to.Employee;
import lk.ijse.spiceExportCompany.to.Transport;
import lk.ijse.spiceExportCompany.to.Vehicle;
import lk.ijse.spiceExportCompany.util.Navigation;
import lk.ijse.spiceExportCompany.util.Routes;
import lk.ijse.spiceExportCompany.view.tm.EmployeeTM;
import lk.ijse.spiceExportCompany.view.tm.VehicleTm;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class VehicleFormController implements Initializable {
    ObservableList<VehicleTm> Vehicle = FXCollections.observableArrayList();

    @FXML
    public AnchorPane pane;
    public TextField txtVehSearchM;

    @FXML
    private TextField txtVehNo;

    @FXML
    private TextField txtVehType;

    @FXML
    private TableView<VehicleTm> tblVehicle;

    @FXML
    private TableColumn<Vehicle, String> colVehicleNo;

    @FXML
    private TableColumn<Vehicle, String> colVehicleType;

    @FXML
    void btnVehAddOnAction(ActionEvent event) {
        String vehNo = txtVehNo.getText();
        String vehType = txtVehType.getText();

        Vehicle vehicle = new Vehicle(vehNo, vehType);

        try {
            boolean isAdded = VehicleModel.add(vehicle);

            if (isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "Vehicle Added!").show();
                setTableData();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something Happened!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
    @FXML
    void txtVehNoOnAction(ActionEvent event) {
        String vehNo = txtVehNo.getText();

        try {
            Vehicle vehicle = VehicleModel.search(vehNo);
            if (vehicle != null) {
                fillData(vehicle);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void btnVehSearchMOnAction(MouseEvent event) {
        String vehNo = txtVehSearchM.getText();

        try {
            Vehicle vehicle = VehicleModel.search(vehNo);
            if (vehicle != null) {
                fillData(vehicle);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void txtVehSearchMOnAction(ActionEvent event) {
    }
    private void fillData(Vehicle vehicle) {
        txtVehNo.setText(vehicle.getVehNo());
        txtVehType.setText(vehicle.getVehType());

    }

    @FXML
    void btnVehUpdateOnAction(ActionEvent event) {
        System.out.println("click");
        try {
            if (VehicleModel.vehicleUpdate(
                    new Vehicle(txtVehNo.getText(),
                            txtVehType.getText())

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
        txtVehNo.setText("");
        txtVehType.setText("");

    }


    @FXML
    void btnVehDeleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        if (VehicleModel.delete(txtVehNo.getText())) {
            new Alert(Alert.AlertType.CONFIRMATION, "Data Deleted!").show();
            setTableData();
            clearTextField();
        } else {
            new Alert(Alert.AlertType.CONFIRMATION, "No data").show();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colVehicleNo.setCellValueFactory(new PropertyValueFactory<>("VehicleNo"));
        colVehicleType.setCellValueFactory(new PropertyValueFactory<>("VehicleType"));
        setTableData();
    }

    private void setTableData() {
        tblVehicle.getItems().clear();
        try {
            ResultSet set = VehicleModel.getAllData();
            while (set.next()) {
                Vehicle.add(new VehicleTm(
                        set.getString(1),
                        set.getString(2)

                ));
            }
            tblVehicle.setItems(Vehicle);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

    }


    public void btnBackVehicleOnAction(ActionEvent event) throws IOException {
      //  Navigation.navigate(Routes.TRANSPORT, pane);
    }
    public void btnNextVehicleOnAction(ActionEvent event) throws IOException {
       // Navigation.navigate(Routes.DRIVER, pane);
    }



}
