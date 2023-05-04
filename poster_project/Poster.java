package poster_project;

import java.io.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.image.*;

public class Poster{
    static BufferedImage base, temp, poster;
    static Graphics2D canvas;
    static int tileX = 5, tileY = 5, width, height;
    
    public static void main(String[] args) throws IOException{
        base = ImageIO.read(new File("poster_project/images/pipe.png"));
        width = base.getWidth();
        height = base.getHeight();
        poster = new BufferedImage(width*tileX,height*tileY,base.getType());
        canvas = (Graphics2D) poster.createGraphics();
        
        int i = 1; 
        for(int x = 0; x < tileX; x++){
            for(int y = 0; y < tileY; y++){
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
                        
                        //break;
                }
                canvas.drawImage(base,null,x*width,y*height);
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
    
    public static Color getColor(BufferedImage img, int x, int y){
        return new Color(img.getRGB(x,y));
    }
    
    public static void setColor(BufferedImage img, Color c, int x, int y){
        img.getGraphics().setColor(c);
        img.getGraphics().fillRect(x,y,x+1,y+1);
    }
    
    // https://stackoverflow.com/questions/23457754/how-to-flip-bufferedimage-in-java
    // use for "better" flips/mirrors
    
    public BufferedImage mirrorDownAboutMiddle(BufferedImage base){
        BufferedImage img = new BufferedImage(base.getWidth(),base.getHeight(),base.getType());
        
        for(int x = 0; x < base.getWidth(); x++){
            for(int y = 0; y < base.getHeight()/2;y++){
                setColor(base,getColor(base,x,y),x,height-y-1);
            }
        }
        
        return img;
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