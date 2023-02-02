package lk.ijse.spiceExportCompany.controller;

import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.spiceExportCompany.util.Navigation;
import lk.ijse.spiceExportCompany.util.Routes;

import java.io.IOException;

public class SupervisorDashboardFormController {

    public AnchorPane pane;

    public void btnSDFarmerManagementOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.FARMERMANAGEMENT, pane);

    }

    public void btnSDEmployeesManagementOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.EMPLOYEEMANAGEMENT, pane);

    }

    public void btnSDStockManagementOnAction(ActionEvent actionEvent) {
    }

    public void btnBackSDashFOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.LOGIN, pane);

    }
}
