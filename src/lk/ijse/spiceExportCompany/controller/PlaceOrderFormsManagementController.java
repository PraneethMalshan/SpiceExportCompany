package lk.ijse.spiceExportCompany.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.spiceExportCompany.util.Navigation;
import lk.ijse.spiceExportCompany.util.Routes;

import java.io.IOException;

public class PlaceOrderFormsManagementController {
    @FXML
    private AnchorPane pane;

    @FXML
    private AnchorPane pane1;

    @FXML
    void btnCustomerPlaceOrderFormBackOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.MANAGERDASHBOARD, pane);

    }

    @FXML
    void btnCustomerPlaceOrderFormOnAction(ActionEvent event) throws IOException {
        setUi("/lk/ijse/spiceExportCompany/view/CustomerPlaceOrderForm");

    }
    public void setUi(String ui) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource(ui+".fxml"));
        pane1.getChildren().clear();
        pane1.getChildren().add(load);
    }

}
