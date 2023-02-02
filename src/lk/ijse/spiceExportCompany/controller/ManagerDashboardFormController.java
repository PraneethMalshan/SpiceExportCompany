package lk.ijse.spiceExportCompany.controller;

import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.spiceExportCompany.util.Navigation;
import lk.ijse.spiceExportCompany.util.Routes;

import java.io.IOException;

public class ManagerDashboardFormController {

    public AnchorPane pane;

    public void btnfarmerManagementOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.FARMERMANAGEMENT, pane);

    }

    public void btnProductManagementOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.PRODUCT, pane);


    }

    public void btnStockManagementOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.STOCK, pane);

    }

    public void btnEmployeesManagementOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.EMPLOYEEMANAGEMENT, pane);

    }

    public void btnTronsportManagementOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.TRANSPORTMANAGEMENT, pane);

    }

    public void btnCustomerManagementOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.CUSTOMERS, pane);
        //

    }

    public void btnOrderManagementOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.PLACEORDERMANAGEMENT, pane);

    }



    public void btnBackMDashFOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.LOGIN, pane);

    }

}
