package screen_maybe;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Screen{
    public static void createScreen(){
        JFrame frame = new JFrame("Test Screen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        JLabel emptyLabel = new JLabel("");
        emptyLabel.setPreferredSize(new Dimension(400, 600));
        frame.getContentPane().add(emptyLabel, BorderLayout.CENTER);
 
        frame.pack();
        frame.setVisible(true);
    }
    public static void main(String[] args){
        createScreen();
    }
}