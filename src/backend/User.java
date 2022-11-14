package backend;

import java.util.*;

import model.Model;
import model.UserModel;
import model.BlogModel;

public class User extends UserAbstract {

	private static User instance;
	private int userID;

	public User() {
		List<HashMap<String, Object>> blogList;
	}


	public void register(String username, String password) {
		// add [username, password] to db
		HashMap<String, Object> newUser = new HashMap<>();
		newUser.put("username", username);
		newUser.put("password", password);
		Model user = UserModel.instance();
		user.insert(newUser);
	}

	public List<HashMap<String, Object>> getBlogList(int UID) {
		// verify if DB has matched account info

			Model blog = BlogModel.instance();
			blogList = blog.getWithUid(UID);
			return blogList;
	}

	public static User getInstance(int uid) {
		if (instance == null) {
			instance = new User();
			instance.userID = uid;
		}
		return instance;
	}
}