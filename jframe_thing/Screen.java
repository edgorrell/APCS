package jframe_thing;

import java.io.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.image.*;
import java.awt.event.*;

public class Screen extends JComponent{
    Thread t1;
    
    //declare objects
    Box box;
    public Screen() throws IOException{
        this.box = new Box(new Color(100,100,100),100,100,50,50);
        
        this.t1 = new Thread(box);
        
        t1.start();
    }
    public void paintComponent(Graphics g){
        if(!Window.isFocused){
            return;
        }
        Graphics2D frame = (Graphics2D) g;
        
        this.box.draw(frame);
    }
    public void nextFrame(){
        repaint();
    }
}
