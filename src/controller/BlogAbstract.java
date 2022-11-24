package controller;

public interface BlogAbstract {
	void editBlog(String title, String content);
	void deleteBlog(int BID);
	int getBID();
}
