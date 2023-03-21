package drawing_stuff;


import java.io.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.image.*;
import java.awt.event.*;

public class Viewer{
    public static JFrame frame;
    public static int width, height;
    
    public static void main(String[] args) throws InterruptedException, IOException{
        JFrame frame = new JFrame();
        frame.setSize(512,512);
        frame.setTitle("Screen");
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