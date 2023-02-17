package jframe_thing;

import java.io.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.image.*;
import java.awt.event.*;

public class Player extends JComponent implements Runnable, GameObject{
    Point2D[] points;
    Color color;
    GameObject following = null;
    double friction = 0.15;
    double x = 20, y = 20, w = 50, h = 50;
    double xv, yv, xres, yres;
    int delay = 0;
    public Player(Color color, double x, double y, double w, double h){
        this.color = color;
        this.x = x; this.y = y;
        this.w = w; this.h = h;
        this.xv = 0; this.yv = 0;
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
    public void follow(GameObject obj, int delay){
        this.following = obj;
        this.delay = delay;
    }
    public void draw(Graphics2D frame){
        frame.setColor(this.color);
        frame.fillRect((int)(this.x),(int)(this.y),(int)(this.w),(int)(this.h));
    }
    public boolean collidesWith(GameObject obj){
        Point2D[] points = obj.getPoints();
        for(Point2D point : points){
            if(point.getX() > this.x && point.getX() < this.x + this.w &&
               point.getY() > this.y && point.getY() < this.y + this.h){
                return true;
            }
        }
        return false;
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
            this.xres = Math.pow(Math.abs(this.xv/10),10); this.xres = 1 - this.xres;
            this.yres = Math.pow(Math.abs(this.yv/10),10); this.yres = 1 - this.yres;
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
            this.xv *= 1 - this.friction;
            this.yv *= 1 - this.friction;
            //collision
            
            //~30fps
            try{
                Thread.sleep(17);
            } catch(Exception e){}
        }
    }
}