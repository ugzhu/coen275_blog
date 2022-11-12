package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BlogModel implements Model{
    private final DbConnector db;

    // Singleton
    static private BlogModel instance_  = new BlogModel();
    static public BlogModel instance() { return instance_; }
    private BlogModel(){
        db = DbConnector.instance();
    }

    public List<HashMap<String, Object>> getAll() {
        String sql = "SELECT * FROM Blog;";
        System.out.println(sql);
        List<HashMap<String, Object>> resList = new ArrayList<>();
        HashMap<String, Object> resMap = new HashMap<>();

        try{
            ResultSet res = db.executeQuery(sql);
            while (res.next()) {
                resMap.put("BID", res.getInt("BID"));
                resMap.put("title", res.getString("title"));
                resMap.put("content", res.getString("content"));
                resMap.put("UID", res.getInt("UID"));
                resList.add(deepCopy(resMap));
                resMap.clear();
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return resList;
    }

    public void insert(HashMap<String, Object> params) {
        String sql = "INSERT INTO Blog (title, content, UID)\n" +
                "VALUES ('" +
                params.get("title") + "', '" +
                params.get("content") + "', " +
                params.get("UID") +
                ");";
        System.out.println(sql);
        db.executeUpdate(sql);
    }

    public List<HashMap<String, Object>> getWithUid(int UID) {
        HashMap<String, Object> resMap = new HashMap<>();
        List<HashMap<String, Object>> resList = new ArrayList<>();
        String sql = "SELECT * FROM Blog WHERE UID=" + UID + ";";
        System.out.println(sql);

        try{

            ResultSet res = db.executeQuery(sql);
            while (res.next()) {
                if (res.getInt("UID") != UID){
                    return null;
                }
                resMap.put("UID", res.getInt("UID"));
                resMap.put("content", res.getString("content"));
                resMap.put("title", res.getString("title"));
                resMap.put("BID", res.getInt("BID"));
                resList.add(deepCopy(resMap));
                resMap.clear();
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return resList;
    }

    // *******
    public List<HashMap<String, Object>> getWithBid(int BID) {
        HashMap<String, Object> resMap = new HashMap<>();
        List<HashMap<String, Object>> resList = new ArrayList<>();
        String sql = "SELECT * FROM Blog WHERE BID=" + BID + ";";
        System.out.println(sql);

        try {
            ResultSet res = db.executeQuery(sql);
            if (res.getInt("BID") != BID) {
                return null;
            }
            resMap.put("UID", res.getInt("UID"));
            resMap.put("content", res.getString("content"));
            resMap.put("title", res.getString("title"));
            resMap.put("BID", res.getInt("BID"));
            resList.add(deepCopy(resMap));
            resMap.clear();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return resList;
    }

    public void delete(int BID){
        String sql = "DELETE FROM Blog WHERE BID=" + BID + ";";
        System.out.println(sql);
        db.executeUpdate(sql);
    }

    public void update(HashMap<String, Object> params) {
        String sql = "UPDATE Blog\n" +
                "SET title = '" + params.get("title") +
                "', content= '" + params.get("content") +
                "'\nWHERE BID = "+ params.get("BID")+";";
        System.out.println(sql);
        db.executeUpdate(sql);
    }

}
