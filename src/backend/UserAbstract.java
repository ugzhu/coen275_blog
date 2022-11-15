package backend;

import java.util.List;
import java.util.HashMap;

public interface UserAbstract {
	public List<HashMap<String, Object>> getBlogList();
	public void addBlog(String title, String content, int UID);
	public int getUID();
	public String getUsername();
}