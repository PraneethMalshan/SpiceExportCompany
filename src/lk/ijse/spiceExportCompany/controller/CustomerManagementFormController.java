package lk.ijse.spiceExportCompany.controller;

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
import lk.ijse.spiceExportCompany.model.CustomerModel;
import lk.ijse.spiceExportCompany.model.EmployeeModel;
import lk.ijse.spiceExportCompany.to.Customer;
import lk.ijse.spiceExportCompany.to.Employee;
import lk.ijse.spiceExportCompany.util.Navigation;
import lk.ijse.spiceExportCompany.util.Routes;
import lk.ijse.spiceExportCompany.view.tm.CustomerTM;
import lk.ijse.spiceExportCompany.view.tm.EmployeeTM;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerManagementFormController implements Initializable {
    ObservableList<CustomerTM> Customer = FXCollections.observableArrayList();

    @FXML
    public AnchorPane pane;

    @FXML
    private TextField txtCusId;

    @FXML
    private TextField txtCusName;

    @FXML
    private TextField txtCusAddress;

    @FXML
    private TextField txtCusTell;

    @FXML
    private TextField txtCusEmail;

    @FXML
    private TableView<CustomerTM> tblCustomer;

    @FXML
    private TableColumn<Customer, String> colCusId;

    @FXML
    private TableColumn<Customer, String> colCusName;

    @FXML
    private TableColumn<Customer, String> colCusAddress;

    @FXML
    private TableColumn<Customer, String> colCusEmail;

    @FXML
    private TableColumn<Customer, String> colCusTell;

    @FXML
    private Label lblUserNameWarning;

    @FXML
    private Label lblUserNameWarning1;

    @FXML
    private Label lblUserNameWarning2;

    @FXML
    private Label lblUserNameWarning3;

    @FXML
    private Label lblUserNameWarning4;

    @FXML
    private TextField txtSearchCustomerM;

    //=======================================================================Validation 1st Part================================================
    private Matcher idMatcher;
    private Matcher nameMatcher;
    private Matcher addressMatcher;
    private Matcher tellMatcher;
    private Matcher emailMatcher;

    public void setPattern(){
        Pattern idPattern = Pattern.compile("^[a-zA-Z0-9]{4}$");
        idMatcher = idPattern.matcher(txtCusId.getText());

        Pattern namePattern = Pattern.compile("^[a-zA-Z0-9]{4,}$");
        nameMatcher = namePattern.matcher(txtCusName.getText());

        Pattern addressPattern = Pattern.compile("^[a-z.\\sA-Z.\\s]{4,}$");
        addressMatcher = addressPattern.matcher(txtCusAddress.getText());

        Pattern tellPattern = Pattern.compile("^(?:7|0|(?:\\+94))[0-9]{9,10}$");
        tellMatcher = tellPattern.matcher(txtCusTell.getText());


        Pattern emailPattern = Pattern.compile("^([a-z0-9]{2,})([@])([a-z]{2,9})([.])([a-z]{2,})$");
        emailMatcher = emailPattern.matcher(txtCusEmail.getText());

    }


//=======================================================================Validation 1st Part over================================================


//=======================================================================Set Tables================================================


    @FXML
    void btnCustomerAddOnAction(ActionEvent event) {

        String cusId = txtCusId.getText();
        String cusName = txtCusName.getText();
        String address = txtCusAddress.getText();
        String email = txtCusEmail.getText();
        String tell = txtCusTell.getText();


        Customer customer = new Customer(cusId, cusName, address, email, tell);

        try {
            boolean isAdded = CustomerModel.add(customer);

            if (isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "Customer Added!").show();
                setTableData();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something Happened!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void txtCusIdOnAction(ActionEvent event) {
        String cusId = txtCusId.getText();

        try {
            Customer customer = CustomerModel.search(cusId);
            if (customer != null) {
                fillData(customer);
            }else {
                new Alert(Alert.AlertType.WARNING, "Something Happened!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void fillData(Customer customer) {
        txtCusId.setText(customer.getCusId());
        txtCusName.setText(customer.getCusName());
        txtCusAddress.setText(customer.getAddress());
        txtCusEmail.setText(customer.getEmail());
        txtCusTell.setText(customer.getTell());
    }

    @FXML
    void btnSearchCustomerOnActionM(MouseEvent event) {
        String cusId = txtSearchCustomerM.getText();

        try {
            Customer customer = CustomerModel.search(cusId);
            if (customer != null) {
                fillData(customer);
            }else {
                new Alert(Alert.AlertType.WARNING, "Something Happened!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void txtSearchCustomerOnActionM(ActionEvent event) {
    }

    @FXML
    void btnCustomerUpdateOnAction(ActionEvent event) {
        System.out.println("click");
        try {
            if (CustomerModel.customerUpdate(
                    new Customer(txtCusId.getText(),
                            txtCusName.getText(),
                            txtCusAddress.getText(),
                            txtCusEmail.getText(),
                            txtCusTell.getText())
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
        txtCusId.setText("");
        txtCusName.setText("");
        txtCusAddress.setText("");
        txtCusEmail.setText("");
        txtCusTell.setText("");
    }


    @FXML
    void btnCustomerDeleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        if (CustomerModel.delete(txtCusId.getText())) {
            new Alert(Alert.AlertType.CONFIRMATION, "Data Deleted!").show();
            setTableData();
            clearTextField();
        } else {
            new Alert(Alert.AlertType.CONFIRMATION, "No data").show();
        }
    }

//=======================================================================Table Load second Step================================================

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colCusId.setCellValueFactory(new PropertyValueFactory<>("CusId"));
        colCusName.setCellValueFactory(new PropertyValueFactory<>("CusName"));
        colCusAddress.setCellValueFactory(new PropertyValueFactory<>("CusAddress"));
        colCusEmail.setCellValueFactory(new PropertyValueFactory<>("Email"));
        colCusTell.setCellValueFactory(new PropertyValueFactory<>("Tell"));
        setTableData();
        setPattern();
    }

    private void setTableData() {
        tblCustomer.getItems().clear();
        try {
            ResultSet set = CustomerModel.getAllData();
            while (set.next()) {
                Customer.add(new CustomerTM(
                        set.getString(1),
                        set.getString(2),
                        set.getString(3),
                        set.getString(4),
                        set.getString(5)
                ));
            }
            tblCustomer.setItems(Customer);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

//=======================================================================Table Load over================================================


    public void btnCustomerBackOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.MANAGERDASHBOARD, pane);
    }
    public void btnCustomerNextBackOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.ORDER, pane);
    }

    //--------------------------Validation 2nd Step------------------------------------------------------------------------

    public void performPattern(){
        if(idMatcher.matches()) {
            if(nameMatcher.matches()) {
                if(addressMatcher.matches()) {
                    if(tellMatcher.matches()) {
                        if(emailMatcher.matches()) {
                            System.out.println("register action now perform");
                        } else {
                            txtCusEmail.requestFocus();
                            lblUserNameWarning4.setText("Invalid");
                            //txtCusEmail.setFocusColor(Paint.valueOf("Red"));
                        }
                    } else {
                        txtCusTell.requestFocus();
                        lblUserNameWarning3.setText("Invalid");
                        //lblUserNameWarning3.setFocusColor(Paint.valueOf("Red"));
                    }
                } else {
                    txtCusAddress.requestFocus();
                   lblUserNameWarning2.setText("Invalid");
                    //txtTel.setFocusColor(Paint.valueOf("Red"));
                }
            } else {
                txtCusName.requestFocus();
                lblUserNameWarning1.setText("Invalid");
                //txtEmail.setFocusColor(Paint.valueOf("Red"));
            }
        } else {
            txtCusId.requestFocus();
            lblUserNameWarning.setText("Invalid");
        }
    }

//------------------------------------------    Validation 3rd Step-----------------

    public void custIdOnKeyReleased(KeyEvent keyEvent) {
        lblUserNameWarning.setText("");

        Pattern idPattern = Pattern.compile("^[a-zA-Z0-9]{4}$");
        idMatcher = idPattern.matcher(txtCusId.getText());


        if(!idMatcher.matches()) {
//            System.out.println(txtUserName.getText());
            txtCusId.requestFocus();
            lblUserNameWarning.setText("invalid");
        }
    }

    public void custNameOnKeyReleased(KeyEvent keyEvent) {
        lblUserNameWarning1.setText("");

        Pattern namePattern = Pattern.compile("^[a-zA-Z0-9]{4,}$");
        nameMatcher = namePattern.matcher(txtCusName.getText());


        if(!nameMatcher.matches()) {
//            System.out.println(txtUserName.getText());
            txtCusName.requestFocus();
            lblUserNameWarning1.setText("invalid");
        }
    }

    public void custAddressOnKeyReleased(KeyEvent keyEvent) {
        lblUserNameWarning2.setText("");

        Pattern addressPattern = Pattern.compile("^[a-z.\\sA-Z.\\s]{4,}$");
        addressMatcher = addressPattern.matcher(txtCusAddress.getText());


        if(!addressMatcher.matches()) {
//            System.out.println(txtUserName.getText());
            txtCusAddress.requestFocus();
            lblUserNameWarning2.setText("invalid");
        }
    }

    public void custTellOnKeyReleased(KeyEvent keyEvent) {
        lblUserNameWarning3.setText("");

        Pattern tellPattern = Pattern.compile("^(?:7|0|(?:\\+94))[0-9]{9,10}$");
        tellMatcher = tellPattern.matcher(txtCusTell.getText());


        if(!tellMatcher.matches()) {
//            System.out.println(txtUserName.getText());
            txtCusTell.requestFocus();
            lblUserNameWarning3.setText("invalid");
        }
    }

    public void custEmailOnKeyReleased(KeyEvent keyEvent) {
        lblUserNameWarning4.setText("");

        Pattern emailPattern = Pattern.compile("^([a-z0-9]{2,})([@])([a-z]{2,9})([.])([a-z]{2,})$");
        emailMatcher = emailPattern.matcher(txtCusEmail.getText());


        if(!emailMatcher.matches()) {
//            System.out.println(txtUserName.getText());
            txtCusEmail.requestFocus();
            lblUserNameWarning4.setText("invalid");
        }
    }

    public void btnCustomerReportOnAction(ActionEvent event) {
        InputStream resource = getClass().getResourceAsStream("/lk/ijse/spiceExportCompany/report/CustomerReport.jrxml");
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(resource);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,null, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint,false);
        } catch (SQLException | ClassNotFoundException | JRException e) {
            throw new RuntimeException(e);

        }
    }

    //=======================================================================Over Validation================================================
}
