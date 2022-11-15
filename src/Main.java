import backend.*;
import gui.*;
import model.*;
import java.awt.*;

import static gui.Home.homeFrame;


public class Main {
    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    // open home frame
                    // Open until login
//                    HomePage homePage = new HomePage();

                    // and prompt login dialog
                    LoginForm loginForm = new LoginForm(homeFrame);
                    loginForm.setVisible(true);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        Model user = UserModel.instance();

        System.out.println(user.getWithUsername("stella2021"));

        user.getAll();



    }
}