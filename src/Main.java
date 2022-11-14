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
                    HomePage homePage = new HomePage();

                    // and prompt login dialog
                    LoginForm loginForm = new LoginForm(homeFrame);
                    loginForm.setVisible(true);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        // //////////////////////////////////
        // /////////    User ///////////////
        // /////////////////////////////////
        Model user = UserModel.instance();

        // // get all
        // user.getAll();
        // // input: none
        // // output: List<HashMap<String, Object>>

        // get with user name
        System.out.println(user.getWithUsername("stella2021"));
        // input: String username
        // output:

        // get all
        user.getAll();
        // input: none
        // output: List<HashMap<String, Object>>

        // // get user
        // user.getWithUid(1);
        // // input: int UID
        // // output: List<HashMap<String, Object>>


        // // insert
        // // user.insert();
        // // input sample: {"username":"myusrname", "password":"mypwd"}
        // // output: none


        // //////////////////////////////////
        // /////////    Blog ///////////////
        // /////////////////////////////////
        // Model blog = BlogModel.instance();

        // // get all
        // blog.getAll();
        // // input: none
        // // output: List<HashMap<String, Object>>


        // // insert
        // // blog.insert()
        // // input example: {"title":"mytitle", "content":"mycontent", "UID":1}
        // // output: none


        // // get with uid
        // blog.getWithUid(1);
        // // input: int UID
        // // output: List<HashMap<String, Object>>


        // // get with bid
        // // ******
        // blog.getWithBid(1);
        // // input: int BID
        // // output: List<HashMap<String, Object>>


        // // delete
        // // blog.delete(1)
        // // input: int BID
        // // output: none


        // // update
        // // blog.update()
        // // sample input: {"title":"newtitle", "content":"newcontent", "BID":1}
        // // output: none



        // //////////////////////////////////
        // /////////    Comment /////////////
        // /////////////////////////////////
        // Model comment = CommentModel.instance();
        // // get all
        // comment.getAll();
        // // input: none
        // // output: List<HashMap<String, Object>>


        // // insert
        // // comment.insert
        // // input example: {BID:2, "content":"mycontent", "UID":1}
        // // output: none


        // // get with bid
        // comment.getWithBid(1);
        // // input: int BID
        // // output: List<HashMap<String, Object>>


        // // delete
        // // comment.delete(1)
        // // input: int CID
        // // output: none


        // // update
        // // comment.update()
        // // sample input: {"content":"new comment", "CID":1}
        // // output: none

        // //////// IMPORTANT ///////////
        // //////// IMPORTANT ///////////
        // //////// IMPORTANT ///////////
        // // All model classes are singleton
        // // do not use new method
        // // see above example
    }
}