package root.Database;

import root.DataClass.Product;
import root.DataClass.ProductInfo;
import root.DataClass.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DBService {
    private Dbcon dbCon ;


    public DBService(){
        dbCon = new Dbcon();

    }


    //user

    public User getUser(String username, String password) {
        String query="SELECT ID,Name,Password FROM SCOTT.\"User\" WHERE name='"+username+"' and Password='"+password+"'";
        User user = null;
        try {
            ResultSet rs = dbCon.selectQuery(query);
            while (rs.next()) {
                user=new User(rs.getInt("ID"), rs.getString("NAME"), rs.getString("Password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public boolean updatePassword(String name,String password){
        String query="UPDATE SCOTT.\"User\"SET Password='"+password+"' WHERE name='"+name+"'";
        try{
            dbCon.inUpdateDelete(query);
        }
        catch (Exception e){return false;}
        return true;
    }

    //Product
    public boolean insertNewProduct(Product p){
        String query="INSERT  INTO PRODUCT VALUES ("+p.getId()+",'"+p.getName()+"',"+"'"+p.getType()+"','"+p.getVendor()+"')";
        try{
            System.out.print(query);
            dbCon.selectQuery(query);
        }
        catch (Exception e){
            System.out.println("Couldn't update Table");
            return false;
        }
        return true;
    }

    public List<Product> getProducts(){
        List<Product> productList =new ArrayList<>();
        String query="SELECT ID,Name,Type,Vendor FROM PRODUCT";
        try {
            ResultSet rs = dbCon.selectQuery(query);
            while (rs.next()) {
                //rs.bla bla create product
                productList.add(new Product(rs.getInt("ID"),rs.getString("Name"),rs.getString("Type"),rs.getString("Vendor")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productList;
    }


    //productInfo
    public boolean insertNewProductInfo(ProductInfo p){
        String query="INSERT  INTO Inventory VALUES ("+p.getId()+",'"+p.getName()+"',"+p.getQuantity()+",'"+p.getPurchaseDate()+"',"+p.getPrice()+","+p.getInventoryNo()+")";
        System.out.print(query);
        try{
            dbCon.selectQuery(query);
        }
        catch (Exception e){
            System.out.println("Couldn't update Table");
            return false;
        }
        return true;
    }

    public List<ProductInfo> getInvetoryData(){
        List<ProductInfo> productInfoList =new ArrayList<>();
        String query="SELECT ID,Name,Quantity,PurchaseDate,Price,INVENTORYNO FROM Inventory";
        try {
            ResultSet rs = dbCon.selectQuery(query);
            while (rs.next()) {
                //rs.bla bla create product
                productInfoList.add(new ProductInfo(rs.getInt("ID"),rs.getString("Name"),rs.getInt("Quantity"),rs.getDate("PurchaseDate"),rs.getDouble("Price"),rs.getInt("InventoryNo")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productInfoList;
    }
}
