package lk.ijse.spiceExportCompany.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.spiceExportCompany.util.Navigation;

import java.io.IOException;

public class TransportManagmentController {

    public AnchorPane pane;
    public AnchorPane pane1;

    public void btnTransportMOnAction(ActionEvent event) throws IOException {
        setUi("/lk/ijse/spiceExportCompany/view/TransportForm");

    }

    public void btnVehicleMOnAction(ActionEvent event) throws IOException {
        setUi("/lk/ijse/spiceExportCompany/view/VehicleForm");

    }
    public void btnDriverMOnAction(ActionEvent event) throws IOException {
        setUi("/lk/ijse/spiceExportCompany/view/DriverForm");

    }
    public void setUi(String ui) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource(ui+".fxml"));
        pane1.getChildren().clear();
        pane1.getChildren().add(load);
    }

    public void btnBackTrensportManagementOnAction(ActionEvent event) throws IOException {
        Navigation.swatchNavigation("ManagerDashboardForm.fxml",event);

    }



}
