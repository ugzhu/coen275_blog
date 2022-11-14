package blog;

// import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public abstract class UIAbstract {
	public List<HashMap<String, Object>> allBlogs;
	public boolean isLogin;
	public List<HashMap<String, Object>> userBlogs;
	public List<HashMap<String, Object>> allComments;
	public int userID;
}