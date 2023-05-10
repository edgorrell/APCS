package poster_project;

import java.io.*;
import java.awt.*;
import java.util.*;
import java.awt.geom.*;
import javax.imageio.*;
import java.awt.image.*;

public class Poster{
    final static int type = getBaseType();
    static int tileX = 8, tileY = 8;
    
    public static void main(String[] args) throws IOException{
        BufferedImage poster = new BufferedImage(
            tileX*getBaseWidth(),
            tileY*getBaseHeight(),
            getBaseType()
        );
        Graphics2D canvas = poster.createGraphics();
        BufferedImage img = getBase();
        Graphics2D g = img.createGraphics();
        int i;
                
        canvas.setColor(Color.WHITE);
        canvas.fillRect(0,0,tileX*getBaseWidth(),tileY*getBaseHeight());
        Color[] colors = {Color.RED,Color.GREEN,Color.BLUE,Color.YELLOW};
        for(int x = 0; x < tileX; x++){
            for(int y = 0; y < tileY; y++){
                img = rotate(getBase(),getBaseWidth(),getBaseHeight(),360*Math.random());
                g = img.createGraphics();
                canvas.drawImage(img,x*getBaseWidth(),y*getBaseHeight(),null);
                g.dispose();
            }
        }
        for(int x = 0; x < tileX; x++){
            for(int y = 0; y < tileY; y++){
                setAlpha(canvas,(float)Math.random());
                canvas.setColor(new Color(
                    (int)(255*Math.random()),
                    (int)(255*Math.random()),
                    (int)(255*Math.random()))
                );
                canvas.fillRect(
                    x*getBaseWidth(),y*getBaseHeight(),
                    getBaseWidth(),getBaseHeight()
                );
            }
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
        return getBase().getWidth();
    }
    
    public static BufferedImage getBase(){
        try{
            return ImageIO.read(new File("poster_project/images/pipe.png"));
        } catch(Exception e){}
        return null;
    }
    
    public Color getColor(BufferedImage img, int x, int y){
        return new Color(img.getRGB(x,y));
    }
    
    public void setColor(Color c, int x, int y){
        
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
    
    // theta is in degrees
    // rotates about origin
    public static BufferedImage rotate(BufferedImage img, int nWidth, int nHeight, double theta){
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
    
    public static BufferedImage scale(BufferedImage img, double sx, double sy){
        AffineTransform at = new AffineTransform();
        at.concatenate(AffineTransform.getScaleInstance(sx, sy));
        if(sx < 0){
            at.concatenate(AffineTransform.getTranslateInstance(-img.getWidth(),0));
        }
        if(sy < 0){
            at.concatenate(AffineTransform.getTranslateInstance(0,-img.getHeight()));
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
}
