package drawing_stuff;

import java.io.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.image.*;

public class Tree extends JComponent{
    Tree[] children;
    Tree parent;
    int x, y, w, h;

    public Tree(int x, int y, int w, int h){
        this.x = x; this.y = y; this.w = w; this.h = h;
        this.parent = null;
    }
    
    public void setParent(Tree t){
        this.parent = t;
    }

    public void draw(Graphics2D canvas){
        System.out.println(getMaxArea());
        canvas.setColor(Color.getHSBColor(this.getArea()/this.getMaxArea(),1,1));
        canvas.fillRect(x, y, w, h);
        if(this.getArea() > 1600){
            branch();
        }
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
        for(Tree child : children){
            child.setParent(this);
        }
    }
    
    public Tree getParent(){
        return this.parent;
    }
    
    public Tree[] getChildren(){
        return this.children;
    }
    
    public boolean hasParent(){
        return this.parent != null;
    }
    
    public boolean hasChildren(){
        return this.children != null;
    }
    
    public int getArea(){
        return this.w * this.h;
    }
    
    public int getMaxArea(){
        Tree t = this;
        while(t.hasParent()){
            t = t.getParent();
        }
        return t.getArea();
    }
    
    public int getDepth(){
        Tree t = this;
        int depth = 1;
        
        if(!this.hasChildren()) return depth;
        while(t.hasChildren()){
            int temp = 0;
            for(Tree child : children){
                if(child.getDepth() > temp) temp = child.getDepth();
            }
            depth = 1 + temp;
        }
        return depth;
    }
}