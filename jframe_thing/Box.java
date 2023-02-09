package jframe_thing;

import java.io.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.image.*;
import java.awt.event.*;

public class Box extends JComponent implements Runnable{
    Color color = new Color(100,100,100);
    int x = 20, y = 20, w = 50, h = 50;
    public Box(){
        
    }
    public void draw(Graphics2D frame){
        frame.setColor(this.color);
        frame.fillRect(this.x+this.w/2,this.y+this.h/2,this.w,this.h);
    }
    public void run(){
        while(true){
            try{
                Keyboard.keysPressed.get(0);
                break;
            } catch(Exception e){}
        }
        while(true){
            for(String key : Keyboard.keysPressed){
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