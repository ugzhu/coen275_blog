package gui;

import blog.Blog;
import blog.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HomePage extends JFrame {
    JPanel contentPane;

    JPanel homePane;
    JPanel userPane;

    JButton homeButton;
    JButton userButton;

    JTextField searchBar;

    JButton postBlogButton;


    List<HashMap<String, Object>> allBlogs;
    List<HashMap<String, Object>> userBlogs;
    List<HashMap<String, Object>> allComments;

    //
    HashMap<Integer,String> blogTitles;
    boolean isLogin;
    User user;

    public HomePage(User user) {

    }

    public HomePage(){
        setMinimumSize(new Dimension(1200,700));
        setSize(1200,700);
        contentPane = new JPanel();
        addCards(contentPane);
        setContentPane(contentPane);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    public void addCards(Container containerPane){

        // MenuPane: searchBar, homeButton, userButton, postBlogButton
        homeButton = new JButton("HOME");
        userButton = new JButton("USER");
        postBlogButton = new JButton("Post New");

        searchBar = new JTextField("   Search for blog", 20);
        searchBar.setBounds(200, 12, 350, 30);
        searchBar.setBackground(new Color(238,238,238));
        searchBar.setForeground(Color.GRAY);
        searchBar.addActionListener(new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Text field is pressed: " + searchBar.getText().toLowerCase());
                String text = searchBar.getText().toLowerCase();
                // Search for blog
                // if found, prompt blog frame
                boolean found = false;
                if(blogTitles.containsValue(text)) {
                    found = true;
                }
                // Test blog and user
                User user = new User();
                Blog blog = new Blog();
                if (found) {
                    BlogFrame frame = new BlogFrame(blog,user);
                    frame.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(containerPane,
                            "Blog not found",
                            "Try again",
                            JOptionPane.INFORMATION_MESSAGE);
                }
                searchBar.setText("");
            }
        });

        JPanel menuPane = new JPanel(new FlowLayout());
        menuPane.add(searchBar);
        menuPane.add(homeButton);
        menuPane.add(userButton);
        menuPane.add(postBlogButton);


        // Cards: homePane, userPane
        // homePane
        homePane = new JPanel();
        homePane.add(new JLabel("i am homePane"));

        JPanel homeBlogScroll = new JPanel();

        // set up container:
        homePane.setPreferredSize(new Dimension(containerPane.getWidth(), containerPane.getHeight() - menuPane.getSize().height));
        homePane.setBackground(containerPane.getBackground());
        homePane.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(8,8,8,8);

        homeBlogScroll.setLayout(new BoxLayout(homeBlogScroll, BoxLayout.PAGE_AXIS));
        homeBlogScroll.setBackground(homePane.getBackground());

        ArrayList<Blog> blogList = new ArrayList<>();

        ArrayList<JPanel> blogPanel = showPost(blogList);
        for (JPanel panel : blogPanel) {
            homeBlogScroll.add(panel);
            homeBlogScroll.add(Box.createRigidArea(new Dimension(0,10)));
        }

        JScrollPane homeScrollPane = new JScrollPane(homeBlogScroll);
        homeScrollPane.setBorder(null);
        homeScrollPane.setPreferredSize(new Dimension(600, 700));
        homeScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        homePane.add(homeScrollPane, gbc);

        // ----------------------------------------------------------
        // userPane
        userPane = new JPanel();
        userPane.add(new JLabel("i am userPane"));

        JPanel userBlogScroll = new JPanel();

        // set up container:
        userPane.setPreferredSize(new Dimension(contentPane.getWidth(), containerPane.getHeight() - menuPane.getSize().height));
        userPane.setBackground(contentPane.getBackground());
        userPane.setLayout(new GridBagLayout());

        userBlogScroll.setLayout(new BoxLayout(homeBlogScroll, BoxLayout.PAGE_AXIS));
        homeBlogScroll.setBackground(contentPane.getBackground());

        ArrayList<Blog> userblogList = new ArrayList<>();

        ArrayList<JPanel> userblogPanel = showPost(userblogList);
        for (JPanel panel : userblogPanel) {
            userBlogScroll.add(panel);
            userBlogScroll.add(Box.createRigidArea(new Dimension(0,10)));
        }

        JScrollPane userScrollPane = new JScrollPane(homeBlogScroll);
        userScrollPane.setBorder(null);
        userScrollPane.setPreferredSize(new Dimension(600, 700));
        userScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        userPane.add(userScrollPane, gbc);



        JPanel cards = new JPanel(new CardLayout());
        cards.add(homePane);
        cards.add(userPane);

        containerPane.add(menuPane);
        containerPane.add(cards);

        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout)(cards.getLayout());
                cl.show(cards, "HOME");
            }
        });

        userButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isLogin==false){
                    LoginForm loginForm = new LoginForm(null);
                    loginForm.setVisible(true);
                }
                CardLayout cl = (CardLayout)(cards.getLayout());
                cl.show(cards, "USER");
            }
        });

        postBlogButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PostBlogFrame postBlogFrame = new PostBlogFrame(user);
            }
        });
    }

    public ArrayList<JPanel> showPost(ArrayList<Blog> list) {
        ArrayList<JPanel> blogPanel = new ArrayList<>();
        // add pane for each blog
        for (Blog blog : list) {
            //test variables - to be changed
            String blogtitle = "test title";
            String bloguser = "test bloguser";
            String blogdate = "test data";
            String blogbody = "test body";
            String username = "test user";

            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

            panel.setBackground(Color.white);
            panel.setBorder(BorderFactory.createLineBorder(Color.black));

            JPanel subPanel = new JPanel();
            subPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
            subPanel.setBackground(Color.white);
            JLabel titleLabel = new JLabel(blogtitle);
            //titleLabel.setFont(xxxx);
            JLabel usernameLabel = new JLabel("• Posted by " + bloguser);
            JLabel datePosted = new JLabel(" at " + blogdate);
            datePosted.setForeground(Color.gray);
            subPanel.add(titleLabel);
            subPanel.add(usernameLabel);
            subPanel.add(datePosted);


            JPanel bodyPanel = new JPanel();
            bodyPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
            bodyPanel.setBackground(Color.white);

            if (blogbody.length() >= 80) {
                String text = "<html>";
                for (int i = 0; i < blogbody.length(); i++) {
                    text += blogbody.charAt(i);
                    if (text.length() % 80 == 0) {
                        text += "<br>";
                    }
                }
                text += "</html>";
                JLabel bodyPost = new JLabel(text);
                bodyPanel.add(bodyPost);
            }
            else {
                JLabel bodyPost = new JLabel(blogbody);
                bodyPanel.add(bodyPost);
            }

            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
            buttonPanel.setBackground(panel.getBackground());

            ArrayList<Integer> blogcommentlist = blog.commentList;
            // show comments? or no?

            JButton commentButton = new JButton("Comment");
            buttonPanel.add(commentButton);

            if (bloguser.equals(username)) {
                JButton editButton = new JButton("Setting");
                buttonPanel.add(editButton);
                editButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent ae) {
                        // Open EditBlogFrame?
                    }
                });
            }

            commentButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    JDialog dialog = new JDialog(HomePage.this, "Comment");
                    // dialog.setSize(400,200);
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
                            // some function that add comment to blog
                            dialog.setVisible(false);
                        }
                    });
                    buttonPanel.add(addCommentButton);

                    dialog.add(panel);
                    dialog.add(buttonPanel);
                    dialog.pack();
                    dialog.setVisible(true);
                }
            });


            panel.add(subPanel);
            panel.add(bodyPanel);
            panel.add(buttonPanel);


            blogPanel.add(panel);

            // add mouse listener for this whole panel
            panel.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    BlogFrame blogFrame = new BlogFrame(blog, user);
                    blogFrame.setVisible(true);
                }
            });
        }

        return blogPanel;
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    // open home frame
                    HomePage homePage = new HomePage();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
