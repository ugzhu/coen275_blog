package backend;

import model.CommentModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CommentController {
    private CommentModel commentModel = CommentModel.instance();
    static private CommentController instance_  = new CommentController();

    static public CommentController instance() {
        return instance_;
    }

    private CommentController() {
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
