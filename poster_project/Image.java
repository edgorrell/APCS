package poster_project;

import java.io.*;
import java.awt.*;
import java.util.*;
import java.awt.geom.*;
import javax.imageio.*;
import java.awt.image.*;

public abstract class Image{
    public static int getBaseType() throws IOException {
        return getBase().getType();
    }
    
    public static int getBaseWidth() throws IOException {
        return getBase().getWidth();
    }
    
    public static int getBaseHeight() throws IOException {
        return getBase().getWidth();
    }
    
    public static int getBaseMax() throws IOException {
        return max(getBaseWidth(),getBaseHeight());
    }
    
    public static BufferedImage getBase() throws IOException {
        return ImageIO.read(new File("poster_project/images/pipe.png"));
    }
    
    public static BufferedImage getBaseBG() throws IOException {
        return ImageIO.read(new File("poster_project/images/pipe-bg.png"));
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
    
    public static Color difference(Color c1, Color c2){
        return new Color(
            (int)(Math.abs(c1.getRed()-c2.getRed())),
            (int)(Math.abs(c1.getGreen()-c2.getGreen())),
            (int)(Math.abs(c1.getBlue()-c2.getBlue()))
        );
    }
    
    public static Color multiply(Color c, double r){
        return new Color((int)(r*c.getRed()),(int)(r*c.getGreen()),(int)(r*c.getBlue()));
    }
    
    public static Color average(Color c1, Color c2){
        return new Color(
            (c1.getRed()+c2.getRed())/2,
            (c1.getGreen()+c2.getGreen())/2,
            (c1.getBlue()+c2.getBlue())/2
        );
    }
    
    // mult = amount c1 is timed by
    public static Color averageWeighted(Color c1, Color c2, double mult){
        double a = mult, b = 1 - a;
        Color c = new Color(
            (int)(c1.getRed()*a + c2.getRed()*b),
            (int)(c1.getGreen()*a + c2.getGreen()*b),
            (int)(c1.getBlue()*a + c2.getBlue()*b)
        );
        return c;
    }
    
    // h = [0,360], s = [0,1], v = [0,1]
    public static Color getHSV(float h, float s, float v){
        return Color.getHSBColor(h/360,s,v);
    }
    
    public static Color getPixel(BufferedImage img, int x, int y){
        return new Color(img.getRGB(x,y));
    }
    
    public static void setPixel(Graphics2D g, int x, int y, Color c){
        g.setColor(c);
        g.fillRect(x,y,1,1);
    }
    
    public static BufferedImage tint(BufferedImage img, Color c, double mult, double tolerance){
        int max = getMax(img);
        BufferedImage newImg = new BufferedImage(max,max,img.getType());
        Graphics2D g = newImg.createGraphics();
        tolerance = 1 - tolerance;
        for(int x = 0; x < max; x++){
            for(int y = 0; y < max; y++){
                Color temp = getPixel(img,x,y);
                if(getValue(temp) > tolerance){
                    setPixel(g,x,y,averageWeighted(c,temp,mult));
                }
            }
        }
        g.dispose();
        return newImg;
    }
    
    public static BufferedImage resize(BufferedImage img, int width, int height){
        BufferedImage newImage = new BufferedImage(width,height,img.getType());
        Graphics2D g = newImage.createGraphics();
        g.drawImage(img,Math.abs(img.getWidth()-width)/2,Math.abs(img.getHeight()-height)/2,null);
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
    
    public static void setAlpha(Graphics2D g, float a){
        AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,a);
        g.setComposite(ac);
    }
}
