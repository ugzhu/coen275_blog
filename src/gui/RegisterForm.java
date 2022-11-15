package gui;

import backend.BackendConsole;

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
    BackendConsole bc;



    public RegisterForm (JFrame parent) {
        super(parent);
        setTitle("Create a new account");
        setContentPane(registerPanel);
        setMinimumSize(new Dimension(450, 450));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        bc = BackendConsole.instance();


        regsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // register
                if(confirmInput.getPassword() == passwordInput.getPassword()) {
                    bc.user.register(usernameInput.getText(), passwordInput.getPassword());
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
