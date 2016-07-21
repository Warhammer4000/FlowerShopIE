package root.Database;

import root.DataClass.Product;
import root.DataClass.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DBService {
    private DBcon dbCon ;


    public DBService(){
        dbCon = new DBcon();

    }

    private boolean dbConnectionCheck() {
        return dbCon.connectionCheck();
    }

    //drops user Table when run
    /*public boolean tableDrop(){
        String query = "DROP table User";
        try{
            dbCon.inUpdateDelete(query);
        }
        catch (Exception e){return false;}
        return true;
    }*/

    public boolean updatePassword(String name,String password){
        String query="UPDATE SCOTT.\"User\"SET Password='"+password+"' WHERE name='"+name+"'";
        try{
            dbCon.inUpdateDelete(query);
        }
        catch (Exception e){return false;}
        return true;
    }

    public List<Product> getTableData(String tableName){
        List<Product> productList=new ArrayList<>();
        String query="SELECT ID,Name,Quantity,PurchaseDate,Price,Vendor FROM "+tableName+"";
        System.out.print(query);
        try {
            ResultSet rs = dbCon.selectQuery(query);
            while (rs.next()) {
                //rs.bla bla create product
                productList.add(new Product(rs.getInt("ID"),rs.getString("Name"),rs.getInt("Quantity"),rs.getDate("PurchaseDate"),rs.getDouble("Price"),rs.getString("Vendor")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productList;
    }

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

    public boolean insertNewProduct(String tableName, Product p){
        String query="INSERT  INTO "+tableName+" VALUES ("+p.getId()+",'"+p.getName()+"',"+p.getQuantity()+",'"+p.getPurchaseDate()+"',"+p.getPrice()+",'"+p.getVendor()+"');";
        try{
            System.out.print(query);
            dbCon.selectQuery(query);


        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("Couldn't update Table");
            return false;
        }
        return true;
    }
}
