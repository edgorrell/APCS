package jframe_thing;

import java.io.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.image.*;
import java.awt.event.*;

public class Keyboard{
    public static ArrayList<Character> keysPressed;
    public static Character lastKey;
    public Keyboard(JFrame frame){
        keysPressed = new ArrayList<Character>();
        frame.addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent e){
                char c = e.getKeyChar();
                int num = e.getKeyCode();
                if(keysPressed.contains(c)){ return; }
                keysPressed.add(c);
                lastKey = c;
                System.out.println(keysPressed);
            }
            public void keyReleased(KeyEvent e){
                char c = e.getKeyChar();
                int num = e.getKeyCode();
                keysPressed.remove(keysPressed.indexOf(c));
            }
        });
    }
}