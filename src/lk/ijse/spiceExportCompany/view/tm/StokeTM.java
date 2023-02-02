package lk.ijse.spiceExportCompany.view.tm;

public class StokeTM {
    private String GradeNumber;
    private String Name;
    private String Quantity;
    private String UnitPriceOfBuy;

    public StokeTM() {
    }

    public StokeTM(String gradeNumber, String name, String quantity, String unitPriceOfBuy) {
        GradeNumber = gradeNumber;
        Name = name;
        Quantity = quantity;
        UnitPriceOfBuy = unitPriceOfBuy;
    }

    public String getGradeNumber() {
        return GradeNumber;
    }

    public void setGradeNumber(String gradeNumber) {
        GradeNumber = gradeNumber;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public String getUnitPriceOfBuy() {
        return UnitPriceOfBuy;
    }

    public void setUnitPriceOfBuy(String unitPriceOfBuy) {
        UnitPriceOfBuy = unitPriceOfBuy;
    }

    @Override
    public String toString() {
        return "StokeTM{" +
                "GradeNumber='" + GradeNumber + '\'' +
                ", Name='" + Name + '\'' +
                ", Quantity='" + Quantity + '\'' +
                ", UnitPriceOfBuy='" + UnitPriceOfBuy + '\'' +
                '}';
    }
}
