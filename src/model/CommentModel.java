package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CommentModel implements Model{
    private final DbConnector db;

    // Singleton
    static private CommentModel instance_  = new CommentModel();
    static public CommentModel instance() { return instance_; }
    private CommentModel(){
        db = DbConnector.instance();
    }

    public List<HashMap<String, Object>> getAll() {
        String sql = "SELECT * FROM Comment;";
        List<HashMap<String, Object>> resList = new ArrayList<>();
        HashMap<String, Object> resMap = new HashMap<>();
        System.out.println(sql);

        try{
            ResultSet res = db.executeQuery(sql);
            while (res.next()) {
                resMap.put("CID", res.getInt("CID"));
                resMap.put("content", res.getString("content"));
                resMap.put("BID", res.getInt("BID"));
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
        String sql = "INSERT INTO Comment (content, BID, UID)\n" +
                "VALUES ('" +
                params.get("content") + "', '" +
                params.get("BID") + "', " +
                params.get("UID") +
                ");";
        System.out.println(sql);
        db.executeUpdate(sql);
    }

    public List<HashMap<String, Object>> getWithBid(int BID) {
        HashMap<String, Object> resMap = new HashMap<>();
        List<HashMap<String, Object>> resList = new ArrayList<>();
        String sql = "SELECT * FROM Comment WHERE BID=" + BID + ";";
        System.out.println(sql);

        try{
            ResultSet res = db.executeQuery(sql);

            while (res.next()) {
                if (res.getInt("BID") != BID){
                    return null;
                }

                resMap.put("UID", res.getInt("UID"));
                resMap.put("content", res.getString("content"));
                resMap.put("CID", res.getInt("CID"));
                resMap.put("BID", res.getInt("BID"));
                resList.add(deepCopy(resMap));
                resMap.clear();
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return resList;
    }

    public void delete(int CID) {
        String sql = "DELETE FROM Comment WHERE CID=" + CID + ";";
        System.out.println(sql);
        db.executeUpdate(sql);
    }

    public void update(HashMap<String, Object> params) {
        String sql = "UPDATE Comment\n" +
                "SET content= '" + params.get("content") +
                "'\nWHERE CID = "+ params.get("CID")+";";
        System.out.println(sql);
        db.executeUpdate(sql);
    }



    // non-applicable class from interface, do not call
    public List<HashMap<String, Object>> getWithUid(int UID) {
        return null;
    }
    public List<HashMap<String, Object>> getWithUsername(String username) {
        return null;
    }
}