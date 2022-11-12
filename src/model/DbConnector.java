package model;
import java.sql.*;

public class DbConnector {
    private Connection conn;

    // Singleton
    static private DbConnector instance_  = new DbConnector();
    static public DbConnector instance() { return instance_; }

    private DbConnector(){
        this.conn = null;
        getConnection();
    }

    public Connection getConnection() {

        try {
            String url = "jdbc:sqlite:db/db.sqlite3";
            this.conn = DriverManager.getConnection(url);
            return this.conn;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public void closeConnection(){

        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public int executeUpdate(String sql){

        try {
            Statement stmt = conn.createStatement();
            return stmt.executeUpdate(sql);
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

        return -1;
    }

    public ResultSet executeQuery(String sql){
        try {
            Statement stmt = conn.createStatement();
            return stmt.executeQuery(sql);
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

        return null;
    }

}






