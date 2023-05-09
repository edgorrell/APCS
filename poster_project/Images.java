package poster_project;

import java.io.*;
import java.awt.*;
import java.util.*;
import java.awt.geom.*;
import javax.imageio.*;
import java.awt.image.*;

public class Images{
    public static BufferedImage getImage(BufferedImage img, int num){
        return null;
    }
    public static void Temple(){
        BufferedImage temple = ImageIO.read(new File("poster_project/images/temple.png"));
        BufferedImage sub = temple.getSubImage(15,30,260,60);
        //(16,87),(276,29) so its (16,29) with w = 260 and h = 60
        sub = Poster.scale(sub,-1,1);
        Graphics2D canvas = temple.createGraphics();
        canvas.drawImage(sub,15,30,null);
        ImageIO.write(temple,"png",new File("poster_project/images/temple_fixed.png"));
    }
}
