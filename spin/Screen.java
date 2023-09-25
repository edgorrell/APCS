package spin;


import java.io.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.image.*;
import java.awt.event.*;

public class Screen extends JComponent{
    Picture p;
    
    public Screen(){
        p = new Picture();
    }
    public void paintComponent(Graphics g){
        Graphics2D canvas = (Graphics2D) g;
        p.draw(canvas);
    }
    public void nextFrame(){
        repaint();
    }
}
