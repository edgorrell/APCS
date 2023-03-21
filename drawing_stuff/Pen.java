package drawing_stuff;

import java.io.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.image.*;
import java.awt.event.*;

public class Pen{
    Scanner scan = new Scanner(System.in);
    double hue, angle;
    int size, radius;
    
    public Pen(int size, int radius){
        this.hue = 0;
        this.size = size;
        this.radius = radius;
        this.angle = Math.toRadians(360/size);
    }
    public void draw(Graphics2D frame){
        for(int i = 0; i < size; i++){
            frame.setColor(toRGB(hue,1,1));
            frame.fillPolygon(new int[] {256,256,256+(int)(radius*Math.cos(angle))},
                              new int[] {256,256-radius,256-(int)(radius*Math.sin(angle))},
                              3);
            frame.fillArc(256,256,size,size,90,(int)(90-angle));
            frame.rotate(angle,256,256);
            hue += 360/size;
            System.out.println(i);
            scan.nextLine();
        }
    }
    public static Color toRGB(double h, double s, double v){
        
        return new Color(0,0,0);
    }
}