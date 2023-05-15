package poster_project;

import java.io.*;
import java.awt.*;
import java.util.*;
import java.awt.geom.*;
import javax.imageio.*;
import java.awt.image.*;

public class Poster extends Image{
    final static int type = getBaseType();
    static int tileX = 3, tileY = 2;
    
    public static void main(String[] args) throws IOException{
        int max = getMax(getBaseBG());
        BufferedImage poster = new BufferedImage(
            tileX*max,
            tileY*max,
            getBaseType()
        );
        Graphics2D canvas = poster.createGraphics();
        canvas.setColor(Color.WHITE);
        canvas.fillRect(0,0,tileX*getBaseWidth(),tileY*getBaseHeight());
        
        int i = 1;
        for(int x = 0; x < tileX; x++){
            for(int y = 0; y < tileY; y++){
                canvas.drawImage(getImage(i,true),x*max,y*max,null);
                i++;
            }
        }
        
        new File("poster_project/images/poster.png").delete();
        ImageIO.write(poster,"png",new File("poster_project/images/poster.png"));
    }
    
    public static BufferedImage getImage(int num, boolean hasBG){
        BufferedImage base;
        if(hasBG){ base = getBaseBG(); }
        else base = getBase();
        int max = getMax(base);
        BufferedImage img = resize(base,max,max);
        switch(num){
            case 1: return img1(img);
            case 2: return img2(img);
            case 3: return img3(img);
            case 4: return img4(img);
            case 5: return img5(img);
            case 6: return img6(img);
        }
        return null;
    }
    
    public static BufferedImage img1(BufferedImage img){
        return img;
    }
    
    public static BufferedImage img2(BufferedImage img){
        int max = getMax(img);
        BufferedImage newImg = new BufferedImage(img.getWidth(),img.getHeight(),img.getType());
        Graphics2D g = newImg.createGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0,0,max,max);
        for(int x = 125; x < max-125; x++){
            for(int y = 300; y < max-300; y++){
                Color c = getColor(img,x,y); if(c.equals(Color.WHITE)){continue;};
                Color hsv = getHSV((float)360*x/(max-250),1,getValue(c));
                g.setColor(hsv);
                g.fillRect(x,y,1,1);
            }
        }
        g.dispose();
        return newImg;
    }
    
    public static BufferedImage img3(BufferedImage img){
        BufferedImage newImg = new BufferedImage(img.getWidth(),img.getHeight(),img.getType());
        Graphics2D g = newImg.createGraphics(); 
        g.drawImage(img,0,0,null);
        int max = Poster.getMax(img);
        BufferedImage left = img.getSubimage(0,0,max/2,max),
                      right = img.getSubimage(max/2,0,max/2,max);
        right = Poster.scale(right,-1,1);
        g.drawImage(right,0,0,null);
        BufferedImage half = newImg.getSubimage(0,0,max,max/2);
        half = Poster.scale(half,1,-1);
        g.drawImage(half,0,max/2,null);
        g.dispose();
        return newImg;
    }
    
    public static BufferedImage img4(BufferedImage img){
        return img;
    }
    
    public static BufferedImage img5(BufferedImage img){
        return img;
    }
    
    public static BufferedImage img6(BufferedImage img){
       return img;
    }
    
    public static void Temple() throws IOException {
        BufferedImage temple = ImageIO.read(new File("poster_project/images/temple.png"));
        BufferedImage sub = temple.getSubimage(15,30,260,60);
        //(16,87),(276,29) so its (16,29) with w = 260 and h = 60
        sub = Poster.scale(sub,-1,1);
        Graphics2D canvas = temple.createGraphics();
        canvas.drawImage(sub,275,30,null);
        ImageIO.write(temple,"png",new File("poster_project/images/temple_fixed.png"));
    }
}