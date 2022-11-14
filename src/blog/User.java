package blog;

import java.awt.event.*;
import java.util.*;

import javax.swing.*;   
import model.Model;
import model.UserModel;
import model.BlogModel;

public class User extends UserAbstract {
	public User() {
		List<HashMap<String, Object>> blogList;
		int userID;
	}
//	private int UID;
//	private List<Integer> blogList;
	
	 
	public void register(String username, String password) {
		// add [username, password] to db
		HashMap<String, Object> newUser = new HashMap<>();
		newUser.put("username", username);
		newUser.put("password", password);
		Model user = UserModel.instance();
		user.insert(newUser);
	}
	
	public HashMap<String, Object> login(String username, String password) {
		// verify if DB has matched account info
		// get user info by username
		Model user = UserModel.instance();
		
		// ****** 
		HashMap<String, Object> userInfo = user.getWithUsername(username).get(0);
		userID = (int) userInfo.get("UID");
		System.out.println(userInfo);
		// *******

		HashMap<String, Object> result = new HashMap<>();

		if (userInfo == null || !userInfo.get("password").equals(password)) {
			result.put("success", false);
			return result;
		} 
		else {
			Model blog = BlogModel.instance();
			blogList = blog.getWithUid(userID);
			result.put("success", true);
			result.put("UID", userID);
			result.put("blogList", blogList);
	        return result;
		}
		
	}
}


