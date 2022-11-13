package gui;

import blog.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterForm extends JDialog {
    private JPanel registerPanel;
    private JTextField usernameInput;
    private JPasswordField passwordInput;
    private JPasswordField confirmInput;
    private JButton regsButton;
    private JButton cancelButton;



    public RegisterForm (JFrame parent) {
        super(parent);
        setTitle("Create a new account");
        setContentPane(registerPanel);
        setMinimumSize(new Dimension(450, 450));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        regsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // register
                if(confirmInput.getPassword() == passwordInput.getPassword()) {
                    User user = new User();
                    user.register(usernameInput.getText(), new String(passwordInput.getPassword()));
                } else {
                    JOptionPane.showMessageDialog(registerPanel,
                            "Confirm Password does not match",
                            "Try again",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        RegisterForm regform = new RegisterForm(null);
    }
}
