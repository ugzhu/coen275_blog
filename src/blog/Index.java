package blog;
import model.BlogModel;
import model.Model;

import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.List;
import javax.swing.*;

 public class Index extends JFrame{
    JButton button1;
    public Index(){
        Container container = getContentPane(); // set the layout
        container.setLayout( new FlowLayout() );
        // create a button and add to the content pane
        JTextField  usernameInput = new JTextField(10);
        JTextField  passwordInput = new JTextField(10);
        JButton loginButton = new JButton("Login");
        container.add( usernameInput );
        container.add( passwordInput );
        container.add( loginButton );
        loginButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent arg0) {
                User user = new User();
                HashMap<String, Object> check = user.login(usernameInput.getText(), passwordInput.getText());
                System.out.println(check);
            }
        });
        setSize(600,300);
        setVisible( true );
    }
}