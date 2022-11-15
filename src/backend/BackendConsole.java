package backend;

import java.util.ArrayList;
import java.util.List;

public class BackendConsole {
    public User user;
    public Blog blog;
    public List<Comment> comments;

    static private BackendConsole instance_  = new BackendConsole();

    static public BackendConsole instance() {
        return instance_;
    }

    private BackendConsole() {
        user = User.instance();
        blog = Blog.instance();
        comments = new ArrayList<>();
    }

}