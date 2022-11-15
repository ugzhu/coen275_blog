package backend;

import model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Blog extends BlogAbstract{

	private static Blog instance;
	private int blogID;
	List<HashMap<String, Object>> commentsList;
	Model comment = CommentModel.instance();
	Model blog = BlogModel.instance();
	Model user = UserModel.instance();


	public Blog(int BID) {
		blogID = BID;
	}
	public List<HashMap<String, Object>> getCommentList() {
		commentsList = comment.getWithBid(blogID);
		return commentsList;
	}

	public HashMap<String, Object> getBlogInfo(int BID) {
		HashMap<String, Object> Info = blog.getWithBid(BID).get(0);
		Info.put("authorName", (String) user.getWithUid((int) Info.get("UID")).get(0).get("username"));
		return Info;
	}

	public void editBlog(String title, String content) {
		HashMap<String, Object> editedBlog = new HashMap<>();
		editedBlog.put("title", title);
		editedBlog.put("content", content);
		editedBlog.put("BID", this.blogID);
		blog.update(editedBlog);
	}
	public void deleteBlog(int BID) {
		blog.delete(BID);
		getInstance(-1);
	}
	public int getBID() {
		return blogID;
	}
	public void addComment(String content, int UID) {
		HashMap<String, Object> newComment = new HashMap<>();
		newComment.put("content", content);
		newComment.put("BID", blogID);
		newComment.put("UID", UID);
		comment.insert(newComment);
	}
	public static Blog getInstance(int bid) {
		if (instance == null) {
			instance = new Blog(bid);
		} else if (bid == -1) {
			instance = null;
		}
		return instance;
	}

}
