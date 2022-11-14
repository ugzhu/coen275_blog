package gui;

import backend.User;
import model.Model;
import model.UserModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class LoginForm extends JDialog {
    private JPanel loginPanel;
    private JTextField usernameInput;
    private JButton loginButton;
    private JButton regsButton;
    private JButton exitButton;
    private JPasswordField passwordInput;

    //// to be changed
    int userID;
    List<HashMap<String, Object>> userBlogs;

    public LoginForm (JFrame parent) {
        super(parent);
        setTitle("Login to your account");
        setContentPane(loginPanel);
        setMinimumSize(new Dimension(450, 450));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        Model user = UserModel.instance();

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                HashMap<String, Object> check = user.getWithUsername(usernameInput.getText()).get(0);
                if (check == null || ! Arrays.equals(check.get("password").toString().toCharArray(), passwordInput.getPassword())) {
                    JOptionPane.showMessageDialog(loginPanel,
                            "Login Failed",
                            "Try again",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    userID = (int) check.get("UID");
                    User user = User.getInstance(userID);
                    userBlogs = user.getBlogList(userID);
                    // close window
                }

            }
        });

        regsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegisterForm registerForm = new RegisterForm(null);
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                // Have to log in as user to continue to blog
                parent.dispose();
            }
        });

    }

    public static void main(String[] args) {
        LoginForm loginForm = new LoginForm(null);
    }
}
