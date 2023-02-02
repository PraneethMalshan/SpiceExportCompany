package lk.ijse.spiceExportCompany.controller;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.spiceExportCompany.util.Navigation;
import lk.ijse.spiceExportCompany.util.Routes;

import java.io.IOException;

public class MainFormController {
    public AnchorPane pane;

    public void btnLetsGoOnAction(MouseEvent mouseEvent) throws IOException {
        Navigation.navigate(Routes.LOGIN, pane);

    }
}
