package gui;

import backend.User;
import model.Model;
import model.UserModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

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
        Model user = UserModel.instance();

        regsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // register
                if(confirmInput.getPassword() == passwordInput.getPassword()) {
                    HashMap<String, Object> newUser = new HashMap<>();
                    newUser.put("username", usernameInput.getText());
                    newUser.put("password", passwordInput.getPassword());
                    user.insert(newUser);;
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
