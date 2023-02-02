package lk.ijse.spiceExportCompany.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import lk.ijse.spiceExportCompany.db.DBConnection;
import lk.ijse.spiceExportCompany.model.EmployeeModel;
import lk.ijse.spiceExportCompany.model.FarmerPaymentsModel;
import lk.ijse.spiceExportCompany.model.SalaryModel;
import lk.ijse.spiceExportCompany.to.Employee;
import lk.ijse.spiceExportCompany.to.FarmerPayments;
import lk.ijse.spiceExportCompany.to.Salary;
import lk.ijse.spiceExportCompany.util.Navigation;
import lk.ijse.spiceExportCompany.util.Routes;
import lk.ijse.spiceExportCompany.view.tm.EmployeeTM;
import lk.ijse.spiceExportCompany.view.tm.FarmerPaymentsTM;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.time.LocalDate.parse;
import static sun.net.www.MimeTable.loadTable;

public class FarmerPaymerntsFormController implements Initializable{
    ObservableList<FarmerPaymentsTM> FarmerPayments = FXCollections.observableArrayList();

    @FXML
    public AnchorPane pane;

    @FXML
    private TextField txtFarmerPaymentId;

    @FXML
    private JFXDatePicker txtFarmerPaymentDate;

    @FXML
    private TextField txtFarmerPaymentBalance;

    @FXML
    private JFXComboBox <String>cmbFarmerPamentsId;

    @FXML
    private TableView<FarmerPaymentsTM> tblFarmerPayments;

    @FXML
    private TableColumn<FarmerPayments, String> colToFarmerPaymentid;

    @FXML
    private TableColumn<FarmerPayments, String> colFarmentPaymentsDate;

    @FXML
    private TableColumn<FarmerPayments, String> colFarmerPaymentBalance;

    @FXML
    private TableColumn<FarmerPayments, String> colFarmerPaymentFid;



//    public void initialize(){
//        loadFarmerfId();
//        setTableData();
//
//    }


    private void loadFarmerfId() {
        String sql = "Select fId from farmer";
        ObservableList<String> data = FXCollections.observableArrayList();

        try {
            PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);
            ResultSet resultSet = pstm.executeQuery();
            while (resultSet.next()){
                data.add(resultSet.getString(1));
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
        cmbFarmerPamentsId.setItems(data);


    }
    @FXML
    void btnFarmerPaymentsAdd(ActionEvent event) {
        String fId =  cmbFarmerPamentsId.getValue();
        String toFarmerPaymentId =  txtFarmerPaymentId.getText();
        String date =  txtFarmerPaymentDate.getValue().toString();
        String balance =  txtFarmerPaymentBalance.getText();

        FarmerPayments farmerPayments = new FarmerPayments( fId,toFarmerPaymentId,date, balance);
        try {
            boolean isAdded = FarmerPaymentsModel.add(farmerPayments);

            if (isAdded){
                new Alert(Alert.AlertType.CONFIRMATION, "FarmerPayments Added!").show();
                setTableData();

            }else {
                new Alert(Alert.AlertType.WARNING, "Something Happend!").show();

            }

        }catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }

    }
//======================================================Update==========================
    @FXML
    void btnFarmerPaymentsUpdate(ActionEvent event) {
        System.out.println("click");
        try {
            if (FarmerPaymentsModel.farmerPaymentsUpdate(
                    new FarmerPayments(cmbFarmerPamentsId.getValue(),
                            txtFarmerPaymentId.getText(),
                            txtFarmerPaymentDate.getValue().toString(),
                            txtFarmerPaymentBalance.getText())
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
        cmbFarmerPamentsId.setValue("");
        txtFarmerPaymentId.setText("");
        txtFarmerPaymentDate.setValue(parse(""));
        txtFarmerPaymentBalance.setText("");
    }




//=======================================================Search===================
    @FXML
    void txtFarmerPaymentIdOnAction(ActionEvent event) {
        String toFarmerPaymentId = txtFarmerPaymentId.getText();

        try {
            FarmerPayments farmerPayments = FarmerPaymentsModel.search(toFarmerPaymentId);
            if (farmerPayments != null) {
                fillData(farmerPayments);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void fillData(FarmerPayments farmerPayments) {
        cmbFarmerPamentsId.setValue(farmerPayments.getfId());
        txtFarmerPaymentId.setText(farmerPayments.getToFarmerPaymentId());
        txtFarmerPaymentDate.setValue(parse(farmerPayments.getDate()));
        txtFarmerPaymentBalance.setText(farmerPayments.getBalance());
    }


    @FXML
    void btnFarmerPaymentsDeleete(ActionEvent event) throws SQLException, ClassNotFoundException {
        if (FarmerPaymentsModel.delete(txtFarmerPaymentId.getText())) {
            new Alert(Alert.AlertType.CONFIRMATION, "Data Deleted!").show();
            setTableData();
            clearTextField();
        } else {
            new Alert(Alert.AlertType.CONFIRMATION, "No data").show();
        }
    }
//    private String fId;
//    private String toFarmerPaymentId;
//    private String date;
//    private String balance;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadFarmerfId();
        colFarmerPaymentFid.setCellValueFactory(new PropertyValueFactory<>("Fid"));
        colToFarmerPaymentid.setCellValueFactory(new PropertyValueFactory<>("toFarmerPaymentId"));
        colFarmentPaymentsDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colFarmerPaymentBalance.setCellValueFactory(new PropertyValueFactory<>("balance"));
        setTableData();
    }

    private void setTableData() {
        tblFarmerPayments.getItems().clear();
        try {
            ResultSet set = FarmerPaymentsModel.getAllData();
            while (set.next()) {
                FarmerPayments.add(new FarmerPaymentsTM(
                        set.getString(1),
                        set.getString(2),
                        set.getString(3),
                        set.getString(4)


                        ));
            }
            tblFarmerPayments.setItems(FarmerPayments);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

    }


    public void btnFarmerPaymentsBackOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.FARMERMANAGEMENT, pane);

    }








}
