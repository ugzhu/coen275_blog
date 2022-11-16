package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyCard extends JFrame{
    private JPanel contentPane;
    private JTextPane loginTextPane;
    private JButton loginButton;
    private JButton indexButton;
    private JPanel indexPane;
    private JPanel loginPane;
    private JLabel loginLabel;
    private JPanel myBlogPane;
    private JTable myBlogTable;
    private JLabel indexLabel;
    private JLabel myBlogLabel;
    private JTable indexTable;
    private JButton button1;
    private JButton myToAllBtn;
    private JButton allToMyBtn;
    private JButton button3;
    private CardLayout cLayout;

    public MyCard(){
        setTitle("Card Layout Example");
        setSize(300, 150);
        setContentPane(contentPane);

        cLayout = new CardLayout();
        contentPane.setLayout(cLayout);
        contentPane.add(loginPane, "login");
        contentPane.add(indexPane, "index");

        cLayout.show(contentPane, "index");

        loginButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {

                cLayout.show(contentPane, "index");
            }
        });

        indexButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {

                cLayout.show(contentPane, "login");
            }
        });
    }



}
