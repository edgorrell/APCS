package jframe_thing;

import java.io.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.image.*;
import java.awt.event.*;

public class Point{
    private double x,y;
    public Point(){
        this.x = 0;
        this.y = 0;
    }
    public Point(double x, double y){
        this.x = x;
        this.y = y;
    }
    public Point(Point point){
        this.x = point.x;
        this.y = point.y;
    }
    public double getX(){ return this.x; }
    public double getY(){ return this.y; }
    public void translate(double dx, double dy){
        this.x += dx;
        this.y += dy;
    }
    public double distance(Point point){
        double xdis = this.x - point.x;
        double ydis = this.y - point.y;
        double dis =  Math.sqrt(Math.pow(xdis,2) + Math.pow(ydis,2));
        return dis;
    }
    public boolean equals(Object obj){
        Point point = (Point) obj;
        return this.x == point.x && this.y == point.y;
    }
    public String toString(){
        return "(" + this.x + "," + this.y + ")";
    }
}