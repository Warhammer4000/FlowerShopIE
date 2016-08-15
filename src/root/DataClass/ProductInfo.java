package root.DataClass;

import java.util.Date;



public class ProductInfo
{
    private Product product;
    private int Id;
    private String name;
    private int Quantity;
    private Date PurchaseDate;
    private double Price;
    private int inventoryNo;


    public ProductInfo(int id, int quantity, Date purchaseDate, double price,int InventoryNo) {
        Id = id;
        Quantity = quantity;
        PurchaseDate = purchaseDate;
        Price = price;

        inventoryNo=InventoryNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getInventoryNo() {
        return inventoryNo;
    }

    public void setInventoryNo(int inventoryNo) {
        this.inventoryNo = inventoryNo;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public Date getPurchaseDate() {
        return PurchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        PurchaseDate = purchaseDate;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

}
