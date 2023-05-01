package poster_project;

import java.io.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.image.*;

public class Poster{
    static BufferedImage image, poster;
    static Graphics2D canvas;
    static int tileX = 3, tileY = 2;
    
    public static void main(String[] args) throws IOException{
        image = ImageIO.read(new File(""));
        poster = new BufferedImage(image.getWidth()*tileX,image.getHeight()*tileY,image.getType());
        canvas = (Graphics2D) poster.getGraphics();
        ImageIO.write(poster,"poster",new File("images/poster.png"));
    }
    
    public void setPixel(BufferedImage img, Color c, int x, int y){
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