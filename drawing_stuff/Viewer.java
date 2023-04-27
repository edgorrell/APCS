package drawing_stuff;

import java.io.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.image.*;

public class Viewer{
    private static EventManager eventHandler;
    
    public static void main(String[] args) throws InterruptedException{
        int size = 512;
        JFrame frame = new JFrame();
        frame.setSize(size,size);
        frame.setTitle("yes");
        frame.setBackground(Color.BLACK);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
        Screen screen = new Screen(size);
        frame.add(screen);
        eventHandler = new EventManager(frame);

        while(true){
            screen.nextFrame();
            Thread.sleep(17);
        }
    }
}
