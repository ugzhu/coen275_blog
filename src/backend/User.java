package backend;

import java.util.*;

import model.Model;
import model.UserModel;
import model.BlogModel;

public class User implements UserAbstract {
	private int userID;
	private String username;

	List<HashMap<String, Object>> blogList;

	Model user = UserModel.instance();
	Model blog = BlogModel.instance();

	static private User instance_  = new User();

	static public User instance() {
		return instance_;
	}

	private User() {
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
	public void setUID(int UID){
		this.userID = UID;
	}
	public int getUID() {
		return userID;
	}
//	public String getUsername() {
//		this.username = (String) user.getWithUid(0).get(0).get("username");
//		return username;
//	}
	public String getNameWithUID(int UID) {
		return (String) user.getWithUid(UID).get(0).get("username");
	}
	public int login(String username, char[] password) {
		HashMap<String, Object> check;
		try {
			 check = user.getWithUsername(username).get(0);
		}catch(Exception e){
			return -1;
		}

		if (! Arrays.equals(check.get("password").toString().toCharArray(), password)) {
			return -1;
		} else {
			this.userID = (int) check.get("UID");
			this.username = getNameWithUID(this.userID);
			return 0;
		}
	}

	public void register(String username, char[] password) {
		HashMap<String, Object> newUser = new HashMap<>();
		newUser.put("username", username);
		newUser.put("password", password.toString());
		user.insert(newUser);;
	}

	public void logout(){
		instance_ = null;
	}
}