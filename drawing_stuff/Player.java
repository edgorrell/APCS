package drawing_stuff;

import java.io.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.image.*;
import java.awt.event.*;

public class Player implements Runnable{
    int x, y, size, speed;
    
    public Player(int x, int y, int size, int speed){
        this.x = x; this.y = y;
        this.speed = speed;
        this.size = size;
    }
    
    public Point getPos(){
        return new Point(this.x,this.y);
    }
    
    public void draw(Graphics2D canvas){
        canvas.fillRect(x,y,x+2,y+2);
    }
    
    public void run(){
        while(true){
            try{
                EventManager.keysPressed.get(0);
                break;
            } catch(Exception e){}
        }
        while(true){
            ArrayList<String> keysPressed = new ArrayList<String>(EventManager.keysPressed);
            for(String key : keysPressed){
                if(key.contains(EventManager.keyStart + "A")){
                    this.x -= speed;
                    continue;
                }
                if(key.contains(EventManager.keyStart + "D")){
                    this.x += speed;
                    continue;
                }
                if(key.contains(EventManager.keyStart + "W")){
                    this.y -= speed;
                    continue;
                }
                if(key.contains(EventManager.keyStart + "S")){
                    this.y += speed;
                    continue;
                }
            }
            if(x < 0) x = 0;
            if(x > size) x = size;
            if(y < 0) y = 0;
            if(y > size) y = size;
            try{
                Thread.sleep(17);
            } catch (Exception e){}
        }
    }
}