package view;

import controller.BackendConsole;
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
    private JPanel newBlogPane;
    private JTextField newBlogTitleField;
    private JTextArea newBlogContentField;
    private JButton newBlogToIndexBtn;
    private JButton createBlogBtn;
    private JButton indexToNewBlogBtn;
    private JPanel newCommentPane;
    private JTextArea commentField;
    private JButton newCommentToBlogDetailBtn;
    private JButton createCommentBtn;
    private JButton leaveACommentButton;
    private JPanel editBlogPane;
    private JTextArea editBlogContentField;
    private JButton deleteBlogBtn;
    private JButton updateBlogBtn;
    private JButton editBlogToBlogDetailBtn;
    private JTextField editBlogTitleField;
    private JPanel editCommentPane;
    private JTextArea commentDetailField;
    private JButton deleteCommentBtn;
    private JButton editCommentToBlogDetailBtn;
    private JButton updateCommentBtn;
    private JLabel commentDetailCID;
    private JButton registerBtn;
    private JButton registerToLoginBtn;
    private JPanel registerPane;
    private JTextField registerUsernameField;
    private JPasswordField registerPasswordField;
    private JButton loginToRegisterBtn;
    private JTextField searchField;
    private JButton searchBtn;
    private CardLayout cLayout;
    private BackendConsole bc;
    private boolean isLoggedIn = false;
    private DefaultTableModel myIndexTableModel;
    private DefaultTableModel myBlogTableModel;
    private DefaultTableModel myBlogDetailTableModel;

    public MainFrame(){
        setTitle("Team 5 Blog Application");
        setSize(800, 500);
        setContentPane(contentPane);
        cLayout = new CardLayout();
        contentPane.setLayout(cLayout);
        contentPane.add(loginPane, "login");
        contentPane.add(indexPane, "index");
        contentPane.add(myBlogPane, "myBlog");
        contentPane.add(blogDetailPane, "blogDetail");
        contentPane.add(newBlogPane, "newBlog");
        contentPane.add(newCommentPane, "newComment");
        contentPane.add(editBlogPane, "editBlog");
        contentPane.add(editCommentPane, "editComment");
        contentPane.add(registerPane, "register");

        bc = BackendConsole.instance();
        initializeTables();
        addListeners();

        if (isLoggedIn){
            loadIndex();
        }else{
            logOut();
        }
    }

    private void loadIndex(){
        myIndexTableModel.setRowCount(0);
        List<HashMap<String, Object>> blogs = bc.blog.getAll();
        for (HashMap<String, Object> blog : blogs){
            myIndexTableModel.addRow(new Object[] {blog.get("title"), blog.get("content"), blog.get("BID")});
        }
        cLayout.show(contentPane, "index");
    }

    private void loadIndex(String searchKey){
        myIndexTableModel.setRowCount(0);
        List<HashMap<String, Object>> blogs = bc.blog.getAll();
        for (HashMap<String, Object> blog : blogs){
            if (blog.get("title").toString().contains(searchKey) ||
                    blog.get("content").toString().contains(searchKey)) {
                myIndexTableModel.addRow(new Object[]{blog.get("title"), blog.get("content"), blog.get("BID")});
            }
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
        blogDetailUIDLabel.setText(""+bc.blog.getUsername(bc.blog.getUID()));

        myBlogDetailTableModel.setRowCount(0);
        List<HashMap<String, Object>> comments = bc.blog.getCommentList();
        for (HashMap<String, Object> comment :  comments){
            if ((int) comment.get("UID") == bc.user.getUID()){
                myBlogDetailTableModel.addRow(new Object[]{comment.get("content"), "Click to update/delete", comment.get("CID")});
            }else{
                myBlogDetailTableModel.addRow(new Object[]{comment.get("content"), "", comment.get("CID")});
            }
        }

        cLayout.show(contentPane, "blogDetail");
    }

    private void logOut(){
        usernameField.setText("");
        passwordField.setText("");
        cLayout.show(contentPane, "login");
    }

    private void loadNewBlog(){
        cLayout.show(contentPane, "newBlog");
    }

    private void loadNewComment(){
        cLayout.show(contentPane, "newComment");
    }

    private void loadEditBlog() {
        editBlogTitleField.setText(bc.blog.getTitle());
        editBlogContentField.setText(bc.blog.getContent());
        cLayout.show(contentPane, "editBlog");
    }

    private void loadEditComment(int CID, String comment) {
        commentDetailField.setText(comment);
        commentDetailCID.setText(String.valueOf(CID));
        commentDetailCID.setVisible(false);
        cLayout.show(contentPane, "editComment");
    }

    private void loadRegister(){
        cLayout.show(contentPane, "register");
    }

    private void addListeners() {
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

        indexToNewBlogBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadNewBlog();
            }
        });

        newBlogToIndexBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadIndex();
            }
        });

        createBlogBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String title = newBlogTitleField.getText();
                String content = newBlogContentField.getText();
                int UID = bc.user.getUID();
                bc.blog.create(title, content, UID);
                newBlogTitleField.setText("");
                newBlogContentField.setText("");
                loadIndex();
            }
        });

        leaveACommentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadNewComment();
            }
        });

        newCommentToBlogDetailBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadBlogDetail(bc.blog.getBID());
            }
        });

        createCommentBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String comment = commentField.getText();
                int BID = bc.blog.getBID();
                int UID = bc.user.getUID();
                bc.comment.createComment(comment, BID, UID);
                commentField.setText("");
                loadBlogDetail(BID);
            }
        });

        blogDetailUpdateBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadEditBlog();
            }
        });

        editBlogToBlogDetailBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadBlogDetail(bc.blog.getBID());
            }
        });

        updateBlogBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String title = editBlogTitleField.getText();
                String content = editBlogContentField.getText();
                bc.blog.editBlog(title, content);
                loadBlogDetail(bc.blog.getBID());
            }
        });

        deleteBlogBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                bc.blog.deleteBlog(bc.blog.getBID());
                loadIndex();
            }
        });

        editCommentToBlogDetailBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadBlogDetail(bc.blog.getBID());
            }
        });

        blogDetailTable.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                JTable table =(JTable) mouseEvent.getSource();
                Point point = mouseEvent.getPoint();
                int row = table.rowAtPoint(point);
                int col = table.columnAtPoint(point);
                String clicked = blogDetailTable.getModel().getValueAt(row, col).toString();

                if (clicked.equals("Click to update/delete")) {
                    int CID = (int) blogDetailTable.getModel().getValueAt(row, 2);
                    String comment = blogDetailTable.getModel().getValueAt(row, 0).toString();
                    loadEditComment(CID, comment);
                }
            }
        });

        deleteCommentBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int CID = Integer.parseInt(commentDetailCID.getText());
                bc.comment.deleteComment(CID);
                loadBlogDetail(bc.blog.getBID());
            }
        });

        updateCommentBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int CID = Integer.parseInt(commentDetailCID.getText());
                String comment = commentDetailField.getText();
                bc.comment.updateComment(CID, comment);
                loadBlogDetail(bc.blog.getBID());
            }
        });

        loginToRegisterBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadRegister();
            }
        });

        registerToLoginBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                logOut();
            }
        });

        registerBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String usr = registerUsernameField.getText();
                String pwd = new String(registerPasswordField.getPassword());
                bc.user.register(usr, pwd);
                registerPasswordField.setText("");
                registerUsernameField.setText("");
                logOut();
            }
        });

        searchBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String searchKey = searchField.getText();
                loadIndex(searchKey);
                searchField.setText("");
            }
        });
    }

    private void initializeTables() {
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
        blogDetailTable.getColumnModel().removeColumn(blogDetailTable.getColumnModel().getColumn(2));
    }
}
