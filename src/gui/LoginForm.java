package gui;

import blog.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;

public class LoginForm extends JDialog {
    private JPanel loginPanel;
    private JTextField usernameInput;
    private JButton loginButton;
    private JButton regsButton;
    private JButton cancelButton;
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

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User user = new User();
                HashMap<String, Object> check = user.login(usernameInput.getText(), passwordInput.getText());
                if ((boolean) check.get("success") == true) {
                    userID = (int) check.get("UID");
                    // *******
                    userBlogs = (List) check.get("blogList");
                    // redirect to userpage

                } else {
                    JOptionPane.showMessageDialog(loginPanel,
                            "Login Failed",
                            "Try again",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        regsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegisterForm registerForm = new RegisterForm(null);
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

    }

    public static void main(String[] args) {
        LoginForm loginForm = new LoginForm(null);
    }
}
