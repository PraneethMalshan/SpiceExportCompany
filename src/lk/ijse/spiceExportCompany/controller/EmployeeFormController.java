package lk.ijse.spiceExportCompany.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import lk.ijse.spiceExportCompany.db.DBConnection;
import lk.ijse.spiceExportCompany.model.EmployeeModel;
import lk.ijse.spiceExportCompany.to.Employee;
import lk.ijse.spiceExportCompany.to.Salary;
import lk.ijse.spiceExportCompany.view.tm.EmployeeTM;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmployeeFormController implements Initializable {
    @FXML
    private TextField txtEmployeeIdSOnActionM;
    //@FXML
    //public JFXTextField txtEmployeeIdSOnActionM;//
    public AnchorPane empPane;
    ObservableList<EmployeeTM> Employee = FXCollections.observableArrayList();

    @FXML
   // txtEmployeeIdSOnActionM
    public TextField txtEmployeeIdSOnAction;//
    @FXML
    private AnchorPane pane;
    @FXML
    private TableView<EmployeeTM> tblEmployee;

    @FXML
    private TableColumn<Employee, String> colEmployeeEid;

    @FXML
    private TableColumn<Employee, String> colEmployeeNationalId;

    @FXML
    private TableColumn<Employee, String> colEmployeeName;

    @FXML
    private TableColumn<Employee, String> colEmployeeDob;

    @FXML
    private TableColumn<Employee, String> colEmployeeGender;

    @FXML
    private TableColumn<Employee, String> colEmployeeAddress;

    @FXML
    private TableColumn<Employee, String> colEmployeeTell;

    @FXML
    private TableColumn<Employee, String> colEmployeeEmail;



    @FXML
    private JFXTextField txtEmployeeId;

    @FXML
    private JFXTextField txtEmployeeNationalId;

    @FXML
    private JFXTextField txtEmployeeName;

    @FXML
    private JFXTextField txtEmployeeDOB;

    @FXML
    private JFXTextField txtEmployeeGender;

    @FXML
    private JFXTextField txtEmployeeAddress;

    @FXML
    private JFXTextField txtEmployeeTell;

    @FXML
    private JFXTextField txtEmployeeEmail;

//    @FXML
//    private TextField txtEmployeeNationalId;
//    @FXML
//    private TextField txtEmployeeId;
//    @FXML
//    private TextField txtEmployeeName;
//    @FXML
//    private TextField txtEmployeeDOB;
//    @FXML
//    private TextField txtEmployeeGender;
//    @FXML
//    private TextField txtEmployeeAddress;
//    @FXML
//    private TextField txtEmployeeTell;
//    @FXML
//    private TextField txtEmployeeEmail;

    @FXML
    private Label lblEmployeeNameWarning;

    @FXML
    private Label lblEmployeeNameWarning1;

    @FXML
    private Label lblEmployeeNameWarning2;

    @FXML
    private Label lblEmployeeNameWarning3;

    @FXML
    private Label lblEmployeeNameWarning4;

    @FXML
    private Label lblEmployeeNameWarning5;

    @FXML
    private Label lblEmployeeNameWarning6;

    @FXML
    private Label lblEmployeeNameWarning7;




    private Matcher eIdMatcher;
    private Matcher nationalIdMatcher;
    private Matcher nameMatcher;
    private Matcher dobMatcher;
    private Matcher genderMatcher;
    private Matcher addressMatcher;
    private Matcher tellMatcher;
    private Matcher emailMatcher;


    public void setPattern(){
        Pattern eidPattern = Pattern.compile("^[a-zA-Z0-9]{4}$");
        eIdMatcher = eidPattern.matcher(txtEmployeeId.getText());

        Pattern nationalIdPattern = Pattern.compile("^[0-9]{12}$");
        nationalIdMatcher = nationalIdPattern.matcher(txtEmployeeNationalId.getText());

        Pattern namePattern = Pattern.compile("^[a-zA-Z0-9]{4,}$");
        nameMatcher = namePattern.matcher(txtEmployeeName.getText());
//dob      ---------
        //-----------
        //Pattern dobPattern = Pattern.compile("^[0-9]{12}$");
        Pattern dobPattern = Pattern.compile("^(?:0[1-9]|[12]\\d|3[01])([\\/.-])(?:0[1-9]|1[012])\\1(?:19|20)\\d\\d$");
        dobMatcher = dobPattern.matcher(txtEmployeeDOB.getText());


        Pattern genderPattern = Pattern.compile("^[a-zA-Z0-9]{4,}$");
        genderMatcher = genderPattern.matcher(txtEmployeeGender.getText());

        Pattern addressPattern = Pattern.compile("^[a-z.\\sA-Z.\\s]{4,}$");
        addressMatcher = addressPattern.matcher(txtEmployeeAddress.getText());

        Pattern tellPattern = Pattern.compile("^(?:7|0|(?:\\+94))[0-9]{9,10}$");
        tellMatcher = tellPattern.matcher(txtEmployeeTell.getText());


        Pattern emailPattern = Pattern.compile("^([a-z0-9]{2,})([@])([a-z]{2,9})([.])([a-z]{2,})$");
        emailMatcher = emailPattern.matcher(txtEmployeeEmail.getText());

    }

    @FXML
    void btnAddEmployeesOnAction(ActionEvent event) {
        String eId = txtEmployeeId.getText();
        String nationalId = txtEmployeeNationalId.getText();
        String name = txtEmployeeName.getText();
        String dob = txtEmployeeDOB.getText();
        String gender = txtEmployeeGender.getText();
        String address = txtEmployeeAddress.getText();
        String tell = txtEmployeeTell.getText();
        String email = txtEmployeeEmail.getText();

        Employee employee = new Employee(eId, nationalId, name, dob, gender, address, tell, email);

        try {
            boolean isAdded = EmployeeModel.add(employee);

            if (isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "Employee Added!").show();
                setTableData();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something Happened!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    void btnUpdateEmployeesOnAction(ActionEvent event) {
        System.out.println("click");
        try {
            if (EmployeeModel.employeeUpdate(
                    new Employee(txtEmployeeId.getText(),
                            txtEmployeeNationalId.getText(),
                            txtEmployeeName.getText(),
                            txtEmployeeDOB.getText(),
                            txtEmployeeGender.getText(),
                            txtEmployeeAddress.getText(),
                            txtEmployeeTell.getText(),
                            txtEmployeeEmail.getText())
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
        txtEmployeeId.setText("");
        txtEmployeeNationalId.setText("");
        txtEmployeeName.setText("");
        txtEmployeeDOB.setText("");
        txtEmployeeGender.setText("");
        txtEmployeeAddress.setText("");
        txtEmployeeTell.setText("");
        txtEmployeeEmail.setText("");
    }

    @FXML
    void btnDeleteEmployeesOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {

        if (EmployeeModel.delete(txtEmployeeId.getText())) {
            new Alert(Alert.AlertType.CONFIRMATION, "Data Deleted!").show();
            setTableData();
            clearTextField();
        } else {
            new Alert(Alert.AlertType.CONFIRMATION, "No data").show();
        }
    }


//====================================SEarch Part===============================


    @FXML
    void txtEmployeeIdOnAction(ActionEvent event) {
        String eId = txtEmployeeId.getText();

        try {
            Employee employee = EmployeeModel.search(eId);
            if (employee != null) {
                fillData(employee);
            }else {
                new Alert(Alert.AlertType.WARNING, "Something Happened!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public void btnSearchEmployeesMousePointOnAction(MouseEvent mouseEvent) {
        String eId = txtEmployeeIdSOnActionM.getText();

        try {
            Employee employee = EmployeeModel.search(eId);
            if (employee != null) {
                fillData(employee);
            }else {
                new Alert(Alert.AlertType.WARNING, "Something Happened!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void txtEmployeeIdSearchOnActionM(ActionEvent event) {
    }




    private void fillData(Employee employee) {
        txtEmployeeId.setText(employee.geteId());
        txtEmployeeNationalId.setText(employee.getNationalId());
        txtEmployeeName.setText(employee.getName());
        txtEmployeeDOB.setText(employee.getDob());
        txtEmployeeGender.setText(employee.getGender());
        txtEmployeeAddress.setText(employee.getAddress());
        txtEmployeeTell.setText(employee.getTell());
        txtEmployeeEmail.setText(employee.getEmail());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {



        colEmployeeEid.setCellValueFactory(new PropertyValueFactory<>("Eid"));
        colEmployeeNationalId.setCellValueFactory(new PropertyValueFactory<>("Nic"));
        colEmployeeName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmployeeDob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colEmployeeGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        colEmployeeAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colEmployeeTell.setCellValueFactory(new PropertyValueFactory<>("tel"));
        colEmployeeEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        setTableData();
    }

    private void setTableData() {
        tblEmployee.getItems().clear();
        try {
            ResultSet set = EmployeeModel.getAllData();
            while (set.next()) {
                Employee.add(new EmployeeTM(
                        set.getString(1),
                        set.getString(2),
                        set.getString(3),
                        set.getString(4),
                        set.getString(5),
                        set.getString(6),
                        set.getString(7),
                        set.getString(8)
                ));
            }
            tblEmployee.setItems(Employee);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

//=======================================================Validation===================


    public void performPattern(){
        if(eIdMatcher.matches()) {
            if(nationalIdMatcher.matches()) {
                if (nameMatcher.matches()) {
                    if (dobMatcher.matches()) {
                        if (genderMatcher.matches()) {
                            if (addressMatcher.matches()) {
                                if (tellMatcher.matches()) {
                                    if (emailMatcher.matches()) {
                                        System.out.println("register action now perform");
                                    } else {
                                        txtEmployeeEmail.requestFocus();
                                        lblEmployeeNameWarning7.setText("Invalid");
                                        txtEmployeeEmail.setFocusColor(Paint.valueOf("Red"));
                                    }
                                } else {
                                    txtEmployeeTell.requestFocus();
                                    lblEmployeeNameWarning6.setText("Invalid");
                                    txtEmployeeTell.setFocusColor(Paint.valueOf("Red"));
                                }
                            } else {
                                txtEmployeeAddress.requestFocus();
                                lblEmployeeNameWarning5.setText("Invalid");
                                txtEmployeeAddress.setFocusColor(Paint.valueOf("Red"));
                            }
                        } else {
                            txtEmployeeGender.requestFocus();
                            lblEmployeeNameWarning4.setText("Invalid");
                            txtEmployeeGender.setFocusColor(Paint.valueOf("Red"));
                        }
                    } else {
                        txtEmployeeDOB.requestFocus();
                        lblEmployeeNameWarning3.setText("Invalid");
                        txtEmployeeDOB.setFocusColor(Paint.valueOf("Red"));
                    }
                }else {
                    txtEmployeeName.requestFocus();
                    lblEmployeeNameWarning2.setText("Invalid");
                    txtEmployeeName.setFocusColor(Paint.valueOf("Red"));
                }
            }else {
                txtEmployeeNationalId.requestFocus();
                lblEmployeeNameWarning1.setText("Invalid");
                txtEmployeeNationalId.setFocusColor(Paint.valueOf("Red"));
            }
        }else {
            txtEmployeeId.requestFocus();
            lblEmployeeNameWarning.setText("Invalid");
            txtEmployeeId.setFocusColor(Paint.valueOf("Red"));
            }
        }

//------------------------------------------Validation 3rd Step-----------------

    @FXML
    void employeeIdOnKeyReleased(KeyEvent event) {
        lblEmployeeNameWarning.setText("");
        txtEmployeeId.setFocusColor(Paint.valueOf("Blue"));

        Pattern eidPattern = Pattern.compile("^[a-zA-Z0-9]{4}$");
        eIdMatcher = eidPattern.matcher(txtEmployeeId.getText());

        if(!eIdMatcher.matches()) {
//            System.out.println(txtUserName.getText());
            txtEmployeeId.requestFocus();
            txtEmployeeId.setFocusColor(Paint.valueOf("Red"));
            lblEmployeeNameWarning.setText("invalid!");
        }
    }

    @FXML
    void employeeNationalIdOnKeyReleased(KeyEvent event) {
        lblEmployeeNameWarning1.setText("");
        txtEmployeeNationalId.setFocusColor(Paint.valueOf("Blue"));

        Pattern nationalIdPattern = Pattern.compile("^[0-9]{12}$");
        nationalIdMatcher = nationalIdPattern.matcher(txtEmployeeNationalId.getText());


        if(!nationalIdMatcher.matches()) {
//            System.out.println(txtUserName.getText());
            txtEmployeeNationalId.requestFocus();
            txtEmployeeNationalId.setFocusColor(Paint.valueOf("Red"));
            lblEmployeeNameWarning1.setText("invalid!");
        }
    }

    @FXML
    void employeeNameOnKeyReleased(KeyEvent event) {
        lblEmployeeNameWarning2.setText("");
        txtEmployeeName.setFocusColor(Paint.valueOf("Blue"));

        Pattern namePattern = Pattern.compile("^[a-zA-Z0-9]{4,}$");
        nameMatcher = namePattern.matcher(txtEmployeeName.getText());


        if(!nameMatcher.matches()) {
//            System.out.println(txtUserName.getText());
            txtEmployeeName.requestFocus();
            txtEmployeeName.setFocusColor(Paint.valueOf("Red"));
            lblEmployeeNameWarning2.setText("invalid!");
        }
    }

    @FXML
    void employeedobOnKeyReleased(KeyEvent event) {
        lblEmployeeNameWarning3.setText("");
        txtEmployeeDOB.setFocusColor(Paint.valueOf("Blue"));

        Pattern dobPattern = Pattern.compile("^(?:0[1-9]|[12]\\d|3[01])([\\/.-])(?:0[1-9]|1[012])\\1(?:19|20)\\d\\d$");
        dobMatcher = dobPattern.matcher(txtEmployeeDOB.getText());

        if(!dobMatcher.matches()) {
//            System.out.println(txtUserName.getText());
            txtEmployeeDOB.requestFocus();
            txtEmployeeDOB.setFocusColor(Paint.valueOf("Red"));
            lblEmployeeNameWarning3.setText("invalid!");
        }
    }

    @FXML
    void employeeGenderOnKeyReleased(KeyEvent event) {
        lblEmployeeNameWarning4.setText("");
        txtEmployeeGender.setFocusColor(Paint.valueOf("Blue"));

        Pattern genderPattern = Pattern.compile("^[a-zA-Z0-9]{4,}$");
        genderMatcher = genderPattern.matcher(txtEmployeeGender.getText());

        if(!genderMatcher.matches()) {
//            System.out.println(txtUserName.getText());
            txtEmployeeGender.requestFocus();
            txtEmployeeGender.setFocusColor(Paint.valueOf("Red"));
            lblEmployeeNameWarning4.setText("invalid!");
        }
    }

    @FXML
    void employeeAddressOnKeyReleased(KeyEvent event) {
        lblEmployeeNameWarning5.setText("");
        txtEmployeeAddress.setFocusColor(Paint.valueOf("Blue"));

        Pattern addressPattern = Pattern.compile("^[a-z.\\sA-Z.\\s]{4,}$");
        addressMatcher = addressPattern.matcher(txtEmployeeAddress.getText());


        if(!addressMatcher.matches()) {
//            System.out.println(txtUserName.getText());
            txtEmployeeAddress.requestFocus();
            txtEmployeeAddress.setFocusColor(Paint.valueOf("Red"));
            lblEmployeeNameWarning5.setText("invalid!");
        }
    }

    @FXML
    void employeeTellOnKeyReleased(KeyEvent event) {
        lblEmployeeNameWarning6.setText("");
        txtEmployeeTell.setFocusColor(Paint.valueOf("Blue"));

        Pattern tellPattern = Pattern.compile("^(?:7|0|(?:\\+94))[0-9]{9,10}$");
        tellMatcher = tellPattern.matcher(txtEmployeeTell.getText());


        if(!tellMatcher.matches()) {
//            System.out.println(txtUserName.getText());
            txtEmployeeTell.requestFocus();
            txtEmployeeTell.setFocusColor(Paint.valueOf("Red"));
            lblEmployeeNameWarning6.setText("invalid!");
        }
    }

    @FXML
    void employeeEmailOnKeyReleased(KeyEvent event) {
        lblEmployeeNameWarning7.setText("");
        txtEmployeeEmail.setFocusColor(Paint.valueOf("Blue"));

        Pattern emailPattern = Pattern.compile("^([a-z0-9]{2,})([@])([a-z]{2,9})([.])([a-z]{2,})$");
        emailMatcher = emailPattern.matcher(txtEmployeeEmail.getText());

        if(!emailMatcher.matches()) {
//            System.out.println(txtUserName.getText());
            txtEmployeeEmail.requestFocus();
            txtEmployeeEmail.setFocusColor(Paint.valueOf("Red"));
            lblEmployeeNameWarning7.setText("invalid!");
        }
    }

    public void btnReportEmployeesOnAction(ActionEvent event) {
        InputStream resource = getClass().getResourceAsStream("/lk/ijse/spiceExportCompany/report/EmployeeReport.jrxml");
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(resource);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,null, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint,false);
        } catch (SQLException | ClassNotFoundException | JRException e) {
            throw new RuntimeException(e);

        }
    }





/*
//    @FXML
//    public void performPattern() {
//        if(eIdMatcher.matches()) {
//            if(nationalIdMatcher.matches()) {
//                if(nameMatcher.matches()) {
//                    if(dobMatcher.matches()) {
//                        if(genderMatcher.matches()) {
//                            if(addressMatcher.matches()) {
//                                if(tellMatcher.matches()) {
//                                    if(emailMatcher.matches()) {
//                                        System.out.println("register action now perform");
//                                    } else {
//                                        txtEmployeeEmail.requestFocus();
//                                        txtEmployeeEmail.setFocusColor(Paint.valueOf("Red"));
//                                    }
//                                } else {
//                                    txtEmployeeTell.requestFocus();
//                                    txtEmployeeTell.setFocusColor(Paint.valueOf("Red"));
//                                }
//                            } else {
//                                txtEmployeeAddress.requestFocus();
//                                txtEmployeeAddress.setFocusColor(Paint.valueOf("Red"));
//                            }
//                        } else {
//                            txtEmployeeGender.requestFocus();
//                            txtEmployeeGender.setFocusColor(Paint.valueOf("Red"));
//                        }
//                    } else {
//                        txtUserName.requestFocus();
//                        txtUserName.setFocusColor(Paint.valueOf("Red"));
//                        lblUserName.setText("invalid user name");
//                    }
//    }

//    public void txtEmployeeNameKeyTypedOnAction(KeyEvent keyEvent) {
//        lblUserName.setText("");
//        txtUserName.setFocusColor(Paint.valueOf("Blue"));
//
//        Pattern userNamePattern = Pattern.compile("^[a-zA-Z0-9]{4,}$");
//        userNameMatcher = userNamePattern.matcher(txtUserName.getText());
//
//        if(!userNameMatcher.matches()) {
////            System.out.println(txtUserName.getText());
//            txtUserName.requestFocus();
//            txtUserName.setFocusColor(Paint.valueOf("Red"));
//            lblUserName.setText("invalid user name");
//        }
//    }

//    private Matcher idMatcher;
//    private void setPatterns() {
//        Pattern idPattern = Pattern.compile("^(S)[0-9][0-9][0-9]$");
//        idMatcher = idPattern.matcher(txtEmployeeId.getText());
//
////        Pattern employeeIdPattern = Pattern.compile("^[a-zA-Z0-9]{4,}$");
////        employeeIdMatcher = userNamePattern.matcher(txtUserName.getText());
////
////        Pattern idPattern = Pattern.compile("^[a-zA-Z0-9]{4,}$");
////        userNameMatcher = userNamePattern.matcher(txtUserName.getText());
//    }

//    Pattern idPattern = Pattern.compile("^(S)[0-9][0-9][0-9]$");
//    idMatcher = idPattern.matcher(txtID.getText());
*/


}








