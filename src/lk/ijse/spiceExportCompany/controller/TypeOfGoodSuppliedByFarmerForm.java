package lk.ijse.spiceExportCompany.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.spiceExportCompany.model.EmployeeModel;
import lk.ijse.spiceExportCompany.to.Employee;
import lk.ijse.spiceExportCompany.util.Navigation;
import lk.ijse.spiceExportCompany.util.Routes;

import java.io.IOException;
import java.sql.SQLException;

public class TypeOfGoodSuppliedByFarmerForm {

    public AnchorPane pane;
    @FXML
    private TextField txtTogId;

    @FXML
    private TextField txtGoodName;

    @FXML
    private TextField txtSuppliedQuantity;

    @FXML
    private TextField txtUnitPrice;

    @FXML
    private TableView<?> tblTypesOfGoodsSuppliedByFarmer;

    @FXML
    private TableColumn<?, ?> colTOGid;

    @FXML
    private TableColumn<?, ?> colGoodName;

    @FXML
    private TableColumn<?, ?> colSuppliedQuantity;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private TextField txtSearchFarmerGoodM;

    @FXML
    void btnAddGoodSupply(ActionEvent event) {

    }

    @FXML
    void btnDeleteGoodSupply(ActionEvent event) {

    }

    @FXML
    void btnSearchFarmerGoodOnActionM(MouseEvent event) {

    }

    @FXML
    void btnUpdateGoodSupply(ActionEvent event) {

    }

    @FXML
    void txtSearchFarmerGoodOnActionM(ActionEvent event) {

    }

    @FXML
    void txtTogIdOnAction(ActionEvent event) {

    }







    public void btnTypesOfGoodsSuppliedByFarmerOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.FARMERMANAGEMENT, pane);

    }
}
