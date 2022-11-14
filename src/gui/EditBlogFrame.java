package gui;

import blog.Blog;
import blog.User;
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

    public EditBlogFrame(Blog b, User u){
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

        Model blog = BlogModel.instance();
        int blogID = 1;

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // read title and body, store under user's blog
                String title = blogTitle.getText();
                String content = blogBody.getText();
                //
                HashMap<String, Object> editBlog = new HashMap<>();
                editBlog.put("title", title);
                editBlog.put("content", content);
                editBlog.put("BID", blogID);
                blog.update(editBlog);
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                blog.delete(blogID);
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        User u = new User();
        Blog b = new Blog();
        EditBlogFrame editBlogFrame = new EditBlogFrame(b,u);
    }
}
