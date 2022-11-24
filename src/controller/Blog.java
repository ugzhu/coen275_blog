package controller;

import model.*;
import java.util.HashMap;
import java.util.List;

public class Blog implements BlogAbstract{
	private int blogID;
	private String title;
	private String content;
	private int authorID;

	List<HashMap<String, Object>> commentsList;
	Model comment = CommentModel.instance();
	Model blog = BlogModel.instance();
	Model user = UserModel.instance();

	// Singleton
	static private Blog instance_  = new Blog();
	static public Blog instance() {
		return instance_;
	}

	private Blog() {
	}

	public void setBID(int BID){
		this.blogID = BID;
	}
	public List<HashMap<String, Object>> getAll() {
		return blog.getAll();
	}

	public List<HashMap<String, Object>> getCommentList() {
		return commentsList;
	}

	public String getTitle(){
		return this.title;
	}

	public String getContent(){
		return this.content;
	}

	public int getUID(){
		return authorID;
	}

	public String getUsername(int UID) {
		return user.getWithUid(UID).get(0).get("username").toString();
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
		instance_ = null;
	}

	public void setBlog(int BID) {
		HashMap<String, Object> info = blog.getWithBid(BID).get(0);
		this.blogID = (int) info.get("BID");
		this.authorID = (int) info.get("UID");
		this.content = (String) info.get("content");
		this.title = (String) info.get("title");
		commentsList = null;
		commentsList = comment.getWithBid(blogID);
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

	public void create(String title, String content, int UID) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("content", content);
		map.put("title", title);
		map.put("UID", UID);
		blog.insert(map);
	}
}
