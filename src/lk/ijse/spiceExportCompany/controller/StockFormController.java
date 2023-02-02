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
import lk.ijse.spiceExportCompany.db.DBConnection;
import lk.ijse.spiceExportCompany.model.EmployeeModel;
import lk.ijse.spiceExportCompany.model.StokeModel;
import lk.ijse.spiceExportCompany.to.Employee;
import lk.ijse.spiceExportCompany.to.Stoke;
import lk.ijse.spiceExportCompany.util.Navigation;
import lk.ijse.spiceExportCompany.util.Routes;
import lk.ijse.spiceExportCompany.view.tm.EmployeeTM;
import lk.ijse.spiceExportCompany.view.tm.StokeTM;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class StockFormController implements Initializable {
    //public TableColumn colGrsdeNumber;
    ObservableList<StokeTM> Stoke = FXCollections.observableArrayList();

    public AnchorPane pane;
    @FXML
    public TextField txtGoodName;
    @FXML
    private TextField txtGradeNumber;

    @FXML
    private TextField txtQty;

    @FXML
    private TextField txtUnitPriceOfBuy;

    @FXML
    private TableView<StokeTM> tblStoke;

    @FXML
    private TableColumn<Stoke, String> colGradeNumber;

    @FXML
    private TableColumn<Stoke, String> colName;

    @FXML
    private TableColumn<Stoke, String> colQuantity;

    @FXML
    private TableColumn<Stoke, String> colUnitPriceOfBuy;

    @FXML
    private TextField txtSearchM;

    @FXML
    void btnAddOnAction(ActionEvent event) {
        String sId = txtGradeNumber.getText();
        String goodName = txtGoodName.getText();
        String unitPrice = txtUnitPriceOfBuy.getText();
        String suppliedQuantity = txtQty.getText();


        Stoke stoke = new Stoke(sId, goodName, unitPrice, suppliedQuantity);

        try {
            boolean isAdded = StokeModel.add(stoke);

            if (isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "Stoke Added!").show();
                setTableData();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something Happened!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void txtGradeNumberOnActionSearch(ActionEvent event) {
        String sId = txtGradeNumber.getText();

        try {
            Stoke stoke = StokeModel.search(sId);
            if (stoke != null) {
                fillData(stoke);
            }else {
                new Alert(Alert.AlertType.WARNING, "Something Happened!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnSearchOnActionM(MouseEvent event) {
        String sId = txtSearchM.getText();

        try {
            Stoke stoke = StokeModel.search(sId);
            if (stoke != null) {
                fillData(stoke);
            }else {
                new Alert(Alert.AlertType.WARNING, "Something Happened!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void txtSearchMOnAction(ActionEvent event) {
    }

    private void fillData(Stoke stoke) {
        txtGradeNumber.setText(stoke.getsId());
        txtGoodName.setText(stoke.getGoodName());
        txtQty.setText(stoke.getSuppliedQuantity());
        txtUnitPriceOfBuy.setText(stoke.getUnitPrice());
    }


    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        System.out.println("click");
        try {
            if (StokeModel.stokeUpdate(
                    new Stoke(txtGradeNumber.getText(),
                            txtGoodName.getText(),
                            txtUnitPriceOfBuy.getText(),
                            txtQty.getText())
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




    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        if (StokeModel.delete(txtGradeNumber.getText())) {
            new Alert(Alert.AlertType.CONFIRMATION, "Data Deleted!").show();
            setTableData();
            clearTextField();
        } else {
            new Alert(Alert.AlertType.CONFIRMATION, "No data").show();
        }
    }
    private void clearTextField() {
        txtGradeNumber.setText("");
        txtGoodName.setText("");
        txtUnitPriceOfBuy.setText("");
        txtQty.setText("");
    }

//    private String GradeNumber;
//    private String Name;
//    private String Quantity;
//    private String UnitPriceOfBuy;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colGradeNumber.setCellValueFactory(new PropertyValueFactory<>("GradeNumber"));
        colName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colUnitPriceOfBuy.setCellValueFactory(new PropertyValueFactory<>("UnitPriceOfBuy"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
        setTableData();
    }

    private void setTableData() {
        tblStoke.getItems().clear();
        try {
            ResultSet set = StokeModel.getAllData();
            while (set.next()) {
                Stoke.add(new StokeTM(
                        set.getString(1),
                        set.getString(2),
                        set.getString(3),
                        set.getString(4)

                ));
            }
            tblStoke.setItems(Stoke);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }


    public void btnBackStockOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.MANAGERDASHBOARD, pane);
    }


    public void btnStockReportOnAction(ActionEvent event) {
        InputStream resource = getClass().getResourceAsStream("/lk/ijse/spiceExportCompany/report/StockReport.jrxml");
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(resource);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,null, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint,false);
        } catch (SQLException | ClassNotFoundException | JRException e) {
            throw new RuntimeException(e);

        }
    }
}
