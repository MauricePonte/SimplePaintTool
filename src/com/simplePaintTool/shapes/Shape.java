package com.simplePaintTool.shapes;

import com.simplePaintTool.commands.Command;
import com.simplePaintTool.strategy.drawStrat;

import java.awt.*;
import java.util.ArrayList;

public class Shape {
    private Color color = Color.BLACK;

    private ArrayList<Shape> children;
    private boolean selected;
    private drawStrat drawStrategy;

    private int x,y,width,height;

    public Shape(Point startPoint, Point endPoint,drawStrat drawStrategy) {
        this.drawStrategy = drawStrategy;
        this.children = new ArrayList<>();
        setAfmetingen(startPoint,endPoint);
    }

    private void setAfmetingen(Point start,Point end){
        if(start.x < end.x) this.x = start.x; else this.x = end.x;
        if(start.y < end.y) this.y = start.y; else this.y = end.y;
        this.width = Math.abs(start.x - end.x);
        this.height = Math.abs(start.y - end.y);
    }

    public void draw(Graphics graphics){
        drawStrategy.draw(graphics,this);
        for (Shape s:children) {
            s.draw(graphics);
        }
    }

    public void selected(Graphics graphics){

    }

    public boolean containsClick(int xCoordinate, int yCoordinate){

        return true;
    };

    public Color getColor() {
        return this.color;
    }
    public void setColor(Color color) {
        this.color = color;
    }

    public boolean getSelected(){ return selected; }
    public void setSelected(boolean s){ this.selected = s; }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
