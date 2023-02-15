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
    final double DAMPENING = 0.95;
    double x = 20, y = 20, w = 50, h = 50;
    double xv, yv, xres, yres;
    public Box(Color color, double x, double y, double w, double h){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.xv = 0;
        this.yv = 0;
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
        frame.fillRect((int)(this.x-this.w/2),(int)(this.y-this.h/2),(int)(this.w),(int)(this.h));
        //frame.setColor(Color.RED);
        //frame.fillOval((int)(this.x-this.w/4),(int)(this.y-this.h/4),(int)(this.w/2),(int)(this.h/2));
    }
    public void run(){
        while(true){
            try{
                EventManager.keysPressed.get(0);
                break;
            } catch(Exception e){}
        }
        while(true){
            //move
            ArrayList<String> keysPressed = new ArrayList<String>(EventManager.keysPressed);
            this.xres = Math.pow(this.xv/10,2); this.xres = 1 - this.xres;
            this.yres = Math.pow(this.yv/10,2); this.yres = 1 - this.yres;
            for(String key : keysPressed){
                if(key.contains(EventManager.keyStart + "A")){
                    this.xv -= 1 * this.xres;
                    continue;
                }
                if(key.contains(EventManager.keyStart + "D")){
                    this.xv += 1 * this.xres;
                    continue;
                }
                if(key.contains(EventManager.keyStart + "W")){
                    this.yv -= 1 * this.yres;
                    continue;
                }
                if(key.contains(EventManager.keyStart + "S")){
                    this.yv += 1 * this.yres;
                    continue;
                }
            }
            if(this.xv > 10){
                this.xv = 10;
            }
            if(this.xv < -10){
                this.xv = -10;
            }
            if(this.yv > 10){
                this.yv = 10;
            }
            if(this.yv < -10){
                this.yv = -10;
            }
            this.x += this.xv;
            this.y += this.yv;
            this.xv *= this.DAMPENING;
            this.yv *= this.DAMPENING;
            //collision
            
            //~30fps
            try{
                Thread.sleep(17);
            } catch(Exception e){}
        }
    }
}