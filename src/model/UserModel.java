package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserModel implements Model {
    private final DbConnector db;

    // Singleton
    static private UserModel instance_  = new UserModel();
    static public UserModel instance() { return instance_; }
    private UserModel(){
        db = DbConnector.instance();
    } 

    public List<HashMap<String, Object>> getAll(){
        String sql = "SELECT * FROM User;";
        System.out.println(sql);
        List<HashMap<String, Object>> resList = new ArrayList<>();
        HashMap<String, Object> resMap = new HashMap<>();

        try{
            ResultSet res = db.executeQuery(sql);
            while (res.next()) {
                resMap.put("UID", res.getInt("UID"));
                resMap.put("password", res.getString("password"));
                resMap.put("username", res.getString("username"));
                resList.add(deepCopy(resMap));
                resMap.clear();
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return resList;
    }

    public void insert(HashMap<String, Object> params){
        String sql = "INSERT INTO User (username, password)\n" +
                "VALUES ('" + params.get("username") + "', '" +
                params.get("password") + "');";
        System.out.println(sql);
        db.executeUpdate(sql);
    }


    public List<HashMap<String, Object>> getWithUid(int UID){
        HashMap<String, Object> resMap = new HashMap<>();
        List<HashMap<String, Object>> resList = new ArrayList<>();
        String sql = "SELECT * FROM User WHERE UID=" + UID + ";";
        System.out.println(sql);

        try{
            ResultSet res = db.executeQuery(sql);
            if (res.getInt("UID") != UID){
                return null;
            }
            resMap.put("UID", res.getInt("UID"));
            resMap.put("password", res.getString("password"));
            resMap.put("username", res.getString("username"));
            resList.add(deepCopy(resMap));
            resMap.clear();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return resList;
    }

    public List<HashMap<String, Object>> getWithUsername(String username) {
        HashMap<String, Object> resMap = new HashMap<>();
        List<HashMap<String, Object>> resList = new ArrayList<>();

        String sql = "SELECT * FROM User WHERE username='" + username + "';";
        System.out.println(sql);

        try{
            ResultSet res = db.executeQuery(sql);
            if (res.getString("username") == null){
                return null;
            }
            resMap.put("UID", res.getInt("UID"));
            resMap.put("password", res.getString("password"));
            resMap.put("username", res.getString("username"));
            resList.add(deepCopy(resMap));
            resMap.clear();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return resList;
    }

    // non-applicable class from interface, do not call
    public List<HashMap<String, Object>> getWithBid(int BID) {
        return null;
    }

    public void delete(int ID) {
    }

    public void update(HashMap<String, Object> params) {
    }
}
