package jframe_thing;

import java.io.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.image.*;
import java.awt.event.*;

public class Box extends JComponent implements Runnable, GameObject{
    Point2D[] points;
    Color color;
    double x = 20, y = 20, w = 50, h = 50;
    public Box(Color color, double x, double y, double w, double h){
        this.color = color;
        this.x = x; this.y = y;
        this.w = w; this.h = h;
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
    public void run(){}
}