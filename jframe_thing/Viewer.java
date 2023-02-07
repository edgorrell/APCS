package jframe_thing;

import java.io.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.image.*;
import java.awt.event.*;

public class Viewer{
    static Keyboard keyboard;
    static JFrame frame;
    public static void main(String[] args) throws InterruptedException, IOException{
        JFrame frame = new JFrame();
        frame.setSize(528, 423);
        frame.setTitle("idk");
        //frame.setIconImage(ImageIO.read(new File("sprites/icon.png")));
        frame.setBackground(Color.black);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        Screen screen = new Screen();
        frame.add(screen);
        keyboard = new Keyboard(frame);
        
        while(true){
            screen.nextFrame();
            Thread.sleep(17); // approx 30fps
        }
    }
}