package root.Database;

import root.DataClass.User;

import java.sql.ResultSet;
import java.sql.SQLException;


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
}
