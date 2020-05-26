package com.simplePaintTool.strategy;

import com.simplePaintTool.shapes.Shape;

import java.awt.*;

public class EllipseStrategy implements drawStrategy {
    // Instance of singleton pattern
    private static final drawStrategy instance = new EllipseStrategy();
    // Method to get instance (Singleton pattern)
    public static drawStrategy getInstance() {
        return instance;
    }
    @Override
    public void draw(Graphics g, Shape s) {
        g.drawOval(s.getX(),s.getY(),s.getWidth(),s.getHeight());
    }

    @Override
    public String toString(){
        return "ellipse";
    }
}

