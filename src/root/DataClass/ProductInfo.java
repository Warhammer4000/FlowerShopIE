package root.DataClass;

import java.util.Date;

/**
 * Created by Fahmida Milee on 15/07/2016.
 */

public class ProductInfo
{
    private int Id;
    private String Name;
    private int Quantity;
    private Date PurchaseDate;
    private double Price;
    private String Vendor;


    public ProductInfo(int id, String name, int quantity, Date purchaseDate, double price, String vendor) {
        Id = id;
        Name = name;
        Quantity = quantity;
        PurchaseDate = purchaseDate;
        Price = price;
        Vendor = vendor;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
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

    public String getVendor() {
        return Vendor;
    }

    public void setVendor(String vendor) {
        Vendor = vendor;
    }
}
