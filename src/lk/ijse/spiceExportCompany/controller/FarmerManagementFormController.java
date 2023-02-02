package lk.ijse.spiceExportCompany.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import lk.ijse.spiceExportCompany.util.Navigation;
import lk.ijse.spiceExportCompany.util.Routes;

import java.io.IOException;

public class FarmerManagementFormController {

    public AnchorPane pane;
    public AnchorPane pane1;

    public void btnFarmersOnAction(ActionEvent event) throws IOException {
       // Navigation.navigate(Routes.FARMERS, pane);
        setUi("/lk/ijse/spiceExportCompany/view/FarmersForm");


    }

    public void btnSuppliedDetailsOnAction(ActionEvent event) {
    }

    public void btnFarmerPaymentOnAction(ActionEvent event) throws IOException {
       // Navigation.navigate(Routes.FARMERSPAMENTS, pane);
        setUi("/lk/ijse/spiceExportCompany/view/FarmerPaymerntsForm");


    }

    public void btnTypesofgoodssuppliedbyfarmerOnAction(ActionEvent event) throws IOException {
      //  Navigation.navigate(Routes.TYPESOFGOODSSUPPLIEDBYFARMER, pane);
        setUi("/lk/ijse/spiceExportCompany/view/TypeOfGoodSuppliedByFarmerForm");


    }

    public void btnFarmerManagementBackOnAction(ActionEvent event) throws IOException {
     Navigation.navigate(Routes.MANAGERDASHBOARD, pane);

    }

    public void setUi(String ui) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource(ui+".fxml"));
        pane1.getChildren().clear();
        pane1.getChildren().add(load);
    }
}
