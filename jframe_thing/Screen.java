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
    Player p1;
    Box box;
    
    public Screen() throws IOException{
        this.p1 = new Player(new Color(255,0,0),100,100,50,50);
        this.box = new Box(new Color(0,0,0),300,300,50,50);
        
        this.t1 = new Thread(p1);
        
        t1.start();
    }
    public void paintComponent(Graphics g){
        Graphics2D frame = (Graphics2D) g;
        
        this.p1.draw(frame);
        this.box.draw(frame);
        
        frame.drawLine(0,100,500,100);
        frame.drawLine(100,0,100,500);
        frame.drawLine(0,300,500,300);
        frame.drawLine(300,0,300,500);
    }
    public void nextFrame(){
        if(p1.collidesWith(box)){ System.out.println(Math.random()); }
        repaint();
    }
}
