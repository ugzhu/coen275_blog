package gui;

import backend.User;
import model.BlogModel;
import model.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class PostBlogFrame extends JFrame {

    JPanel contentPane;

    JButton createButton;
    JButton cancelButton;

    JTextField blogTitle;
    JTextArea blogBody;

    public PostBlogFrame(User u){
        contentPane = new JPanel();
        setTitle("Post a New Blog");
        setContentPane(contentPane);
        setMinimumSize(new Dimension(750, 750));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setVisible(true);

        createButton = new JButton("Create");
        cancelButton = new JButton("Cancel");
        blogTitle = new JTextField();
        blogBody = new JTextArea();

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // read title and body, store under user's blog
                String title = blogTitle.getText();
                String content = blogBody.getText();
                String userID = "test ID";
                //
                Model blog = BlogModel.instance();
                HashMap<String, Object> newBlog = new HashMap();
                newBlog.put("title", title);
                newBlog.put("content", content);
                newBlog.put("UID",userID);
                blog.insert(newBlog);
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
        User user = new User();
        PostBlogFrame postBlogFrame = new PostBlogFrame(user);
    }
}
