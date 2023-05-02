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
    static int tileX = 3, tileY = 2;
    
    public static void main(String[] args) throws IOException{
        File f = new File("images/pipe.jpg");
        base = ImageIO.read(f);
        poster = new BufferedImage(base.getWidth()*tileX,base.getHeight()*tileY,base.getType());
        canvas = (Graphics2D) poster.getGraphics();
        ImageIO.write(poster,"poster",new File("images/poster.png"));
        
        int index = 1; 
        for(int x = 0; x < tileX; x++){
            for(int y = 0; y < tileY; y++){
                switch(index){
                    case 1:
                        //temp = Image1(base);
                        break;
                    case 2:
                        
                        break;
                    case 3:
                        
                        break;
                    case 4:
                        
                        break;
                    case 5:
                        
                        break;
                    case 6:
                        temp = base;
                        break;
                }
                canvas.drawImage(temp,null,x*base.getWidth(),y*base.getHeight());
                index++;
            }
        }
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
    
    public static Color getPixel(BufferedImage img, int x, int y){
        return new Color(img.getRGB(x,y));
    }
    
    public static void setPixel(BufferedImage img, Color c, int x, int y){
        img.getGraphics().setColor(c);
        img.getGraphics().fillRect(x,y,x+1,y+1);
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