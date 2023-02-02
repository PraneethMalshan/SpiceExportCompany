package lk.ijse.spiceExportCompany.view.tm;

public class AttendanceTM {
    private String EmployeeId;
    private String Date;
    private String InTime;
    private String OutTime;

    public AttendanceTM() {
    }

    public AttendanceTM(String employeeId, String date, String inTime, String outTime) {
        EmployeeId = employeeId;
        Date = date;
        InTime = inTime;
        OutTime = outTime;
    }

    public String getEmployeeId() {
        return EmployeeId;
    }

    public void setEmployeeId(String employeeId) {
        EmployeeId = employeeId;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getInTime() {
        return InTime;
    }

    public void setInTime(String inTime) {
        InTime = inTime;
    }

    public String getOutTime() {
        return OutTime;
    }

    public void setOutTime(String outTime) {
        OutTime = outTime;
    }

    @Override
    public String toString() {
        return "AttendanceTM{" +
                "EmployeeId='" + EmployeeId + '\'' +
                ", Date='" + Date + '\'' +
                ", InTime='" + InTime + '\'' +
                ", OutTime='" + OutTime + '\'' +
                '}';
    }

//    public String getEmployeeId() {
//        return EmployeeId;
//    }
//
//    public AttendanceTM(String employeeId) {
//        EmployeeId = employeeId;
//    }
//
//    public AttendanceTM() {
//    }
//
//    public void setEmployeeId(String employeeId) {
//        EmployeeId = employeeId;
//    }
//
//
//
//
//    public AttendanceTM() {
//    }
//
//    public AttendanceTM(String employeeId, String date, String inTime, String outTime) {
//        EmployeeId = employeeId;
//        Date = date;
//        InTime = inTime;
//        OutTime = outTime;
//    }
//
//    public String getEmployeeId() {
//        return EmployeeId;
//    }
//
//    public void setEmployeeId(String employeeId) {
//        EmployeeId = employeeId;
//    }
//
//    public String getDate() {
//        return Date;
//    }
//
//    public void setDate(String date) {
//        Date = date;
//    }
//
//    public String getInTime() {
//        return InTime;
//    }
//
//    public void setInTime(String inTime) {
//        InTime = inTime;
//    }
//
//    public String getOutTime() {
//        return OutTime;
//    }
//
//    public void setOutTime(String outTime) {
//        OutTime = outTime;
//    }
//
//    @Override
//    public String toString() {
//        return "AttendanceTM{" +
//                "EmployeeId='" + EmployeeId + '\'' +
//                ", Date='" + Date + '\'' +
//                ", InTime='" + InTime + '\'' +
//                ", OutTime='" + OutTime + '\'' +
//                '}';
//    }
}
