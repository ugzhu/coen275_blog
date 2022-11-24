package controller;

import java.util.ArrayList;
import java.util.List;

public class BackendConsole {
    public User user;
    public Blog blog;
    public Comment comment;
    public List<CommentList> commentLists;

    static private BackendConsole instance_  = new BackendConsole();

    static public BackendConsole instance() {
        return instance_;
    }

    private BackendConsole() {
        user = User.instance();
        blog = Blog.instance();
        comment = Comment.instance();
        commentLists = new ArrayList<>();
    }

}
