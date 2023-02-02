package lk.ijse.spiceExportCompany.to;

public class Product {
    private String productCode;
    private String productName;
    private String unitWeight;
    private String productQuantity;
    private String productUnitPrice;

    public Product(String productCode, String productName, double price, int quantity) {
    }

    public Product(String productCode, String productName, String unitWeight, String productQuantity, String productUnitPrice) {
        this.productCode = productCode;
        this.productName = productName;
        this.unitWeight = unitWeight;
        this.productQuantity = productQuantity;
        this.productUnitPrice = productUnitPrice;
    }

    public Product(String productCode, String productName, int unitWeight, int quantity, double price) {
    }

    public Product(String productCode, String productName, int unitWeight, double price, int quantity) {

    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getUnitWeight() {
        return unitWeight;
    }

    public void setUnitWeight(String unitWeight) {
        this.unitWeight = unitWeight;
    }

    public String getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(String productQuantity) {
        this.productQuantity = productQuantity;
    }

    public String getProductUnitPrice() {
        return productUnitPrice;
    }

    public void setProductUnitPrice(String productUnitPrice) {
        this.productUnitPrice = productUnitPrice;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productCode='" + productCode + '\'' +
                ", productName='" + productName + '\'' +
                ", unitWeight='" + unitWeight + '\'' +
                ", productQuantity='" + productQuantity + '\'' +
                ", productUnitPrice='" + productUnitPrice + '\'' +
                '}';
    }
}
