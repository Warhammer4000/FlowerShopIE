package root.Database;

import java.sql.*;


class Dbcon {
    private Connection con;
    private  Statement stmt;
    private  ResultSet result;
    private boolean connectionCheck=true;
    Dbcon() {
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

    ResultSet  selectQuery (String sql) throws SQLException{
        try{
            result = stmt.executeQuery(sql);
        }catch (Exception e){
         System.out.println(e.getMessage());
        }
        return   result;
    }
}
