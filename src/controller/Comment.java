package controller;

import model.CommentModel;
import java.util.HashMap;

public class Comment implements CommentAbstract{
    private CommentModel commentModel = CommentModel.instance();
    static private Comment instance_  = new Comment();

    static public Comment instance() {
        return instance_;
    }

    private Comment() {
    }

    public void createComment(String content, int BID, int UID){
        HashMap<String, Object> map = new HashMap<>();
        map.put("content", content);
        map.put("BID", BID);
        map.put("UID", UID);
        commentModel.insert(map);
    }

    public void deleteComment(int CID) {
        commentModel.delete(CID);
    }

    public void updateComment(int CID, String content) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("CID", CID);
        map.put("content", content);
        commentModel.update(map);
    }
}
