package gui;

import blog.Blog;
import blog.BlogAbstract;
import blog.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BlogFrame extends JFrame {

    JPanel contentPane = new JPanel();
    JLabel titleBlog;
    JLabel usernameBlog;
    JLabel datePosted;
    JLabel bodyBlog;
    JButton commentButton = new JButton();

    // user
    static User user = new User();
    // blog
    static Blog blog = new Blog();
    // comments
    List<HashMap<String, Object>> allComments;

    public String getDate() {
        String date = "";

        date = java.time.LocalDate.now().toString() + " " + java.time.LocalTime.now().getHour() + ":"
                + java.time.LocalTime.now().getMinute() + ":" + java.time.LocalTime.now().getSecond();
        return date;
    }

    public BlogFrame(Blog b, User u){

        // test variables to be changed
        String username = "test username";
        String blogtitle = "test title";
        String blogcontent = "test content";

        setTitle("Blog of " + username);
        setBounds(500, 300, 600, 400);
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));
        contentPane.setBackground(Color.blue);
        contentPane.setBorder(BorderFactory.createLineBorder(Color.blue));

        titleBlog = new JLabel(blogtitle);
        usernameBlog = new JLabel(username);
        datePosted = new JLabel(" at " + getDate());
        datePosted.setForeground(Color.gray);
        bodyBlog = new JLabel(blogcontent);

        JPanel subPanel = new JPanel();
        subPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        subPanel.setBackground(Color.white);
        subPanel.add(titleBlog);
        subPanel.add(usernameBlog);
        subPanel.add(datePosted);

        JPanel bodyPanel = new JPanel();
        bodyPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        bodyPanel.setBackground(Color.white);

        if (blogcontent.length() >= 85) {
            String text = "<html>";
            for (int i = 0; i < blogcontent.length(); i++) {
                text += blogcontent.charAt(i);
                if (text.length() % 85 == 0) {
                    text += "<br>";
                }
            }
            text += "</html>";
            bodyBlog = new JLabel(text);
            bodyPanel.add(bodyBlog);
        }
        else {
            bodyBlog = new JLabel(blogcontent);
            bodyPanel.add(bodyBlog);
        }

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.setBackground(Color.white);

        JButton commentButton = new JButton("Comment");
        //JButton editButton = new JButton("Edit");
        //JButton deleteButton = new JButton("Delete");

        commentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                JDialog dialog = new JDialog();
                dialog.setBounds(600, 300, 600, 200);
                dialog.setLayout(new FlowLayout());
                JPanel panel = new JPanel();
                panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
                JTextArea commentText = new JTextArea(3, 20);
                commentText.addKeyListener(new KeyAdapter() {
                    public void keyTyped(KeyEvent e) {
                        JTextArea textArea = (JTextArea) e.getSource();
                        String text = textArea.getText();
                        if (text.length() % 30 == 0 && text.length() != 0) {
                            text += "\n";
                        }

                        textArea.setText(text);
                    }
                });

                panel.add(new JLabel("Your comment: "));
                panel.add(commentText);

                JPanel buttonPanel = new JPanel();
                buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
                JButton addCommentButton = new JButton("OK");
                addCommentButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        System.out.println(commentText.getText());
                        ////////to be added
                    }
                });
                buttonPanel.add(addCommentButton);

                dialog.add(panel);
                dialog.add(buttonPanel);
                dialog.setVisible(true);
            }
        });

        buttonPanel.add(commentButton);
        //buttonPanel.add(editButton);
        //buttonPanel.add(deleteButton);

        contentPane.add(subPanel);
        contentPane.add(bodyPanel);
        contentPane.add(buttonPanel);

        // show comment here?
        /////////

        JScrollPane commentScrollPane = new JScrollPane(contentPane);



        add(commentScrollPane);
        pack();
    }

    public static void main(String[] args) {
        BlogFrame frame = new BlogFrame(blog,user);
        frame.setVisible(true);
    }
}
