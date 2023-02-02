package lk.ijse.spiceExportCompany.controller;

import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.spiceExportCompany.util.Navigation;
import lk.ijse.spiceExportCompany.util.Routes;

import java.io.IOException;

public class SalesManagerDashboardFormController {

    public AnchorPane pane;

    public void btnSMCustomerManagementOnAction(ActionEvent actionEvent) {
    }

    public void btnSMOrderManagementOnAction(ActionEvent actionEvent) {
    }

    public void btnBackSaMDashFOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.LOGIN, pane);

    }
}
