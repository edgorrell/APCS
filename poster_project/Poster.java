package poster_project;

import java.io.*;
import java.awt.*;
import java.util.*;
import java.awt.geom.*;
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
            img = rotate(getBase(),1280,1280,360*Math.random());
            img = resize(img,1280,1280);
            canvas.drawImage(
                img,
                (int)(tileX*getBaseWidth()*Math.random())-getBaseWidth()/2,
                (int)(tileY*getBaseHeight()*Math.random())-getBaseHeight()/2,
                null
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
    
    public static BufferedImage resize(BufferedImage img, int width, int height){
        BufferedImage newImage = new BufferedImage(width,height,img.getType());
        newImage.createGraphics().drawImage(img,Math.abs(img.getWidth()-width)/2,Math.abs(img.getHeight()-height)/2,null);
        return newImage;
    }
    
    // theta is in degrees
    // rotates about origin
    public static BufferedImage rotate(BufferedImage img, int nWidth, int nHeight, double theta){
        double rads = Math.toRadians(-theta);
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
    
    public static BufferedImage scale(BufferedImage img, int dx, int dy){
        AffineTransform at = new AffineTransform();
        at.concatenate(AffineTransform.getScaleInstance(dx, dy));
        at.concatenate(AffineTransform.getTranslateInstance(0, -img.getHeight()));
        return transform(img, at);
    }
    
    public static BufferedImage transform(BufferedImage img, AffineTransform at){
        BufferedImage newImage = new BufferedImage(
            img.getWidth(), img.getHeight(),
            BufferedImage.TYPE_INT_ARGB
        );
        Graphics2D g = newImage.createGraphics();
        g.transform(at);
        g.drawImage(img, 0, 0, null);
        g.dispose();
        return newImage;
    }
}