package com.simplePaintTool.shapes;

import java.awt.*;
import java.util.ArrayList;

public abstract class Shape {
    private Color color = Color.BLACK;
    private ArrayList<Shape> children;
    private boolean selected;

    protected Point startPoint;
    protected Point endPoint;

    public Shape(Point startPoint, Point endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    public abstract void draw(Graphics graphics);
    public abstract void selected(Graphics graphics);
    public abstract boolean containsClick(int xCoordinate, int yCoordinate);

    public Color getColor() {
        return this.color;
    }
    public void setColor(Color color) {
        this.color = color;
    }

    public boolean getSelected(){ return selected; }
    public void setSelected(boolean s){ this.selected = s; }
}
