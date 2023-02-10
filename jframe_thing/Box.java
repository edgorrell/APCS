package jframe_thing;

import java.io.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.image.*;
import java.awt.event.*;

public class Box extends JComponent implements Runnable{
    Point2D[] points;
    Color color = new Color(100,100,100);
    double x = 20, y = 20, w = 50, h = 50;
    public Box(Color color, double x, double y, double w, double h){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        points = this.getPoints();
    }
    public Point2D[] getPoints(){
        this.points = new Point2D[4];
        this.points[0] = new Point2D(this.x + (this.w/2.0), this.y + (this.h/2.0));
        this.points[1] = new Point2D(this.x - (this.w/2.0), this.y + (this.h/2.0));
        this.points[2] = new Point2D(this.x - (this.w/2.0), this.y - (this.h/2.0));
        this.points[3] = new Point2D(this.x + (this.w/2.0), this.y - (this.h/2.0));
        return this.points;
    }
    public void draw(Graphics2D frame){
        frame.setColor(this.color);
        frame.fillRect((int)(this.x+this.w/2),(int)(this.y+this.h/2),(int)(this.w),(int)(this.h));
    }
    public void run(){
        while(true){
            try{
                Window.keysPressed.get(0);
                break;
            } catch(Exception e){}
        }
        while(true){
            ArrayList<String> keysPressed = new ArrayList<String>(keysPressed);
            for(String key : keysPressed){
                if(key.contains(Keyboard.keyStart + "A")){
                    this.x--;
                    continue;
                }
                if(key.contains(Keyboard.keyStart + "D")){
                    this.x++;
                    continue;
                }
                if(key.contains(Keyboard.keyStart + "W")){
                    this.y--;
                    continue;
                }
                if(key.contains(Keyboard.keyStart + "S")){
                    this.y++;
                    continue;
                }
            }
            try{
                Thread.sleep(17);
            } catch(Exception e){}
        }
    }
}