package backend;

import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.HashMap;

import model.Model;
import model.CommentModel;

public class Comment extends CommentAbstract{
	int commentID;
	String content;
	int userID;
	public Comment(){

	}
	public Comment(int cID) {
		commentID = cID;


		JButton editComment = new JButton("edit comment");
		JButton deleteComment = new JButton("delete comment");
		Model comment = CommentModel.instance();


		editComment.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				HashMap<String, Object> editComment = new HashMap<>();
				editComment.put("content", content);
				comment.update(editComment);
			}
		});
		deleteComment.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				comment.delete(commentID);
			}
		});
	}
}