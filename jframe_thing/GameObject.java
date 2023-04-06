package jframe_thing;

import java.io.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.image.*;
import java.awt.event.*;

public interface GameObject{
    public Point[] getPoints();
    public boolean collidesWith(GameObject obj);
}