package gui;

import backend.BackendConsole;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm extends JDialog {
    private JPanel loginPanel;
    private JTextField usernameInput;
    private JButton loginButton;
    private JButton regsButton;
    private JButton exitButton;
    private JPasswordField passwordInput;

    //// to be changed
    int userID;
    BackendConsole bc;

    public LoginForm (JFrame parent) {
        super(parent);
        setTitle("Login to your account");
        setContentPane(loginPanel);
        setMinimumSize(new Dimension(450, 450));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        bc = BackendConsole.instance();

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int ifUserID = bc.user.login(usernameInput.getText(), passwordInput.getPassword());
                if (ifUserID == -1){
                    JOptionPane.showMessageDialog(loginPanel,
                            "Login Failed",
                            "Try again",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    userID = ifUserID;
                    HomePage homePage = new HomePage(userID);
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
