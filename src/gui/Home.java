package gui;

import backend.Blog;
import backend.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home {
    public static JFrame homeFrame;
    private JPanel bloghome;
    final static String HOMEPANEL = "HOME";
    final static String USERPANEL = "USER";
    final static String BLOGPANEL = "BLOG";

    private JButton homeButton;
    private JButton userButton;
    private JButton blogButton;
    private JTextField searchBar;

    private JPanel homeNorth;
    private JPanel homeWest;
    private JPanel homeEast;
    private JPanel homeCenter;

    private JButton postBlog;

    boolean isLogin = false;




    public void addCards(Container pane){
        homeButton = new JButton(HOMEPANEL);
        userButton = new JButton(USERPANEL);
        blogButton = new JButton(BLOGPANEL);

        JPanel menuPane = new JPanel(new FlowLayout());
        menuPane.add(homeButton);
        menuPane.add(userButton);
        menuPane.add(blogButton);

        // HOME PAGE
        JPanel home = new JPanel(new BorderLayout());
        home.setBackground(Color.WHITE);
        //home.add(new JLabel("i am home"),BorderLayout.CENTER);

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
                // Test blog and user
                User user = new User();
                Blog blog = new Blog();
                if (false) {
                    BlogFrame frame = new BlogFrame(blog,user);
                    frame.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(home,
                            "Blog not found",
                            "Try again",
                            JOptionPane.INFORMATION_MESSAGE);
                }
                searchBar.setText("");
            }
        });

        homeNorth.add(searchBar);
        //homeWest.add();

        // Blogs display on home center


        // USER PAGE
        JPanel user = new JPanel();
        user.add(new JLabel("i am user"));
        // add blog
        // update blog
        // delete blog

        // BLOG PAGE
        JPanel blog = new JPanel();
        blog.add(new JLabel("i am blog"));

        bloghome = new JPanel(new CardLayout());
        bloghome.add(home,HOMEPANEL);
        bloghome.add(user,USERPANEL);
        bloghome.add(blog,BLOGPANEL);

        pane.add(menuPane, BorderLayout.PAGE_START);
        pane.add(bloghome, BorderLayout.CENTER);

        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout)(bloghome.getLayout());
                cl.show(bloghome, HOMEPANEL);
            }
        });

        userButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isLogin==false){
                    LoginForm loginForm = new LoginForm(homeFrame);
                    loginForm.setVisible(true);
                }
                CardLayout cl = (CardLayout)(bloghome.getLayout());
                cl.show(bloghome, USERPANEL);
            }
        });

        blogButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout)(bloghome.getLayout());
                cl.show(bloghome, BLOGPANEL);
            }
        });
    }

    public static void createAndShowGUI() {

        homeFrame = new JFrame("Blog App");
        homeFrame.setMinimumSize(new Dimension(1200, 700));
        homeFrame.setSize(1200, 700);

        homeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Home homepage = new Home();
        homepage.addCards(homeFrame.getContentPane());


        homeFrame.pack();
        homeFrame.setVisible(true);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    // open home frame
                    Home home = new Home();
                    home.createAndShowGUI();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }



}
