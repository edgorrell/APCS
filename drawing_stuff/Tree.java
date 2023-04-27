package drawing_stuff;

import java.io.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.image.*;

public class Tree extends JComponent{
    Tree[] children;
    int x, y, w, h;

    public Tree(int x, int y, int w, int h){
        this.x = x; this.y = y; this.w = w; this.h = h;
    }

    public void draw(Graphics2D canvas){
        int depth = getDepth();
        canvas.setColor(Color.BLACK);
        canvas.drawRect(x, y, w, h);
        branch();
        if(this.children == null){ return; }
        for(Tree t : children){
            t.draw(canvas);
        }
    }

    public void branch(){
        this.children = null;
        int num = 0;
        for(Point p : Screen.points){
            if(p.x > this.x && p.x < this.x + this.w && 
               p.y > this.y && p.y < this.y + this.h){
                num++;
            }
        }
        if(num < 1){ return; }
        this.children = new Tree[4];
        this.children[0] = new Tree(x,y,w/2,h/2);
        this.children[1] = new Tree(x+(w/2),y,w/2,h/2);
        this.children[2] = new Tree(x+(w/2),y+(w/2),w/2,h/2);
        this.children[3] = new Tree(x,y+(w/2),w/2,h/2);
    }
    
    public int getDepth(){
        Tree t = this;
        int depth = 0;
        
        if(this.children == null) return 0;
        while(t.children != null){
            int temp = 0;
            for(Tree child : children){
                if(child.getDepth() > temp) temp = child.getDepth();
            }
            depth = 1 + temp;
        }
        return depth;
    }
}