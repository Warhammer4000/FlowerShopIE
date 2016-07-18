package root.Database;

import java.sql.*;

/**
 * Created by tazim on 7/18/2016.
 */
public class DBcon {
    private  String sqlQuery;
    private Connection con;
    private  Statement stmt;
    private  ResultSet result;
    private boolean connectionCheck=true;
    DBcon() {
        try {
            //step1 load the driver class
            Class.forName("oracle.jdbc.driver.OracleDriver");
        }catch(ClassNotFoundException ignored) {
            System.out.println("Connector not found");
        }
        try{

            //step2 create  the connection object
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "scott", "tiger");

            //step3 create the statement object
            stmt = con.createStatement();

        } catch (Exception ex) {
            connectionCheck = false;
            System.out.println("Connection Failed");
        }

    }



    boolean connectionCheck(){
        return connectionCheck;
    }

    int inUpdateDelete(String sql) throws SQLException {
        return stmt.executeUpdate(sql);
    }

    ResultSet selectQuery(String sql){
        try{
            result = stmt.executeQuery(sql);
            System.out.print(sql);
        }catch (Exception e){
            System.out.print("Error");
            e.printStackTrace();
        }
        return   result;
    }
}
