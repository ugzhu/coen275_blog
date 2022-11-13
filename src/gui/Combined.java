package gui;

import javax.swing.*;

import static gui.Home.homeFrame;

public class Combined {


    public static void main(String[] args) {
        // open home
        Home home = new Home();
        home.createAndShowGUI();

        // and prompt login dialog
        LoginForm loginForm = new LoginForm(homeFrame);
        loginForm.setVisible(true);

    }
}
