package spin;

import java.io.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.geom.*;
import java.awt.image.*;

public class Picture{
    BufferedImage img;
    int theta = 0;
    public Picture(){
        try{
            img = ImageIO.read(new File("spin/funny.png"));
        } catch(Exception e){}
    }
    public void draw(Graphics2D canvas){
        theta++; theta %= 360;
        double rads = Math.toRadians(theta);
        AffineTransform at = new AffineTransform(
            Math.cos(rads),0,Math.sin(rads),
            0,1,0
        );
        BufferedImage temp = new BufferedImage(img.getWidth(),img.getHeight(),img.getType());
        Graphics2D g = temp.createGraphics();
        g.drawImage(img,0,0,null);
        g.transform(at);
        g.dispose();
        canvas.drawImage(temp,0,0,null);
    }
}