package gui;

import backend.Blog;
import backend.User;
import model.BlogModel;
import model.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class EditBlogFrame extends JFrame {
    JPanel contentPane;

    JButton updateButton;
    JButton cancelButton;
    JButton deleteButton;

    JTextField blogTitle;
    JTextArea blogBody;

    Blog blog;

    public EditBlogFrame(int BID, int UID){
        contentPane = new JPanel();
        setTitle("Edit Blog");
        setContentPane(contentPane);
        setMinimumSize(new Dimension(750, 750));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setVisible(true);

        updateButton = new JButton("Update");
        cancelButton = new JButton("Cancel");
        deleteButton = new JButton("Delete");
        blogTitle = new JTextField();
        blogBody = new JTextArea();

        int blogID = BID;
        blog = Blog.getInstance(blogID);


        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // read title and body, store under user's blog
                String newTitle = blogTitle.getText();
                String newContent = blogBody.getText();
                //
                blog.editBlog(newTitle, newContent);
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                blog.deleteBlog(blogID);
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
//
//    public static void main(String[] args) {
//
//        EditBlogFrame editBlogFrame = new EditBlogFrame(BID,UID);
//    }
}
