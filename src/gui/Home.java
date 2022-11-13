package gui;

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
        JPanel home = new JPanel();
        home.add(new JLabel("i am home"));

        // USER PAGE
        JPanel user = new JPanel();
        user.add(new JLabel("i am user"));

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

        try {
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        /* Turn off metal's use of bold fonts */
        UIManager.put("swing.boldMetal", Boolean.FALSE);

        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }



}
