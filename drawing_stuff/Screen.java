package drawing_stuff;

import java.io.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.image.*;

public class Screen extends JComponent{
    public static ArrayList<Point> points;
    private Tree tree;
    public Screen(int size){
        points = new ArrayList<Point>();
        for(int i = 0; i < 1024; i++){
            double t = 2*Math.PI*i/1024.0;
            Point p = new Point((int)(size*((t*Math.cos(2*t)/(4*Math.PI))+0.5)),
                                (int)(size*((t*Math.sin(2*t)/(4*Math.PI))+0.5)));
            p.x += Math.random(); p.y += Math.random();
        }
        tree = new Tree(0,0,size,size);
    }
    public void paintComponent(Graphics g){
        Graphics2D canvas = (Graphics2D) g;
        canvas.setColor(Color.RED);
        for(Point p : points){
            canvas.fillOval(p.x-3,p.y-3,6,6);
        }
        tree.draw(canvas);
    }
    public void nextFrame(){
        repaint();
    }
}