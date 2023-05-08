package poster_project;

import java.io.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.image.*;

public class Poster{
    static int tileX = 15, tileY = 15;
    
    public static void main(String[] args) throws IOException{
        BufferedImage poster = new BufferedImage(getBaseWidth()*tileX,getBaseHeight()*tileY,getBaseType());
        Graphics2D canvas = poster.createGraphics();
        BufferedImage img;
        
        canvas.setColor(Color.WHITE);
        canvas.fillRect(0,0,tileX*getBaseWidth(),tileY*getBaseHeight());
        for(int i = 0; i < 500; i++){
            img = rotate(getBase(),360*Math.random(),1280,1280);
            canvas.drawImage(
                img,null,
                (int)(tileX*getBaseWidth()*Math.random())-getBaseWidth()/2,
                (int)(tileY*getBaseHeight()*Math.random())-getBaseHeight()/2
            );
        }
        
        new File("poster_project/images/poster.png").delete();
        ImageIO.write(poster,"png",new File("poster_project/images/poster.png"));
    }
    
    public static int getBaseType(){
        return getBase().getType();
    }
    
    public static int getBaseWidth(){
        return getBase().getWidth();
    }
    
    public static int getBaseHeight(){
        return getBase().getHeight();
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