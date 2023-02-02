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
import lk.ijse.spiceExportCompany.model.FarmersModel;
import lk.ijse.spiceExportCompany.to.Employee;
import lk.ijse.spiceExportCompany.to.Farmers;
import lk.ijse.spiceExportCompany.util.Navigation;
import lk.ijse.spiceExportCompany.util.Routes;
import lk.ijse.spiceExportCompany.view.tm.EmployeeTM;
import lk.ijse.spiceExportCompany.view.tm.FarmersTM;
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

public class FarmersFormController implements Initializable {
//    public Label lblFarmerWarning;
//    public Label lblFarmerWarning1;
//    public Label lblFarmerWarning2;
//    public Label lblFarmerWarning3;
//    public Label lblFarmerWarning4;
    ObservableList<FarmersTM> Farmers = FXCollections.observableArrayList();
    @FXML
    public AnchorPane pane;
    public TextField txtSearchFarmersM;


//    @FXML
//    private TextField txtFarmersId;
//
//    @FXML
//    private TextField txtFarmersName;
//
//    @FXML
//    private TextField txtFarmersAddress;
//
//    @FXML
//    private TextField txtFarmersTell;
//
//    @FXML
//    private TextField txtFarmersEmail;

    @FXML
    private TableView<FarmersTM> tblFarmers;

    @FXML
    private TableColumn<Farmers, String> colFarmersFid;

    @FXML
    private TableColumn<Farmers, String> colFarmersName;

    @FXML
    private TableColumn<Farmers, String> colFarmersAddress;

    @FXML
    private TableColumn<Farmers, String> colFarmersTell;

    @FXML
    private TableColumn<Farmers, String> colFarmersEmail;

    @FXML
    private TextField btnSearchFarmersM;

    @FXML
    private JFXTextField txtFarmersId;

    @FXML
    private JFXTextField txtFarmersName;

    @FXML
    private JFXTextField txtFarmersAddress;

    @FXML
    private JFXTextField txtFarmersTell;

    @FXML
    private JFXTextField txtFarmersEmail;
    @FXML
    private Label lblFarmerWarning;

    @FXML
    private Label lblFarmerWarning1;

    @FXML
    private Label lblFarmerWarning2;

    @FXML
    private Label lblFarmerWarning3;

    @FXML
    private Label lblFarmerWarning4;


    //=======================================================================Validation 1st Part================================================

    private Matcher fIdMatcher;
    private Matcher nameMatcher;
    private Matcher addressMatcher;
    private Matcher tellMatcher;
    private Matcher emailMatcher;

    public void setPattern(){
        Pattern fIdPattern = Pattern.compile("^[a-zA-Z0-9]{4}$");
        fIdMatcher = fIdPattern.matcher(txtFarmersId.getText());

        Pattern namePattern = Pattern.compile("^[a-zA-Z0-9]{4,}$");
        nameMatcher = namePattern.matcher(txtFarmersName.getText());

        Pattern addressPattern = Pattern.compile("^[a-z.\\sA-Z.\\s]{4,}$");
        addressMatcher = addressPattern.matcher(txtFarmersAddress.getText());

        Pattern tellPattern = Pattern.compile("^(?:7|0|(?:\\+94))[0-9]{9,10}$");
        tellMatcher = tellPattern.matcher(txtFarmersTell.getText());


        Pattern emailPattern = Pattern.compile("^([a-z0-9]{2,})([@])([a-z]{2,9})([.])([a-z]{2,})$");
        emailMatcher = emailPattern.matcher(txtFarmersEmail.getText());

    }
    //=======================================================================Validation 1st Part over================================================







    @FXML
    void btnFarmersAddOnAction(ActionEvent event) {
        String fId = txtFarmersId.getText();
        String name = txtFarmersName.getText();
        String address = txtFarmersAddress.getText();
        String tell = txtFarmersTell.getText();
        String email = txtFarmersEmail.getText();

        Farmers farmers = new Farmers(fId, name, address, tell, email);

        try {
            boolean isAdded = FarmersModel.add(farmers);

            if (isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "Farmers Added!").show();
               setTableData();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something Happened!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    //==========================================Search=======================

    @FXML
    void txtFarmersIdOnAction(ActionEvent event) {
        String fId = txtFarmersId.getText();

        try {
            Farmers farmers = FarmersModel.search(fId);
            if (farmers != null) {
                fillData(farmers);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnSearchFarmersM(MouseEvent event) {
        String fId = txtSearchFarmersM.getText();

        try {
            Farmers farmers = FarmersModel.search(fId);
            if (farmers != null) {
                fillData(farmers);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void txtSearchFarmersM(ActionEvent event) {
        String fId = txtSearchFarmersM.getText();

        try {
            Farmers farmers = FarmersModel.search(fId);
            if (farmers != null) {
                fillData(farmers);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void fillData(Farmers farmers) {
        txtFarmersId.setText(farmers.getfId());
        txtFarmersName.setText(farmers.getName());
        txtFarmersAddress.setText(farmers.getAddress());
        txtFarmersTell.setText(farmers.getTell());
        txtFarmersEmail.setText(farmers.getEmail());
    }
//===================================================================
    @FXML
    void btnFarmersUpdateOnAction(ActionEvent event) {
        System.out.println("click");
        try {
            if (FarmersModel.farmersUpdate(
                    new Farmers(txtFarmersId.getText(),
                            txtFarmersName.getText(),
                            txtFarmersAddress.getText(),
                            txtFarmersTell.getText(),
                            txtFarmersEmail.getText())
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
        txtFarmersId.setText("");
        txtFarmersName.setText("");
        txtFarmersAddress.setText("");
        txtFarmersTell.setText("");
        txtFarmersEmail.setText("");
    }


    @FXML
    void btnFarmersDeleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        if (FarmersModel.delete(txtFarmersId.getText())) {
            new Alert(Alert.AlertType.CONFIRMATION, "Data Deleted!").show();
            setTableData();
            clearTextField();
        } else {
            new Alert(Alert.AlertType.CONFIRMATION, "No data").show();
        }

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colFarmersFid.setCellValueFactory(new PropertyValueFactory<>("Fid"));
        colFarmersName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colFarmersAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colFarmersTell.setCellValueFactory(new PropertyValueFactory<>("tell"));
        colFarmersEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        setTableData();
    }

    private void setTableData() {
        tblFarmers.getItems().clear();
        try {
            ResultSet set = FarmersModel.getAllData();
            while (set.next()) {
                Farmers.add(new FarmersTM(
                        set.getString(1),
                        set.getString(2),
                        set.getString(3),
                        set.getString(4),
                        set.getString(5)

                ));
            }
            tblFarmers.setItems(Farmers);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

    }





    public void performPattern(){
        if(fIdMatcher.matches()) {
            if(nameMatcher.matches()) {
                if(addressMatcher.matches()) {
                    if(tellMatcher.matches()) {
                        if(emailMatcher.matches()) {
                            System.out.println("register action now perform");
                        } else {
                            txtFarmersEmail.requestFocus();
                            lblFarmerWarning4.setText("Invalid");
                            txtFarmersEmail.setFocusColor(Paint.valueOf("Red"));
                        }
                    } else {
                        txtFarmersTell.requestFocus();
                        lblFarmerWarning3.setText("Invalid");
                        txtFarmersTell.setFocusColor(Paint.valueOf("Red"));
                    }
                } else {
                    txtFarmersAddress.requestFocus();
                    lblFarmerWarning2.setText("Invalid");
                    txtFarmersAddress.setFocusColor(Paint.valueOf("Red"));
                }
            } else {
                txtFarmersName.requestFocus();
                lblFarmerWarning1.setText("Invalid");
                txtFarmersName.setFocusColor(Paint.valueOf("Red"));
            }
        } else {
            txtFarmersId.requestFocus();
            lblFarmerWarning.setText("Invalid");
            txtFarmersId.setFocusColor(Paint.valueOf("Red"));
        }
    }

//    @FXML
//    void farmerFIdOnKeyReleased(java.awt.event.KeyEvent event) {
//
//    }
//    @FXML
//    void farmerNameOnKeyReleased(java.awt.event.KeyEvent event) {
//
//    }
//    @FXML
//    void farmerAddressOnKeyReleased(java.awt.event.KeyEvent event) {
//
//    }
//
//    @FXML
//    void farmerTellOnKeyReleased(java.awt.event.KeyEvent event) {
//
//    }
//
//    @FXML
//    void farmerEmailOnKeyReleased(java.awt.event.KeyEvent event) {
//
//    }























    public void btnFarmersBackOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.FARMERMANAGEMENT, pane);

    }


    public void farmerFIdOnKeyReleased(KeyEvent keyEvent) {
        lblFarmerWarning.setText("");
        txtFarmersId.setFocusColor(Paint.valueOf("Blue"));

        Pattern fIdPattern = Pattern.compile("^[a-zA-Z0-9]{4}$");
        fIdMatcher = fIdPattern.matcher(txtFarmersId.getText());

        if(!fIdMatcher.matches()) {
//            System.out.println(txtUserName.getText());
            txtFarmersId.requestFocus();
            txtFarmersId.setFocusColor(Paint.valueOf("Red"));
            lblFarmerWarning.setText("invalid!");
        }
    }

    public void farmerNameOnKeyReleased(KeyEvent keyEvent) {
        lblFarmerWarning1.setText("");
        txtFarmersName.setFocusColor(Paint.valueOf("Blue"));

        Pattern namePattern = Pattern.compile("^[a-zA-Z0-9]{4,}$");
        nameMatcher = namePattern.matcher(txtFarmersName.getText());

        if(!nameMatcher.matches()) {
//            System.out.println(txtUserName.getText());
            txtFarmersName.requestFocus();
            txtFarmersName.setFocusColor(Paint.valueOf("Red"));
            lblFarmerWarning1.setText("invalid!");
        }
    }

    public void farmerAddressOnKeyReleased(KeyEvent keyEvent) {
        lblFarmerWarning2.setText("");
        txtFarmersAddress.setFocusColor(Paint.valueOf("Blue"));

        Pattern addressPattern = Pattern.compile("^[a-z.\\sA-Z.\\s]{4,}$");
        addressMatcher = addressPattern.matcher(txtFarmersAddress.getText());

        if(!addressMatcher.matches()) {
//            System.out.println(txtUserName.getText());
            txtFarmersAddress.requestFocus();
            txtFarmersAddress.setFocusColor(Paint.valueOf("Red"));
            lblFarmerWarning2.setText("invalid!");
        }
    }

    public void farmerTellOnKeyReleased(KeyEvent keyEvent) {
        lblFarmerWarning3.setText("");
        txtFarmersTell.setFocusColor(Paint.valueOf("Blue"));

        Pattern tellPattern = Pattern.compile("^(?:7|0|(?:\\+94))[0-9]{9,10}$");
        tellMatcher = tellPattern.matcher(txtFarmersTell.getText());

        if(!tellMatcher.matches()) {
//            System.out.println(txtUserName.getText());
            txtFarmersTell.requestFocus();
            txtFarmersTell.setFocusColor(Paint.valueOf("Red"));
            lblFarmerWarning3.setText("invalid!");
        }
    }

    public void farmerEmailOnKeyReleased(KeyEvent keyEvent) {
        lblFarmerWarning4.setText("");
        txtFarmersEmail.setFocusColor(Paint.valueOf("Blue"));

        Pattern emailPattern = Pattern.compile("^([a-z0-9]{2,})([@])([a-z]{2,9})([.])([a-z]{2,})$");
        emailMatcher = emailPattern.matcher(txtFarmersEmail.getText());

        if(!emailMatcher.matches()) {
//            System.out.println(txtUserName.getText());
            txtFarmersEmail.requestFocus();
            txtFarmersEmail.setFocusColor(Paint.valueOf("Red"));
            lblFarmerWarning4.setText("invalid!");
        }
    }


    public void btnFarmersReportOnAction(ActionEvent event) {

        InputStream resource = getClass().getResourceAsStream("/lk/ijse/spiceExportCompany/report/FarmerReport.jrxml");
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(resource);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,null, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint,false);
        } catch (SQLException | ClassNotFoundException | JRException e) {
            throw new RuntimeException(e);

        }
    }
}
