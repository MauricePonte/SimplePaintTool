package com.simplePaintTool.strategy;

import com.simplePaintTool.shapes.Shape;

import java.awt.*;

public class RectangleStrat implements drawStrat{
    @Override
    public void draw(Graphics g, Shape s) {
        g.drawRect(s.getX(),s.getY(),s.getWidth(),s.getHeight());
    }

    @Override
    public String toString(Shape s){

        return "4Head";
    }

}
