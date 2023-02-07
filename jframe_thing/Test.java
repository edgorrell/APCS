package jframe_thing;

import java.io.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.image.*;
import java.awt.event.*;

public class Test extends JComponent implements Runnable{
    String str = "";
    int x = 20, y = 20;
    public Test(){
        
    }
    public void draw(Graphics2D frame){
        frame.drawString(str,100,100);    
    }
    public void run(){
        str = "" + Keyboard.lastKey;
    }
}