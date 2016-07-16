package root.DataClass;

/**
 * Created by Fahmida Milee on 15/07/2016.
 */

public class Product
{
    private int productId;
    private String productName;
    private int productQuantity;
    private String pPurchaseDate;
    private double productPrice;
    private String productVendor;

    public Product ()
    {
        this.productId = 0;
        this.productName = "no name";
        this.productQuantity = 0;
        this.pPurchaseDate = "no date";
        this.productPrice = 0;
        this.productVendor = "no vendor";
    }

    public Product (int pid, String pname, int pquantity, String ppdate, double pprice, String pvendor)
    {
        this.productId = pid;
        this.productName = pname;
        this.productQuantity = pquantity;
        this.pPurchaseDate = ppdate;
        this.productPrice = pprice;
        this.productVendor = pvendor;
    }

    public void showinfo()
    {
        System.out.println("Product ID: " +productId);
        System.out.println("Product Name: " +productName);
        System.out.println("Product Quantity: " +productQuantity);
        System.out.println("Purchase Date: " +pPurchaseDate);
        System.out.println("Product Price: " +productPrice);
        System.out.println("product Ventor: " +productVendor);
    }

    public void setproductId(int pid)
    {
        this.productId = pid;
    }

    public int getproductId()
    {
        return productId;
    }

    public void setproductName(String pname)
    {
        productName = pname;
    }

    public String getproductName()
    {
        return productName;
    }

    public void setproductQuantity(int pquantity)
    {
        this.productQuantity = pquantity;
    }

    public int getproductQuantity()
    {
        return productQuantity;
    }

    public void setpPurchaseDate(String ppdate)
    {
        this.pPurchaseDate=ppdate;
    }

    public String getpPurchaseDate()
    {
        return pPurchaseDate;
    }

    public void setproductPrice()
    {
        productPrice = pprice;
    }

    public double getproductPrice()
    {
        return productPrice;
    }

    public void setproductVentor(String pvendor)
    {
        productVendor=pvendor;
    }

    public String getproductVendor()
    {
        return productVendor;
    }
}
