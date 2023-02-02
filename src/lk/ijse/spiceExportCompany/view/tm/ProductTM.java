package lk.ijse.spiceExportCompany.view.tm;

public class ProductTM {
    private String ProductCode;
    private String ProductName;
    private String UnitWeigh;
    private String ProductUnitPrice;
    private String ProductQuantity;

    public ProductTM() {
    }

    public ProductTM(String productCode, String productName, String unitWeigh, String productUnitPrice, String productQuantity) {
        ProductCode = productCode;
        ProductName = productName;
        UnitWeigh = unitWeigh;
        ProductUnitPrice = productUnitPrice;
        ProductQuantity = productQuantity;
    }

    public String getProductCode() {
        return ProductCode;
    }

    public void setProductCode(String productCode) {
        ProductCode = productCode;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getUnitWeigh() {
        return UnitWeigh;
    }

    public void setUnitWeigh(String unitWeigh) {
        UnitWeigh = unitWeigh;
    }

    public String getProductUnitPrice() {
        return ProductUnitPrice;
    }

    public void setProductUnitPrice(String productUnitPrice) {
        ProductUnitPrice = productUnitPrice;
    }

    public String getProductQuantity() {
        return ProductQuantity;
    }

    public void setProductQuantity(String productQuantity) {
        ProductQuantity = productQuantity;
    }

    @Override
    public String toString() {
        return "ProductTM{" +
                "ProductCode='" + ProductCode + '\'' +
                ", ProductName='" + ProductName + '\'' +
                ", UnitWeigh='" + UnitWeigh + '\'' +
                ", ProductUnitPrice='" + ProductUnitPrice + '\'' +
                ", ProductQuantity='" + ProductQuantity + '\'' +
                '}';
    }
}
