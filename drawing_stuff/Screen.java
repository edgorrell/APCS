package drawing_stuff;

import java.io.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.image.*;
import java.awt.event.*;

public class Screen extends JComponent{
    Pen pen;
    
    public Screen(){
        pen = new Pen(100,100);
    }
    public void paintComponent(Graphics g){
        Graphics2D frame = (Graphics2D) g;
        
        pen.draw(frame);
    }
    public void nextFrame(){
        repaint();
    }
}