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

        boolean[] hasBG = {true,true,false,true,false,false};
        int i = 1;
        for(int x = 0; x < tileX; x++){
            for(int y = 0; y < tileY; y++){
                canvas.drawImage(getImage(i,hasBG[i-1]),x*max,y*max,null);
                i++;
            }
        }
        setAlpha(canvas,0.2f);
        BufferedImage bg = ImageIO.read(new File("poster_project/images/sus.png"));
        bg = scale(bg,
            (double)max/bg.getWidth(),
            (double)max/bg.getHeight()
        );
        canvas.drawImage(bg,0,0,null);
        ImageIO.write(poster,"png",new File("poster_project/images/poster.png"));
    }

    public static BufferedImage getImage(int num, boolean hasBG){
        BufferedImage base;
        if(hasBG){
            base = getBaseBG();
        } else {
            base = getBase();
        }
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
        BufferedImage newImg = new BufferedImage(max,max,img.getType());
        Graphics2D g = newImg.createGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0,0,max,max);
        for(int x = 125; x < max-125; x++){
            for(int y = 300; y < max-300; y++){
                Color c = getPixel(img,x,y);
                float v = getValue(c);
                if(v > 0.95){continue;};
                Color hsv = getHSV((float)360*(x-125)/(max-250),1,v);
                setPixel(g,x,y,hsv);
            }
        }
        g.dispose();
        return newImg;
    }

    public static BufferedImage img3(BufferedImage img){
        int max = getMax(img);
        BufferedImage newImg = new BufferedImage(max,max,img.getType());
        Graphics2D g = newImg.createGraphics(); 
        BufferedImage section = img.getSubimage(0,max/2,max/2,max/2);
        g.drawImage(section,0,max/2,null);
        g.drawImage(scale(section,1,-1),0,0,null);
        g.drawImage(scale(section,-1,1),max/2,max/2,null);
        g.drawImage(scale(section,-1,-1),max/2,0,null));
        return newImg;
    }

    public static BufferedImage img4(BufferedImage img){
        int max = getMax(img);
        BufferedImage newImg = new BufferedImage(max,max,img.getType());
        Graphics2D g = newImg.createGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0,0,max,max);
        g.drawImage(img,0,0,null);
        for(int x = 0; x < max; x++){
            for(int y = 0; y < max; y++){
                Color c = getPixel(newImg,x,y);
                setPixel(g,x,y,new Color(
                        255 - c.getRed(),
                        255 - c.getGreen(),
                        255 - c.getBlue()
                ));
            }
        }
        g.dispose();
        return newImg;
    }

    public static BufferedImage img5(BufferedImage img){
        int max = getMax(img);
        BufferedImage newImg = new BufferedImage(max,max,img.getType());
        Graphics2D g = newImg.createGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0,0,max,max);
        int gen = 72;
        for(int i = 0; i < gen; i++){
            BufferedImage temp = rotate(img,(double)360/gen*i,max,max);
            temp = scale(temp,(gen-i)/(double)gen,(gen-i)/(double)gen);
            g.drawImage(temp,0,0,null);
        }
        g.dispose();
        return newImg;
    }

    public static BufferedImage img6(BufferedImage img){
        int max = getMax(img);
        BufferedImage newImg = new BufferedImage(max,max,img.getType());
        Graphics2D g = newImg.createGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0,0,max,max);
        double scale = 16;
        g.drawImage(scale(scale(img,1/scale,1/scale),scale,scale),0,0,null);
        g.dispose();
        return newImg;
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
