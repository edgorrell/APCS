package poster_project;

import java.io.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.image.*;

public class Poster{
    static final BufferedImage base = getBase();
    static BufferedImage poster, img;
    static Graphics2D canvas, temp;
    static int tileX = 1, tileY = 1;
    static final int type = getBase().getType();
    
    public static void main(String[] args) throws IOException{
        poster = new BufferedImage(base.getWidth()*tileX,base.getHeight()*tileY,base.getType());
        canvas = (Graphics2D) poster.createGraphics();
        
        int i = 1; 
        for(int x = 0; x < tileX; x++){
            for(int y = 0; y < tileY; y++){
                img = getBase();
                temp = img.createGraphics();
                switch(i){
                    case 1:
                        //temp = Image1(base);
                        //break;
                    case 2:
                        
                        //break;
                    case 3:
                        
                        //break;
                    case 4:
                        
                        //break;
                    case 5:
                        
                        //break;
                    case 6:
                        mirrorHorizontal(base,temp);
                        //break;
                }
                canvas.drawImage(img,null,x*base.getWidth(),y*base.getHeight());
                i++;
            }
        }
        new File("poster_project/images/poster.png").delete();
        ImageIO.write(poster,"png",new File("poster_project/images/poster.png"));
    }
    
    public static BufferedImage Image1(BufferedImage base){
        BufferedImage img = new BufferedImage(base.getWidth(),base.getHeight(),base.getType());
        return img;
    }
    
    public static BufferedImage Image2(BufferedImage base){
        BufferedImage img = new BufferedImage(base.getWidth(),base.getHeight(),base.getType());
        return img;
    }
    
    public static BufferedImage Image3(BufferedImage base){
        BufferedImage img = new BufferedImage(base.getWidth(),base.getHeight(),base.getType());
        return img;
    }
    
    public static BufferedImage Image4(BufferedImage base){
        BufferedImage img = new BufferedImage(base.getWidth(),base.getHeight(),base.getType());
        return img;
    }
    
    public static BufferedImage Image5(BufferedImage base){
        BufferedImage img = new BufferedImage(base.getWidth(),base.getHeight(),base.getType());
        return img;
    }
    
    public static BufferedImage Image6(BufferedImage base){
        BufferedImage img = new BufferedImage(base.getWidth(),base.getHeight(),base.getType());
        return img;
    }
    
    public static BufferedImage getBase(){
        try{
            return ImageIO.read(new File("poster_project/images/pipe.png"));
        } catch(Exception e){}
        return null;
    }
    
    public static Color getColor(BufferedImage base, int x, int y){
        return new Color(base.getRGB(x,y));
    }
    
    public static void setColor(Graphics2D canvas, Color c, int x, int y){
       canvas.setColor(c);
       canvas.fillRect(x,y,x+1,y+1);
    }
    
    // https://stackoverflow.com/questions/23457754/how-to-flip-bufferedimage-in-java
    // use for "better" flips/mirrors
    
    public static Graphics2D mirrorHorizontal(BufferedImage base, Graphics2D canvas){
        for(int x = 0; x < base.getWidth()/2; x++){
            for(int y = 0; y < base.getHeight(); y++){
                setColor(canvas,getColor(base,x,y),base.getWidth()-x-1,y);
            }
        }
        return canvas;
    }
    
    // theta is in degrees
    // rotates about origin
    public static BufferedImage rotate(BufferedImage img, double theta, int nWidth, int nHeight){
        int width = img.getWidth();
        int height = img.getHeight();

        BufferedImage rotatedImage = new BufferedImage(nWidth, nHeight, BufferedImage.TYPE_INT_ARGB);

        Graphics2D graphics = rotatedImage.createGraphics();

        graphics.setRenderingHint(
            RenderingHints.KEY_INTERPOLATION,
            RenderingHints.VALUE_INTERPOLATION_BICUBIC
        );

        graphics.translate((nWidth - width) / 2, (nHeight - height) / 2);
        graphics.rotate(Math.toRadians(theta), width/2.0, height/2.0);
        graphics.drawImage(img,0,0,null);
        graphics.dispose();

        return rotatedImage;
    }
}