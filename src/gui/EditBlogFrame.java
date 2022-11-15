package gui;

import backend.BackendConsole;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditBlogFrame extends JFrame {
    JPanel contentPane;

    JButton updateButton;
    JButton cancelButton;
    JButton deleteButton;

    JTextField blogTitle;
    JTextArea blogBody;


    public EditBlogFrame(int BID, int UID){
        BackendConsole bc = BackendConsole.instance();
        bc.blog.setBID(BID);
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

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // read title and body, store under user's blog
                String newTitle = blogTitle.getText();
                String newContent = blogBody.getText();
                //
                bc.blog.editBlog(newTitle, newContent);
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                bc.blog.deleteBlog(blogID);
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
