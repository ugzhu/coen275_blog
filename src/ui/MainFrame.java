package ui;

import backend.BackendConsole;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.List;

public class MainFrame extends JFrame{
    private JPanel contentPane;
    private JButton loginButton;
    private JButton indexButton;
    private JPanel indexPane;
    private JPanel loginPane;
    private JPanel myBlogPane;
    private JTable myBlogTable;
    private JLabel indexLabel;
    private JLabel myBlogLabel;
    private JTable indexTable;
    private JButton myBlogLogOutBtn;
    private JButton myToIndexBtn;
    private JButton indexToMyBtn;
    private JButton indexLogOutBtn;
    private JButton loginBtn;
    private JPasswordField passwordField;
    private JTextField usernameField;
    private JLabel loginPrompt;
    private JScrollPane indexScrollPane;
    private JScrollPane myBlogScrollPane;
    private JPanel blogDetailPane;
    private JTable blogDetailTable;
    private JLabel blogDetailUIDLabel;
    private JLabel blogDetailContentLabel;
    private JLabel blogDetailTitleLabel;
    private JButton blogDetailUpdateBtn;
    private JButton blogDetailToIndexBtn;
    private JButton blogDetailToLogOutBtn;
    private JLabel login;
    private CardLayout cLayout;
    private BackendConsole bc;
    private boolean isLoggedIn = false;
    private DefaultTableModel myIndexTableModel;
    private DefaultTableModel myBlogTableModel;
    private DefaultTableModel myBlogDetailTableModel;
    public MainFrame(){
        myIndexTableModel = new DefaultTableModel();
        myIndexTableModel.addColumn("title");
        myIndexTableModel.addColumn("content");
        myIndexTableModel.addColumn("BID");
        indexTable.setModel(myIndexTableModel);
        indexTable.removeColumn(indexTable.getColumnModel().getColumn(2));

        myBlogTableModel = new DefaultTableModel();
        myBlogTableModel.addColumn("title");
        myBlogTableModel.addColumn("content");
        myBlogTableModel.addColumn("BID");
        myBlogTable.setModel(myBlogTableModel);
        myBlogTable.removeColumn(myBlogTable.getColumnModel().getColumn(2));


        myBlogDetailTableModel = new DefaultTableModel();
        myBlogDetailTableModel.addColumn("comment");
        myBlogDetailTableModel.addColumn("");
        myBlogDetailTableModel.addColumn("CID");
        blogDetailTable.setModel(myBlogDetailTableModel);
        blogDetailTable.removeColumn(blogDetailTable.getColumnModel().getColumn(2));


        setTitle("Team 5 Blog Application");
        setSize(600, 300);
        setContentPane(contentPane);
        cLayout = new CardLayout();
        contentPane.setLayout(cLayout);
        contentPane.add(loginPane, "login");
        contentPane.add(indexPane, "index");
        contentPane.add(myBlogPane, "myBlog");
        contentPane.add(blogDetailPane, "blogDetail");
        bc = BackendConsole.instance();

        if (isLoggedIn){
            loadIndex();
        }else{
            logOut();
        }


        myToIndexBtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                loadIndex();
            }
        });

        indexToMyBtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                loadMyBlog();
            }
        });

        loginBtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                if(bc.user.login(usernameField.getText(), passwordField.getPassword()) == 0){
                    loadIndex();
                }else{
                    loginPrompt.setText("Incorrect username or password!");
                    loginPrompt.setForeground(Color.RED);
                    usernameField.setText("");
                    passwordField.setText("");
                }
            }
        });

        myBlogLogOutBtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                logOut();
            }
        });

        blogDetailToLogOutBtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                logOut();
            }
        });


        indexLogOutBtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                isLoggedIn = true;
                logOut();
            }
        });

        blogDetailToIndexBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadIndex();
            }
        });

        indexTable.addComponentListener(new ComponentAdapter() {
        });
        indexTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int BID = (int) indexTable.getModel().getValueAt(indexTable.getSelectedRow(), 2);

                loadBlogDetail(BID);
            }
        });


        myBlogTable.addComponentListener(new ComponentAdapter() {
        });
        myBlogTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int BID = (int) myBlogTable.getModel().getValueAt(myBlogTable.getSelectedRow(), 2);
                loadBlogDetail(BID);
            }
        });

    }

    private void loadIndex(){
        myIndexTableModel.setRowCount(0);
        List<HashMap<String, Object>> blogs = bc.blog.getAll();
        for (HashMap<String, Object> blog : blogs){
            myIndexTableModel.addRow(new Object[] {blog.get("title"), blog.get("content"), blog.get("BID")});
        }
        cLayout.show(contentPane, "index");
    }
    private void loadMyBlog(){
        myBlogTableModel.setRowCount(0);
        List<HashMap<String, Object>> blogs = bc.blog.getAll();
        for (HashMap<String, Object> blog : blogs){
            if((int) blog.get("UID") == bc.user.getUID()){
                myBlogTableModel.addRow(new Object[] {blog.get("title"), blog.get("content"), blog.get("BID")});
            }
        }
        cLayout.show(contentPane, "myBlog");
    }

    private void loadBlogDetail(int BID){
        bc.blog.setBlog(BID);
        if(bc.user.getUID() != bc.blog.getUID()){
            blogDetailUpdateBtn.setVisible(false);
        }else{
            blogDetailUpdateBtn.setVisible(true);
        }
        blogDetailContentLabel.setText(bc.blog.getContent());
        blogDetailTitleLabel.setText(bc.blog.getTitle());
        blogDetailUIDLabel.setText(""+bc.blog.getUID());

        myBlogDetailTableModel.setRowCount(0);
        List<HashMap<String, Object>> comments = bc.blog.getCommentList();
        for (HashMap<String, Object> comment :  comments){
            if ((int) comment.get("UID") == bc.user.getUID()){
                myBlogDetailTableModel.addRow(new Object[]{comment.get("content"), "Update your comment", comment.get("content")});
            }else{
                myBlogDetailTableModel.addRow(new Object[]{comment.get("content"), "", comment.get("content")});
            }
        }

        cLayout.show(contentPane, "blogDetail");
    }

    private void logOut(){
        usernameField.setText("");
        passwordField.setText("");
        cLayout.show(contentPane, "login");
    }

}
