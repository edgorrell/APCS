package jframe_thing;

import java.io.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.image.*;
import java.awt.event.*;

public class Keyboard{
    public static ArrayList<String> keysPressed;
    public static String keyStart = "keyText=";
    public static String unicodeStart = "primaryLevelUnicode=";
    public Keyboard(JFrame frame){
        keysPressed = new ArrayList<String>();
        frame.addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent e){
                if(keysPressed.contains(e.toString())){ return; }
                keysPressed.add(e.toString());
            }
            public void keyReleased(KeyEvent e){
                String event = e.toString();
                int id1 = event.indexOf(unicodeStart)+unicodeStart.length();
                String unicode = event.substring(id1,event.indexOf(",",id1));
                for(String str : keysPressed){
                    if(str.contains(unicodeStart + unicode)){
                        keysPressed.remove(str);
                        return;
                    }
                }
            }
            public void keyTyped(KeyEvent e){
                
            }
        });
    }
    public String getUnicode(char c){
        return String.format("\\u%04x", (int) c);
    }
    public void printKeys(){
        for(String str : keysPressed){
            System.out.println(str);
        }
        System.out.println();
    }
}