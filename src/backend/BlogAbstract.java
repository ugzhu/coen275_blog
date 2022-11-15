package backend;

import model.BlogModel;
import model.CommentModel;
import model.Model;
import model.UserModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class BlogAbstract {
	private static Blog instance;
	private int blogID;
	List<HashMap<String, Object>> commentsList;

	public void editBlog(String title, String content) { }
	public void deleteBlog(int BID) { }
	public int getBID() { return blogID;}
	public void addComment(String content, int UID) { }

}
