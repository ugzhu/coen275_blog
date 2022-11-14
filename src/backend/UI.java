package backend;

import model.Model;
import model.BlogModel;
import model.CommentModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
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


	public void homePage() {
		// get allBlogs from db
		Model blog = BlogModel.instance();
		allBlogs = blog.getAll();
		System.out.println(allBlogs);
	};
	public void userPage() {
		// render userBlogs
		JButton addBlog = new JButton("add blog");
		// UI get
		String title = "";
		String content = "";
		Model blog = BlogModel.instance();
		HashMap<String, Object> newBlog = new HashMap();
		newBlog.put("title", title);
		newBlog.put("content", content);
		newBlog.put("UID", userID);
		blog.insert(newBlog);

	};
	public void blogPage() {

		// pass BID from button
		int blogID = 0;
		Model blog = BlogModel.instance();
		Model comment = CommentModel.instance();

		HashMap<String, Object> currBlog = blog.getWithBid(blogID).get(0);
		String title = (String) currBlog.get("title");
		String content = (String) currBlog.get("content");
		allComments = comment.getWithBid(blogID);
		// create comment instance
		int CID = 0;
		Comment c1 = new Comment(CID);

		JButton editBlog = new JButton("edit blog");
		JButton deleteBlog = new JButton("delete blog");
		JButton addComment = new JButton("add comment");

		addComment.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String commentContent = "";
				HashMap<String, Object> newComment = new HashMap<>();
				newComment.put("content", commentContent);
				newComment.put("BID", blogID);
				newComment.put("UID", userID);
				comment.insert(newComment);
			}
		});
		editBlog.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				HashMap<String, Object> editBlog = new HashMap<>();
				editBlog.put("title", title);
				editBlog.put("content", content);
				editBlog.put("BID", blogID);
				blog.update(editBlog);
			}
		});
		deleteBlog.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				blog.delete(blogID);
			}
		});

	};
}