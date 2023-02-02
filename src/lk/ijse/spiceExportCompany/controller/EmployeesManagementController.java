package lk.ijse.spiceExportCompany.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import lk.ijse.spiceExportCompany.util.Navigation;
import lk.ijse.spiceExportCompany.util.Routes;

import java.io.IOException;

public class EmployeesManagementController {


    public AnchorPane pane;

    public void btnEmployeesOnAction(ActionEvent actionEvent) throws IOException {
        //Navigation.navigate(Routes.EMPLOYEEMANAGEMENT, pane1);
        setUi("/lk/ijse/spiceExportCompany/view/EmployeeForm");


    }

    public void btnAttendanceOnAction(ActionEvent actionEvent) throws IOException {
        setUi("/lk/ijse/spiceExportCompany/view/AttendanceForm");
    }

    public void btnSalaryOnAction(ActionEvent actionEvent) throws IOException {
        setUi("/lk/ijse/spiceExportCompany/view/SalaryForm");

    }
    public void btnBackEmpManageOnAction(ActionEvent event) throws IOException {
     Navigation.swatchNavigation("ManagerDashboardForm.fxml",event);

    }
    public void setUi(String ui) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource(ui+".fxml"));
        pane.getChildren().clear();
        pane.getChildren().add(load);
    }




}
