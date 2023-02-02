package lk.ijse.spiceExportCompany.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import lk.ijse.spiceExportCompany.util.Navigation;
import lk.ijse.spiceExportCompany.util.Routes;
import sun.security.util.Password;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginFormController {
    public JFXTextField txtUserName;
    public JFXTextField txtPassword;
    public AnchorPane pane;
    public JFXButton btnLogin;
    public Label lblLoginUserName;
    public Label lblLoginPassword;

    private Matcher userNameMatcher;
    private Matcher passwordMatcher;

    private void setPatterns() {

        Pattern userNamePattern = Pattern.compile("^[a-zA-Z0-9]{4,}$");
        userNameMatcher = userNamePattern.matcher(txtUserName.getText());

        Pattern passwordPattern = Pattern.compile("^[a-zA-Z0-9]{4}$");
        passwordMatcher = passwordPattern.matcher(txtPassword.getText());

    }



        public void btnLoginOnAction(ActionEvent event) throws IOException {
        //



        if (txtUserName.getText().equals("Manager") && txtPassword.getText().equals("1234")) {
                Parent root = FXMLLoader.load(getClass().getResource("/lk/ijse/spiceExportCompany/view/ManagerDashboardForm.fxml"));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            }

          /*  else if (txtUserName.getText().equals("Supervisor") && txtPassword.getText().equals("12345")) {
            Parent root = FXMLLoader.load(getClass().getResource("/lk/ijse/spiceExportCompany/view/SupervisorDashboardForm.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }else if (txtUserName.getText().equals("Sales") && txtPassword.getText().equals("123456")) {
                Parent root = FXMLLoader.load(getClass().getResource("/lk/ijse/spiceExportCompany/view/SalesManagerDashboardForm.fxml"));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }*/
            else {
                new Alert(Alert.AlertType.WARNING, "Invalid User name or Password").show();
               // System.out.println("Invalid Username or Password ! Please check and Log again");
            }


        //
     //   Navigation.navigate(Routes.MANAGERDASHBOARD, pane);

    }

    public void btnBackLoginFOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.MAIN, pane);
    }

    public void txtUserNamedOnaction(ActionEvent event) {
       // txtUserName.fire();
    }
   public void txtUserNameOnaction(ActionEvent event) {
     txtPassword.requestFocus();
   }
    public void txtPasswordOnaction(ActionEvent event) {
        btnLogin.fire();
    }


    public void initialize() {
        setPatterns();
    }

    public void performPattern(){
        if(userNameMatcher.matches()) {
            if(passwordMatcher.matches()) {
                            System.out.println("register action now perform");
                        }  else {
                txtPassword.requestFocus();
                lblLoginPassword.setText("Invalid");
                txtPassword.setFocusColor(Paint.valueOf("Red"));
            }
        } else {
            txtUserName.requestFocus();
            lblLoginUserName.setText("Invalid");
            txtUserName.setFocusColor(Paint.valueOf("Red"));

        }
    }


    public void UserNameIdOnKeyReleased(KeyEvent keyEvent) {
        lblLoginUserName.setText("");
        txtUserName.setFocusColor(Paint.valueOf("Blue"));

        Pattern userNamePattern = Pattern.compile("^[a-zA-Z0-9]{4,}$");
        userNameMatcher = userNamePattern.matcher(txtUserName.getText());

        if(!userNameMatcher.matches()) {
//            System.out.println(txtUserName.getText());
            txtUserName.requestFocus();
            txtUserName.setFocusColor(Paint.valueOf("Red"));
            lblLoginUserName.setText("invalid!");
        }
    }



    public void UserPasswordOnKeyReleased(KeyEvent keyEvent) {
        lblLoginPassword.setText("");
        txtPassword.setFocusColor(Paint.valueOf("Blue"));

        Pattern passwordPattern = Pattern.compile("^[a-zA-Z0-9]{4}$");
        passwordMatcher = passwordPattern.matcher(txtPassword.getText());

        if(!passwordMatcher.matches()) {
//            System.out.println(txtUserName.getText());
            txtPassword.requestFocus();
            txtPassword.setFocusColor(Paint.valueOf("Red"));
            lblLoginPassword.setText("invalid!");
        }
    }
}

