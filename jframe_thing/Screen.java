package jframe_thing;

import java.io.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.image.*;
import java.awt.event.*;

public class Screen extends JComponent{
    Thread t1, t2, t3, t4;
    Box box1, box2, box3, box4;
    
    public Screen() throws IOException{
        this.box1 = new Box(new Color(0,0,0),100,100,50,50);
        this.box2 = new Box(new Color(255,0,0),120,120,50,50);
        this.box3 = new Box(new Color(0,255,0),140,140,50,50);
        this.box4 = new Box(new Color(0,0,255),160,160,50,50);
        
        this.t1 = new Thread(box1);
        this.t2 = new Thread(box2);
        this.t3 = new Thread(box3);
        this.t4 = new Thread(box4);
        
        t1.start(); t2.start(); t3.start(); t4.start();
    }
    public void paintComponent(Graphics g){
        Graphics2D frame = (Graphics2D) g;
        
        this.box1.draw(frame);
        //this.box2.draw(frame);
        //this.box3.draw(frame);
        //this.box4.draw(frame);
    }
    public void nextFrame(){
        repaint();
    }
}
