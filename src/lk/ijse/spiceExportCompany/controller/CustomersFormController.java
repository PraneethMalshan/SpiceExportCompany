package lk.ijse.spiceExportCompany.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.spiceExportCompany.util.Navigation;
import lk.ijse.spiceExportCompany.util.Routes;

import java.io.IOException;

public class CustomersFormController {

    public AnchorPane pane;
    public AnchorPane pane1;

    public void btnCfCOnAction(ActionEvent event) throws IOException {
        setUi("/lk/ijse/spiceExportCompany/view/CustomerManagementForm");
    }
    public void btnCfOrderOnAction(ActionEvent event) throws IOException {
        setUi("/lk/ijse/spiceExportCompany/view/OrderForm");

    }
    public void btnCfOrderDetailsOnAction(ActionEvent event) throws IOException {
        setUi("/lk/ijse/spiceExportCompany/view/OrderDetails");

    }

    public void setUi(String ui) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource(ui+".fxml"));
        pane.getChildren().clear();
        pane.getChildren().add(load);
    }


    public void btnCustomersForm(ActionEvent event) throws IOException {
        Navigation.swatchNavigation("ManagerDashboardForm.fxml",event);

    }



}
