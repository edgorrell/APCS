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
    Test test;
    public Screen() throws IOException{
        this.test = new Test();
        
        this.t1 = new Thread(test);
    }
    public void paintComponent(Graphics g){
        Graphics2D frame = (Graphics2D) g;
        
        this.test.draw(frame);
    }
    public void nextFrame(){
        repaint();
    }
}
