package lk.ijse.spiceExportCompany.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
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
import lk.ijse.spiceExportCompany.model.FarmerPaymentsModel;
import lk.ijse.spiceExportCompany.model.SalaryModel;
import lk.ijse.spiceExportCompany.to.Salary;
import lk.ijse.spiceExportCompany.util.Navigation;
import lk.ijse.spiceExportCompany.util.Routes;
import lk.ijse.spiceExportCompany.view.tm.FarmerPaymentsTM;
import lk.ijse.spiceExportCompany.view.tm.SalaryTM;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Parameter;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SalaryFormController implements Initializable {
    ObservableList<SalaryTM> Salary = FXCollections.observableArrayList();

    @FXML
    public AnchorPane pane;

    @FXML
    private JFXDatePicker txtSalDate;

    @FXML
    public TextField txtSalPayment;
    @FXML
    public JFXComboBox <String>txtSalCmb;
    @FXML
    public TableView tblSalary;

    @FXML
    public TableColumn colSalDate;
    @FXML
    public TableColumn colSalPayment;
    @FXML
    public TableColumn colSalEid;
    @FXML
    public TextField txtSalSearchM;


//public void initialize(){
//    loadEmployeeId();
//}

    private void loadEmployeeId() {
     //   System.out.println("Click");
        String sql = "Select eId from employee";
        ObservableList<String> data = FXCollections.observableArrayList();

        try {
            PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);
            ResultSet resultSet = pstm.executeQuery();
            while (resultSet.next()){
                data.add(resultSet.getString(1));
              //  System.out.println(resultSet.getString(1));
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
        txtSalCmb.setItems(data);

    }

    @FXML
    public void btnAddSalary(ActionEvent event) {
         String eId =  txtSalCmb.getValue();
         String date = txtSalDate.getValue().toString();
         String payment = txtSalPayment.getText();

        Salary salary = new Salary( eId,date, payment);
        try {
            boolean isAdded = SalaryModel.add(salary);

            if (isAdded){
                new Alert(Alert.AlertType.CONFIRMATION, "Salary Added!").show();
                setTableData();

            }else {
                new Alert(Alert.AlertType.WARNING, "Something Happend!").show();

            }

        }catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void btnSearchM(MouseEvent mouseEvent) {
    }

    @FXML
    public void btnUpdateSalary(ActionEvent event) {

    }

    @FXML
    public void btnDeleteSalary(ActionEvent event) {
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadEmployeeId();
        colSalEid.setCellValueFactory(new PropertyValueFactory<>("Eid"));
        colSalDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colSalPayment.setCellValueFactory(new PropertyValueFactory<>("payment"));
        setTableData();
    }

    private void setTableData() {
        tblSalary.getItems().clear();
        try {
            ResultSet set = SalaryModel.getAllData();
            while (set.next()) {
                Salary.add(new SalaryTM(
                        set.getString(1),
                        set.getString(2),
                        set.getString(3)


                ));
            }
            tblSalary.setItems(Salary);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

    }

    public void btnSalaryReportOnAction(ActionEvent event) {
        InputStream resource = getClass().getResourceAsStream("/lk/ijse/spiceExportCompany/report/SalaryReport.jrxml");
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(resource);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,null, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint,false);
        } catch (SQLException | ClassNotFoundException | JRException e) {
            throw new RuntimeException(e);

        }
    }
}
