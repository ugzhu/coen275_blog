package gui;

import backend.BackendConsole;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PostBlogFrame extends JFrame {

    JPanel contentPane;

    JButton createButton;
    JButton cancelButton;

    JTextField blogTitle;
    JTextArea blogBody;
    BackendConsole bc;

    public PostBlogFrame(int UID){
        bc = BackendConsole.instance();
        bc.user.setUID(UID);
        int userID = UID;
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
                String newTitle = blogTitle.getText();
                String newContent = blogBody.getText();

                bc.user.addBlog(newTitle, newContent, userID);
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

}
