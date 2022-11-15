package backend;

public interface BlogAbstract {
	public void editBlog(String title, String content);
	public void deleteBlog(int BID);
	public int getBID();
	public void addComment(String content, int UID);
}
