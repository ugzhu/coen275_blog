package gui;

import blog.Blog;
import blog.BlogAbstract;
import blog.Comment;
import blog.User;
import model.BlogModel;
import model.CommentModel;
import model.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
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
    JButton commentButton;

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
        Model blog = BlogModel.instance();
        Model comment = CommentModel.instance();

        String username = "test username";
        String blogusername = "test blogusername";
        String blogtitle = "test title";
        String blogcontent = "test content";
        int blogID = 1;
        int userID = 11;

        setTitle("Blog of " + blogusername);
        setBounds(500, 300, 600, 400);
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));
        contentPane.setBackground(Color.blue);
        contentPane.setBorder(BorderFactory.createLineBorder(Color.blue));

        titleBlog = new JLabel(blogtitle);
        usernameBlog = new JLabel(blogusername);
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
                        HashMap<String, Object> newComment = new HashMap<>();
                        newComment.put("content", commentContent);
                        newComment.put("BID", blogID);
                        newComment.put("UID", userID);
                        comment.insert(newComment);
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
        if (username.equals(blogusername)){
            JButton editButton = new JButton("Edit");
            JButton deleteButton = new JButton("Delete");

            editButton.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    //
                    EditBlogFrame editBlogFrame = new EditBlogFrame(b,u);
                }
            });
            deleteButton.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    blog.delete(blogID);
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

        ArrayList<Comment> commentList = new ArrayList<>();

        ArrayList<JPanel> blogPanel = showComment(commentList);
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

    public ArrayList<JPanel> showComment(ArrayList<Comment> list) {
        ArrayList<JPanel> commentPanel = new ArrayList<>();
        // add pane for each comment
        for(Comment comment : list){

            //test variables
            String commentbody = "test comment";
            String commentuser = "test comment user";
            String commentdate = "test comment data";
            String username = "test username";
            int commentID = 111;
            Model commentModel = CommentModel.instance();

            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

            panel.setBackground(Color.white);
            panel.setBorder(BorderFactory.createLineBorder(Color.black));

            JPanel subPanel = new JPanel();
            subPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
            subPanel.setBackground(Color.white);
            JLabel usernameLabel = new JLabel("• Posted by " + commentuser);
            JLabel datePosted = new JLabel(" at " + commentdate);
            datePosted.setForeground(Color.gray);
            subPanel.add(usernameLabel);
            subPanel.add(datePosted);


            JPanel bodyPanel = new JPanel();
            bodyPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
            bodyPanel.setBackground(Color.white);

            JLabel bodyComment = new JLabel(commentbody);
            bodyPanel.add(bodyComment);

            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
            buttonPanel.setBackground(panel.getBackground());

            JButton commentButton = new JButton("Comment");
            buttonPanel.add(commentButton);

            if (commentuser.equals(username)) {
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
                    }
                });

                deleteButton.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent arg0) {
                        commentModel.delete(commentID);
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

    public static void main(String[] args) {
        BlogFrame frame = new BlogFrame(blog,user);
        frame.setVisible(true);
    }
}
