package com.simplePaintTool.shapes;

import com.simplePaintTool.shapes.Shape;

import java.awt.*;

public class Elipse extends Shape {

    public Elipse(Point startPoint, Point endPoint) {
        super(startPoint, endPoint);
    }

    @Override
    public void draw(Graphics graphics) {

    }

    @Override
    public void selected(Graphics graphics) {

    }

    @Override
    public boolean containsClick(int xCoordinate, int yCoordinate) {
        return false;
    }
}
