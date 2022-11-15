package gui;

import backend.Blog;
import backend.Comment;
import backend.User;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BlogFrame extends JFrame {

    JPanel contentPane = new JPanel();
    JLabel titleBlog;
    JLabel usernameBlog;
    JLabel datePosted;
    JLabel bodyBlog;
    JButton commentButton;


    // blog
    int blogID;
    int userID;
    HashMap<String, Object> blogInfo;
    Blog blog;
    // comments
    List<HashMap<String, Object>> allComments;
    Model user = UserModel.instance();

    public BlogFrame(int BID, int UID){

        // test variables to be changed
        blog = Blog.getInstance(BID);
        allComments = blog.getCommentList();
        blogInfo = blog.getBlogInfo(BID);

        blogID = BID;
        userID = UID;
        int authorUID = (int) blogInfo.get("UID");
        String authorName = (String) blogInfo.get("authorName");
        String blogTitle = (String) blogInfo.get("title");
        String blogContent = (String) blogInfo.get("content");



        setTitle("Blog of " + authorName);
        setBounds(500, 300, 600, 400);
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));
        contentPane.setBackground(Color.blue);
        contentPane.setBorder(BorderFactory.createLineBorder(Color.blue));

        titleBlog = new JLabel(blogTitle);
        usernameBlog = new JLabel(authorName);
//        datePosted = new JLabel(" at " + blog.getDate());
        datePosted.setForeground(Color.gray);
        bodyBlog = new JLabel(blogContent);

        JPanel subPanel = new JPanel();
        subPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        subPanel.setBackground(Color.white);
        subPanel.add(titleBlog);
        subPanel.add(usernameBlog);
        subPanel.add(datePosted);

        JPanel bodyPanel = new JPanel();
        bodyPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        bodyPanel.setBackground(Color.white);

        if (blogContent.length() >= 85) {
            String text = "<html>";
            for (int i = 0; i < blogContent.length(); i++) {
                text += blogContent.charAt(i);
                if (text.length() % 85 == 0) {
                    text += "<br>";
                }
            }
            text += "</html>";
            bodyBlog = new JLabel(text);
            bodyPanel.add(bodyBlog);
        }
        else {
            bodyBlog = new JLabel(blogContent);
            bodyPanel.add(bodyBlog);
        }

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.setBackground(Color.white);

        commentButton = new JButton("Comment");
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
                    public void actionPerformed(ActionEvent arg0) {
                        String commentContent = commentText.getText();
                        blog.addComment(commentContent, userID);
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

        // if user is bloguser, add edit and delete buttons
        if (UID == authorUID){
            JButton editButton = new JButton("Edit");
            JButton deleteButton = new JButton("Delete");

            editButton.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    //
                    EditBlogFrame editBlogFrame = new EditBlogFrame(blogID, userID);
                }
            });
            deleteButton.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent arg0) {

                    blog.deleteBlog(blogID);
                    blogID = -1;
                    // redirect to Home
                }
            });

            buttonPanel.add(editButton);
            buttonPanel.add(deleteButton);
        }

        contentPane.add(subPanel);
        contentPane.add(bodyPanel);
        contentPane.add(buttonPanel);

        // show comment
        JPanel commentScroll = new JPanel();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(8,8,8,8);

        commentScroll.setLayout(new BoxLayout(commentScroll, BoxLayout.PAGE_AXIS));
        commentScroll.setBackground(contentPane.getBackground());



        ArrayList<JPanel> blogPanel = showComment(allComments);
        for (JPanel panel : blogPanel) {
            commentScroll.add(panel);
            commentScroll.add(Box.createRigidArea(new Dimension(0,10)));
        }

        JScrollPane commentScrollPane = new JScrollPane(commentScroll);
        commentScrollPane.setBorder(null);
        commentScrollPane.setPreferredSize(new Dimension(600, 700));
        commentScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        add(commentScrollPane);
        pack();
    }

    public ArrayList<JPanel> showComment(List<HashMap<String, Object>> commentList) {
        ArrayList<JPanel> commentPanel = new ArrayList<>();
        // add pane for each comment

        for (int i = 0; i < commentList.size(); i++) {
            String commentContent = (String) commentList.get(i).get("content");
            int commentID = (int) commentList.get(i).get("CID");
            String commentDate = "test comment data";
            int commentAuthorID = (int) commentList.get(i).get("UID");
            String commentAuthorName = (String) user.getWithUid(commentAuthorID).get(0).get("username");
            int blogID = (int) commentList.get(i).get("BID");

            Comment comment = new Comment(commentID, commentContent, commentAuthorID, blogID);


            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

            panel.setBackground(Color.white);
            panel.setBorder(BorderFactory.createLineBorder(Color.black));

            JPanel subPanel = new JPanel();
            subPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
            subPanel.setBackground(Color.white);
            JLabel usernameLabel = new JLabel("• Posted by " + commentAuthorName);
            JLabel datePosted = new JLabel(" at " + commentDate);
            datePosted.setForeground(Color.gray);
            subPanel.add(usernameLabel);
            subPanel.add(datePosted);


            JPanel bodyPanel = new JPanel();
            bodyPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
            bodyPanel.setBackground(Color.white);

            JLabel bodyComment = new JLabel(commentContent);
            bodyPanel.add(bodyComment);

            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
            buttonPanel.setBackground(panel.getBackground());

            JButton commentButton = new JButton("Comment");
            buttonPanel.add(commentButton);

            if (commentAuthorID == userID) {
                JButton editButton = new JButton("Edit");
                JButton deleteButton = new JButton("Delete");
                editButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent ae) {
                        // need to open new EditCommentFrame
                        HashMap<String, Object> editComment = new HashMap<>();
                        //editComment.put("content", blogcontent);
                        //editComment.put("BID", blogID);
                        //editComment.put("UID", userID)
                        //comment.update(editComment);
                    }
                });

                buttonPanel.add(editButton);
                buttonPanel.add(deleteButton);

                editButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent ae) {
                        // Need to open EditBlogFrame(tobedone)
//                         comment.editComment(String newComment);
                    }
                });

                deleteButton.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent arg0) {

                        comment.deleteComment();
                    }
                });
            }


            panel.add(subPanel);
            panel.add(bodyPanel);
            panel.add(buttonPanel);


            commentPanel.add(panel);

            // add mouse listener for this whole panel
//            panel.addMouseListener(new MouseAdapter() {
//                public void mouseClicked(MouseEvent e) {
//                    BlogFrame blogFrame = new BlogFrame(blog, user);
//                    blogFrame.setVisible(true);
//                }
//            });

        }

        return commentPanel;
    }

}
