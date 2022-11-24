package controller;

public class CommentListItem {

	int commentID;
	String content;
	int userID;
	int blogID;

	public CommentListItem(int CID, String content, int BID, int UID) {
		commentID = CID;
		this.content = content;
		this.userID = UID;
		this.blogID = BID;
	}
}



