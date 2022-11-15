package backend;

import java.util.*;

import model.Model;
import model.UserModel;
import model.BlogModel;

public class User extends UserAbstract {

	private static User instance;
	private int userID;
	private String username;

	List<HashMap<String, Object>> blogList;

	Model user = UserModel.instance();
	Model blog = BlogModel.instance();
	public User() {

	}

	public List<HashMap<String, Object>> getBlogList() {
		Model blog = BlogModel.instance();
		blogList = blog.getWithUid(this.userID);
		return blogList;
	}
	public void addBlog(String title, String content, int UID) {
		HashMap<String, Object> newBlog = new HashMap<>();
		newBlog.put("title", title);
		newBlog.put("content", content);
		newBlog.put("UID", UID);
		blog.insert(newBlog);
	}
	public int getUID() {
		return userID;
	}
	public String getUsername() {
		this.username = (String) user.getWithUid(0).get(0).get("username");
		return username;
	}

	public static User getInstance(int uid) {
		if (instance == null) {
			instance = new User();
			instance.userID = uid;
		}
		return instance;
	}
}