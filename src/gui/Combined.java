package gui;

import javax.swing.*;

import java.awt.*;

import static gui.Home.homeFrame;

public class Combined {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    // open home frame
                    HomePage homePage = new HomePage();

                    // and prompt login dialog
                    LoginForm loginForm = new LoginForm(homeFrame);
                    loginForm.setVisible(true);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
