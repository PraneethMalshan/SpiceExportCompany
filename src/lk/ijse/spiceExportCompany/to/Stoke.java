package lk.ijse.spiceExportCompany.to;

public class Stoke {
    private String sId;
    private String goodName;
    private String unitPrice;
    private String suppliedQuantity;

    public Stoke() {
    }

    public Stoke(String sId, String goodName, String unitPrice, String suppliedQuantity) {
        this.sId = sId;
        this.goodName = goodName;
        this.unitPrice = unitPrice;
        this.suppliedQuantity = suppliedQuantity;
    }

    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getSuppliedQuantity() {
        return suppliedQuantity;
    }

    public void setSuppliedQuantity(String suppliedQuantity) {
        this.suppliedQuantity = suppliedQuantity;
    }

    @Override
    public String toString() {
        return "Stoke{" +
                "sId='" + sId + '\'' +
                ", goodName='" + goodName + '\'' +
                ", unitPrice='" + unitPrice + '\'' +
                ", suppliedQuantity='" + suppliedQuantity + '\'' +
                '}';
    }
}
