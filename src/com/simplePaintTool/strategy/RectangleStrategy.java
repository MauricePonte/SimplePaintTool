package com.simplePaintTool.strategy;

import com.simplePaintTool.shapes.DrawingObject;
import com.simplePaintTool.shapes.Shape;

import java.awt.*;

public class RectangleStrategy implements drawStrategy {
    // Instance of singleton pattern
    private static drawStrategy instance = new RectangleStrategy();
    // Method to get instance (Singleton pattern)
    public static drawStrategy getInstance() {
        return instance;
    }

    @Override
    public void draw(Graphics g, Shape s) {
        g.drawRect(s.getX(),s.getY(),s.getWidth(),s.getHeight());
    }

    @Override
    public String toString(){
        return "rectangle";
    }

}
