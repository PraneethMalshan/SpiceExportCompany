package lk.ijse.spiceExportCompany.controller;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
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
import lk.ijse.spiceExportCompany.model.StokeModel;
import lk.ijse.spiceExportCompany.model.TransportModel;
import lk.ijse.spiceExportCompany.to.Employee;
import lk.ijse.spiceExportCompany.to.Stoke;
import lk.ijse.spiceExportCompany.to.Transport;
import lk.ijse.spiceExportCompany.util.Navigation;
import lk.ijse.spiceExportCompany.util.Routes;
import lk.ijse.spiceExportCompany.view.tm.EmployeeTM;
import lk.ijse.spiceExportCompany.view.tm.TransportTM;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class TransportFormController implements Initializable {
    ObservableList<TransportTM> Transport = FXCollections.observableArrayList();

    public AnchorPane pane;

    @FXML
    private TextField txtTransportId;

    @FXML
    private TextField txtTransportCost;

    @FXML
    private JFXDatePicker txtTransportDate;

    @FXML
    private JFXTimePicker txtTransportTime;

    @FXML
    private TableView<TransportTM> tblTransport;

    @FXML
    private TableColumn<Transport, String> colTransportId;

    @FXML
    private TableColumn<Transport, String> colTransportDate;

    @FXML
    private TableColumn<Transport, String> colTransportTime;

    @FXML
    private TableColumn<Transport, String> colTransportCost;

    @FXML
    private TextField txtTransportSearch;

//    private String transportId;
//    private String date;
//    private String time;
//    private String cost;
    @FXML
    void btnTransportAddOnAction(ActionEvent event) {
        String transportId = txtTransportId.getText();
        String date = txtTransportDate.getValue().toString();
        String time = txtTransportTime.getValue().toString();
        String cost = txtTransportCost.getText();


        Transport transport = new Transport(transportId, date, time, cost);

        try {
            boolean isAdded = TransportModel.add(transport);

            if (isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "Transport Added!").show();
                setTableData();
                clearTextField();

            } else {
                new Alert(Alert.AlertType.WARNING, "Something Happened!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void txtTransportIdOnAction(ActionEvent event) {
        String transportId = txtTransportId.getText();

        try {
            Transport transport = TransportModel.search(transportId);
            if (transport != null) {
                fillData(transport);
            }else {
                new Alert(Alert.AlertType.WARNING, "Something Happened!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void fillData(Transport transport) {
        txtTransportId.setText(transport.getTransportId());
        txtTransportDate.setValue(LocalDate.parse(transport.getDate()));
        txtTransportTime.setValue(LocalTime.parse(transport.getTime()));
        txtTransportCost.setText(transport.getCost());


    }


    @FXML
    void btnTransportSearchM(MouseEvent event) {
        String transportId = txtTransportSearch.getText();

        try {
            Transport transport = TransportModel.search(transportId);
            if (transport != null) {
                fillData(transport);
            }else {
                new Alert(Alert.AlertType.WARNING, "Something Happened!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void txtTransportSearchOnAction(ActionEvent event) {
    }


    @FXML
    void btnTransportUpdateOnAction(ActionEvent event) {
        System.out.println("click");
        try {
            if (TransportModel.transportUpdate(
                    new Transport(txtTransportId.getText(),
                            txtTransportDate.getValue().toString(),
                            txtTransportTime.getValue().toString(),
                            txtTransportCost.getText())
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
        txtTransportId.setText("");
        txtTransportDate.setValue(LocalDate.parse(""));
        txtTransportTime.setValue(LocalTime.parse(""));
        txtTransportCost.setText("");
    }

    @FXML
    void btnTransportDeleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {

        if (TransportModel.delete(txtTransportId.getText())) {
            new Alert(Alert.AlertType.CONFIRMATION, "Data Deleted!").show();
            setTableData();
            clearTextField();
        } else {
            new Alert(Alert.AlertType.CONFIRMATION, "No data").show();
        }
    }



    public void btnBackTransportOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.MANAGERDASHBOARD, pane);
    }
    public void btnNextTransportOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.VEHICLE, pane);
    }

//    private String Transportid;
//    private String Date;
//    private String Time;
//    private String Cost;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colTransportId.setCellValueFactory(new PropertyValueFactory<>("Transportid"));
        colTransportDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
        colTransportTime.setCellValueFactory(new PropertyValueFactory<>("Time"));
        colTransportCost.setCellValueFactory(new PropertyValueFactory<>("Cost"));
        setTableData();
    }

    private void setTableData() {
        tblTransport.getItems().clear();
        try {
            ResultSet set = TransportModel.getAllData();
            while (set.next()) {
                Transport.add(new TransportTM(
                        set.getString(1),
                        set.getString(2),
                        set.getString(3),
                        set.getString(4)
                ));
            }
            tblTransport.setItems(Transport);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }
}
