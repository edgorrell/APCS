package jframe_thing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class test{
    public static Robot r;
    public static int delay = 1;
    public static void main(String[] args){
        try{
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice[] gs = ge.getScreenDevices();
            GraphicsDevice gd = gs[0];
            GraphicsConfiguration gc = gd.getConfigurations()[0];
            Rectangle bounds = gc.getBounds();
            
            //System.out.println(MouseInfo.getPointerInfo().getLocation());
            r = new Robot(gd);
            
            while(Math.random() > 0.00001){
                r.mouseMove((int)(Math.random()*bounds.width),(int)(Math.random()*bounds.height));
            }
        } catch(Exception e){}
    }
    public static void type(int keycode){
        try{
            Thread.sleep(delay);
            r.keyPress(keycode);
            Thread.sleep(delay);
            r.keyRelease(keycode);
        } catch(Exception e){}
    }
    public static void type(String str){
        try{
            for(int i = 0; i < str.length(); i++){
                Thread.sleep(delay);
                char c = str.charAt(i);
                type(KeyEvent.getExtendedKeyCodeForChar(c));
            }
        } catch (Exception e){}
    }
}
//