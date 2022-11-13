package gui;

import javax.swing.*;
import java.awt.*;

public class LayeredHome extends JFrame {

    public JFrame blogFrame;

    private JPanel layeredhome;
    private JLayeredPane layeredPane;

    private JPanel p1;
    private JPanel p2;
    private JPanel p3;

    public LayeredHome(){
        blogFrame = new JFrame();
        blogFrame.setTitle("BLOG");
        blogFrame.setMinimumSize(new Dimension(1000, 600));
        blogFrame.setSize(1000, 600);
        blogFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        layeredPane = new JLayeredPane();
        layeredPane.setSize(700,600);
        layeredPane.setBounds(300,0,700,600);
        layeredPane.setBackground(Color.blue);
        blogFrame.setLayeredPane(layeredPane);

        blogFrame.setVisible(true);
    }

    public void switchPanes(JPanel panel){
        layeredPane.removeAll();
        layeredPane.add(panel);
        layeredPane.repaint();
        layeredPane.revalidate();
    }

    public static void main(String[] args) {
        LayeredHome bloghome = new LayeredHome();
    }
}
