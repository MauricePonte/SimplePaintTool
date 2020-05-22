package com.simplePaintTool.strategy;

import com.simplePaintTool.shapes.Shape;

import java.awt.*;

public class EllipseStrat implements drawStrat{

    @Override
    public void draw(Graphics g, Shape s) {
        g.drawOval(s.getX(),s.getY(),s.getWidth(),s.getHeight());
    }

    @Override
    public String toString(){
        return "ellipse";
    }
}
