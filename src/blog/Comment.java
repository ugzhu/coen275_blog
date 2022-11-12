package blog;

import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.HashMap;

public class Comment extends CommentAbstract{
	public Comment(int commentID) {
		String content;
		int userID;
	}

	JButton editComment = new JButton("edit comment"); 
	JButton deleteComment = new JButton("delete comment");
	Model comment = CommentModel.instance();
	
	editComment.addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent arg0) {
			HashMap<String, Object> editComment;
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
