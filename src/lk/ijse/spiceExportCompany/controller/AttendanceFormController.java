package lk.ijse.spiceExportCompany.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.spiceExportCompany.db.DBConnection;
import lk.ijse.spiceExportCompany.model.AttendanceModel;
import lk.ijse.spiceExportCompany.model.CustomerModel;
import lk.ijse.spiceExportCompany.model.EmployeeModel;
import lk.ijse.spiceExportCompany.to.Attendance;
import lk.ijse.spiceExportCompany.to.Employee;
import lk.ijse.spiceExportCompany.view.tm.AttendanceTM;
import lk.ijse.spiceExportCompany.view.tm.EmployeeTM;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AttendanceFormController  implements Initializable {
    ObservableList<AttendanceTM> Attendance = FXCollections.observableArrayList();

    //ObservableList<AttendanceTM> Attendance = FXCollections.observableArrayList();

    @FXML
    private AnchorPane pane;
    @FXML
    private JFXComboBox<String> combAttenEmployeeeId;

    @FXML
    private JFXTimePicker txtAttendanceInTime;

    @FXML
    private JFXTimePicker txtAttendanceOutTime;

    @FXML
    private JFXDatePicker txtAttendanceDate;

    @FXML
    private TableView<AttendanceTM> tblAttendance;

    @FXML
    private TableColumn<Attendance, String> colAttendancEId;

    @FXML
    private TableColumn<Attendance, String> colAttendanceDate;

    @FXML
    private TableColumn<Attendance, String> colAttendanceInTime;

    @FXML
    private TableColumn<Attendance, String> colAttendanceOutTime;

//    private String eId;
//    private String date;
//    private String inTime;
//    private String outTime;


    //======================================================Add Combo Box=======================
//    public void initialize(){
//        loadEmployeeId();
//    }

    private void loadEmployeeId() {
//        tblAttendance.getItems();
//            try {
//                ResultSet set=AttendanceModel.getAllData();
//                while (set.next()){
//                    Attendance.add(new AttendanceTM(
//                         set.getString(1)
//                    ));
//                }
//            } catch (SQLException | ClassNotFoundException e) {
//                System.out.println(e);
//            }

        //  System.out.println("Click");
        String sql = "Select eId from employee";
        ObservableList<String> data = FXCollections.observableArrayList();

        try {
            PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);
            ResultSet resultSet = pstm.executeQuery();
            while (resultSet.next()) {
                data.add(resultSet.getString(1));

                //  System.out.println(resultSet.getString(1));

            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
        combAttenEmployeeeId.setItems(data);
    }


    //======================================================Over Combo Box=======================


    @FXML
    void btnAddAttendanceOnAction(ActionEvent event) {
        String eId = combAttenEmployeeeId.getValue();
        String date = txtAttendanceDate.getValue().toString();
        String inTime = txtAttendanceInTime.getValue().toString();
        String outTime = txtAttendanceOutTime.getValue().toString();

        Attendance attendance = new Attendance(eId, date, inTime, outTime);

        try {
            boolean isAdded = AttendanceModel.add(attendance);

            if (isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "Employee Added!").show();
                setTableData();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something Happened!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnAttendanceOnAction(MouseEvent event) {

    }

    @FXML
    void btnDeleteAttendanceOnAction(ActionEvent event) {

    }

    @FXML
    void btnSearchAttendanceMousapointOnAction(MouseEvent event) {

    }

//    private String EmployeeId;
//    private String Date;
//    private String InTime;
//    private String OutTime;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadEmployeeId();
        colAttendancEId.setCellValueFactory(new PropertyValueFactory<>("EmployeeId"));
        colAttendanceDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
        colAttendanceInTime.setCellValueFactory(new PropertyValueFactory<>("InTime"));
        colAttendanceOutTime.setCellValueFactory(new PropertyValueFactory<>("OutTime"));
        setTableData();
    }

    private void setTableData() {
        tblAttendance.getItems().clear();
        try {
            ResultSet set = AttendanceModel.getAllData();
            while (set.next()) {
                Attendance.add(new AttendanceTM(
                        set.getString(1),
                        set.getString(2),
                        set.getString(3),
                        set.getString(4)

                ));
            }
            tblAttendance.setItems(Attendance);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

/*    public void refreshTable(){
        Attendance.clear();
        String SQL = "Select * From attendance";
        try {
            PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(SQL);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                attendances.add(new Attendance(resultSet.getString("sid"), resultSet.getString("Date"),
                        resultSet.getString("int_time"), resultSet.getString("out_time")));
                tbl.setItems(attendances);

                tbl.setItems(attendances);
            }

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }

        tblId.setCellValueFactory(new PropertyValueFactory<>("sid"));
        tblDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
        tblInTime.setCellValueFactory(new PropertyValueFactory<>("int_time"));
        tblOuttime.setCellValueFactory(new PropertyValueFactory<>("out_time"));

    }*/


}














//    @FXML
//    private JFXComboBox <String>combAttenEmployeeId;
//
//    @FXML
//    private JFXTimePicker txtAttendanceInTime;
//
//    @FXML
//    private JFXTimePicker txtAttendanceOutTime;
//
//    @FXML
//    private JFXDatePicker txtAttendanceDate;
//
//    @FXML
//    private TableView<AttendanceTM> tblAttendance;
//
//    @FXML
//    private TableColumn<Attendance, String > colAttendancEId;
//
//    @FXML
//    private TableColumn<Attendance, String> colAttendanceDate;
//
//    @FXML
//    private TableColumn<Attendance, String> colAttendanceInTime;
//    @FXML
//    private TableColumn<Attendance, String> colAttendanceOutTime;
//
//
//
//    public void initialize(){
//        loadEmployeeId();
//    }
//
//    public void loadEmployeeId(){
//
//            String sql = "Select eId from attendance";
//            try {
//                PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sql);
//                ResultSet resultSet = statement.executeQuery();
//                ObservableList<String> data = FXCollections.observableArrayList();
//                while (resultSet.next()){
//                    data.add(resultSet.getString(1));
//                    combAttenEmployeeId.setItems(data);
//                }
//
//            } catch (SQLException | ClassNotFoundException e) {
//                System.out.println(e);
//            }
//
//
//
////        String sql = "Select eId from employee";
////        try {
////            PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sql);
////            ResultSet resultSet = statement.executeQuery();
////            ObservableList<String> data = FXCollections.observableArrayList();
////            while (resultSet.next()){
////                data.add(resultSet.getString(1));
////                combAttenEmployeeId.setItems(data);
////            }
////
////        } catch (SQLException | ClassNotFoundException e) {
////            System.out.println(e);
////        }
//    }
//
//    @FXML
//    void btnAddAttendanceOnAction(ActionEvent event) {
//        String eId = combAttenEmployeeId.getValue();
//        String date = txtAttendanceDate.getValue().toString();
//        String inTime = txtAttendanceInTime.getValue().toString();
//        String outTime = txtAttendanceOutTime.getValue().toString();
//
//        Attendance attendance = new Attendance(eId,date,inTime,outTime);
//
//        try {
//            boolean isAdded = AttendanceModel.add(attendance);
//
//            if (isAdded) {
//                new Alert(Alert.AlertType.CONFIRMATION, "Attendance Added!").show();
//                setTableData();
//            } else {
//                new Alert(Alert.AlertType.WARNING, "Something Happened!").show();
//            }
//        } catch (SQLException | ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//
//
//
//    @FXML
//    void btnDeleteAttendanceOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
//        if (AttendanceModel.delete(combAttenEmployeeId.getValue())) {
//            new Alert(Alert.AlertType.CONFIRMATION, "Data Deleted!").show();
//            setTableData();
//           // clearTextField();
//        } else {
//            new Alert(Alert.AlertType.CONFIRMATION, "No data").show();
//        }
//    }
//
//
//
//    @FXML
//    void btnAttendanceOnAction(MouseEvent event) {
//    }
//    @FXML
//    void btnSearchAttendanceMousapointOnAction(MouseEvent event) {
//    }
//
//
//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
////        private String eId;
////        private String date;
////        private String inTime;
////        private String outTime;
//        colAttendancEId.setCellValueFactory(new PropertyValueFactory<>("Employeeid"));
//        colAttendanceDate.setCellValueFactory(new PropertyValueFactory<>("date"));
//        colAttendanceInTime.setCellValueFactory(new PropertyValueFactory<>("inTime"));
//        colAttendanceOutTime.setCellValueFactory(new PropertyValueFactory<>("outTime"));
//        setTableData();
//    }
//
//    private void setTableData() {
//        tblAttendance.getItems().clear();
//        try {
//            ResultSet set = AttendanceModel.getAllData();
//            while (set.next()) {
//                Attendance.add(new AttendanceTM(
//                        set.getString(1),
//                        set.getString(2),
//                        set.getString(3),
//                        set.getString(4)
//
//                ));
//            }
//            tblAttendance.setItems(Attendance);
//        } catch (SQLException | ClassNotFoundException throwables) {
//            throwables.printStackTrace();
//        }
//    }

//}
