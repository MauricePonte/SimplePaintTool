package com.simplePaintTool.shapes;

import com.simplePaintTool.shapes.Shape;

import java.awt.*;

public class Rectangle extends Shape {

    private int width,height;

    public Rectangle(Point startPoint,Point endPoint){
        super(startPoint,endPoint);
        width = endPoint.x - startPoint.y;
        height = endPoint.x - startPoint.x;
    }


    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(getColor());
        graphics.drawRect(startPoint.x,startPoint.y,width,height); // TODO check
        //fillUpShape(graphics);
        if(getSelected()){
            selected(graphics);
        }
    }

    @Override
    public void selected(Graphics graphics) {

    }

    @Override
    public boolean containsClick(int xCoordinate, int yCoordinate) {
        return false;
    }
}
