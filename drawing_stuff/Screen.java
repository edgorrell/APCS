package drawing_stuff;

import java.io.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.image.*;

public class Screen extends JComponent{
    public static ArrayList<Point> points;
    Tree tree;
    Player player;
    
    public Screen(int size){
        points = new ArrayList<Point>();
        player = new Player(size/2,size/2,size,10);
        points.add(player.getPos());
        tree = new Tree(0,0,size,size);
        Thread t = new Thread(player);
        t.start();
    }
    public void paintComponent(Graphics g){
        Graphics2D canvas = (Graphics2D) g;
        tree.draw(canvas);
        canvas.setColor(Color.BLACK);
        points.set(0,player.getPos());
        for(Point p : points){
            canvas.fillOval(p.x-3,p.y-3,6,6);
        }
    }
    public void nextFrame(){
        repaint();
    }
}