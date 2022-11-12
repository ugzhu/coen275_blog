package blog;

import model.Model;
import model.BlogModel;
import model.UserModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JTextField;


public class UI extends UIAbstract {
	protected boolean isLogin;
	
	// main frame
	public UI() {
		List<HashMap<String, Object>> allBlogs;
		List<HashMap<String, Object>> userBlogs;
		List<HashMap<String, Object>> allComments;
		int userID;

		// use for redirect when not login
		boolean isLogin = false;
		
	}
	
	// user = new User
    // [{BID: 1, title: "mytitle1"}, {BID: 2, title: "othertitle"}]
	public void registerPage() {
		JTextField  usernameInput = new JTextField(10);
		JTextField  passwordInput = new JTextField(10);
		JButton regsButton = new JButton("Register"); 
		regsButton.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				User user = new User();
				user.register(usernameInput.getText(), passwordInput.getText());
				// System.out.print("Registered! Please login.");
				// redirect to login page
			}  
		});

	};
	public void loginPage() {
		JTextField  usernameInput = new JTextField(10);
		JTextField  passwordInput = new JTextField(10);
		JButton loginButton = new JButton("Login"); 
		loginButton.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				User user = new User();
				HashMap<String, Object> check = user.login(usernameInput.getText(), passwordInput.getText());
				if ((boolean) check.get("success") == true) {
					userID = (int) check.get("UID");
					// *******
					userBlogs = check.get("blogList");
		
				} else {
					System.out.print("Login failed.");
				}
			}  
		});
			
	};
	public void homePage() {
		// get allBlogs from db	
		Model blog = BlogModel.instance();
		allBlogs = blog.getAll();
	};
	public void userPage() {
		userBlogs = 
	};
	public void blogPage() {
		JButton editBlog = new JButton("edit blog"); 
		JButton deleteBlog = new JButton("delete blog"); 
		JButton editComment = new JButton("edit comment"); 
		JButton deleteComment = new JButton("delete comment");
		editBlog.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
			}  
		});
		deleteBlog.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
			}  
		});
		editComment.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
			}  
		});
		deleteComment.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
			}  
		});
	};
}

