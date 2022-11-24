package controller;

import java.util.HashMap;
import model.Model;
import model.CommentModel;

public class CommentList{

	int commentID;
	String content;
	int userID;
	int blogID;
	Model comment = CommentModel.instance();

	public CommentList(int CID, String content, int BID, int UID) {
		commentID = CID;
		this.content = content;
		this.userID = UID;
		this.blogID = BID;
	}

	public void editComment(String newContent) {
		HashMap<String, Object> editedComment = new HashMap<>();
		editedComment.put("CID", commentID);
		editedComment.put("content", newContent);
		comment.update(editedComment);
	}
	public void deleteComment() {
		comment.delete(commentID);
	}

}