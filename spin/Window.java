package spin;

import java.io.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.image.*;
import java.awt.event.*;

public class Window{
    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame();
        frame.setSize(800,600);
        frame.setTitle("idk");
        //frame.setIconImage(ImageIO.read(new File("sprites/icon.png")));
        frame.setBackground(Color.black);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        Screen screen = new Screen();
        frame.add(screen);
        while(true){
            screen.nextFrame();
            Thread.sleep(17);
        }
    }
}
