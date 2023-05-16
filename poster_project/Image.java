package poster_project;

import java.io.*;
import java.awt.*;
import java.util.*;
import java.awt.geom.*;
import javax.imageio.*;
import java.awt.image.*;

public abstract class Image{
    public static int getBaseType(){
        return getBase().getType();
    }
    
    public static int getBaseWidth(){
        return getBase().getWidth();
    }
    
    public static int getBaseHeight(){
        return getBase().getWidth();
    }
    
    public static int getBaseMax(){
        return max(getBaseWidth(),getBaseHeight());
    }
    
    public static BufferedImage getBase(){
        try{
            return ImageIO.read(new File("poster_project/images/pipe.png"));
        } catch(Exception e){}
        return null;
    }
    
    public static BufferedImage getBaseBG(){
        try{
            return ImageIO.read(new File("poster_project/images/pipe-bg.png"));
        } catch(Exception e){}
        return null;
    }
    
    public static int max(int a, int b){
        return (a+b+Math.abs(a-b))/2;
    }
    
    public static int getMax(BufferedImage img){
        return max(img.getWidth(),img.getHeight());
    }
    
    public static float getValue(Color c){
        return (float)((c.getRed() + c.getGreen() + c.getBlue())/(255*3.0));
    }
    
    public static Color multiply(Color c, float r){
        return new Color(r*c.getRed(),r*c.getGreen(),r*c.getBlue());
    }
    
    public static Color average(Color c1, Color c2){
        return new Color(
            (c1.getRed()+c2.getRed())/2,
            (c1.getGreen()+c2.getGreen())/2,
            (c1.getBlue()+c2.getBlue())/2
        );
    }
    
    public static Color getHSV(float h, float s, float v){
        return Color.getHSBColor(h/360,s,v);
    }
    
    public static Color getPixel(BufferedImage img, int x, int y){
        return new Color(img.getRGB(x,y));
    }
    
    // have no idea why this doesnt work
    // works fine when copy code over
    public static void setPixel(Graphics2D g, int x, int y, Color c){
        g.setColor(c);
        g.fillRect(x,y,1,1);
    }
    
    public static void setAlpha(Graphics2D g, float a){
        AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,a);
        g.setComposite(ac);
    }
    
    public static BufferedImage resize(BufferedImage img, int width, int height){
        BufferedImage newImage = new BufferedImage(width,height,img.getType());
        newImage.createGraphics().drawImage(img,Math.abs(img.getWidth()-width)/2,Math.abs(img.getHeight()-height)/2,null);
        return newImage;
    }
    
    public static BufferedImage scale(BufferedImage img, double sx, double sy){
        AffineTransform at = new AffineTransform();
        at.concatenate(AffineTransform.getScaleInstance(sx, sy));
        if(sx < 0){
            at.concatenate(AffineTransform.getTranslateInstance(img.getWidth()*at.getScaleX(),0));
        }
        if(sy < 0){
            at.concatenate(AffineTransform.getTranslateInstance(0,img.getHeight()*at.getScaleY()));
        }
        return transform(img, at);
    }
    
    public static BufferedImage transform(BufferedImage img, AffineTransform at){
        BufferedImage newImage = new BufferedImage(
            (int)(Math.abs(img.getWidth()*at.getScaleX()))*2, 
            (int)(Math.abs(img.getHeight()*at.getScaleY()))*2,
            BufferedImage.TYPE_INT_ARGB
        );
        Graphics2D g = newImage.createGraphics();
        g.transform(at);
        g.drawImage(img,0,0,null);
        g.dispose();
        return newImage;
    }
    
    // theta is in degrees
    // rotates about origin
    public static BufferedImage rotate(BufferedImage img, double theta, int nWidth, int nHeight){
        double rads = Math.toRadians(theta);
        int width = img.getWidth();
        int height = img.getHeight();

        BufferedImage newImage = new BufferedImage(nWidth, nHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = newImage.createGraphics();
        graphics.setRenderingHint(
            RenderingHints.KEY_INTERPOLATION,
            RenderingHints.VALUE_INTERPOLATION_BICUBIC
        );

        graphics.translate((nWidth - width) / 2, (nHeight - height) / 2);
        graphics.rotate(Math.toRadians(theta), width/2.0, height/2.0);
        graphics.drawImage(img, 0, 0, null);
        graphics.dispose();

        return newImage;
    }
}
