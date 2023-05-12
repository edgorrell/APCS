package poster_project;

import java.io.*;
import java.awt.*;
import java.util.*;
import java.awt.geom.*;
import javax.imageio.*;
import java.awt.image.*;

public class Images{
    public static BufferedImage getImage(BufferedImage base, int num){
        int max = Poster.max(base.getWidth(),base.getHeight());
        BufferedImage img = Poster.resize(base,max,max);
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
    public static void Temple() throws IOException {
        BufferedImage temple = ImageIO.read(new File("poster_project/images/temple.png"));
        BufferedImage sub = temple.getSubimage(15,30,260,60);
        //(16,87),(276,29) so its (16,29) with w = 260 and h = 60
        sub = Poster.scale(sub,-1,1);
        Graphics2D canvas = temple.createGraphics();
        canvas.drawImage(sub,275,30,null);
        ImageIO.write(temple,"png",new File("poster_project/images/temple_fixed.png"));
    }
    public static BufferedImage img1(BufferedImage img){
        return img;
    }
    public static BufferedImage img2(BufferedImage img){
        int max = Poster.max(img.getWidth(),img.getHeight());
        BufferedImage right = img.getSubimage(max/2,0,max/2,max);
        right = Poster.scale(right,-1,1);
        img.createGraphics().drawImage(right,max/2,0,null);
        return img;
    }
    public static BufferedImage img3(BufferedImage img){
        return img;
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
}
