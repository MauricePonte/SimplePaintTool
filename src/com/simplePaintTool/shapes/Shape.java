package com.simplePaintTool.shapes;

import com.simplePaintTool.commands.Command;
import com.simplePaintTool.strategy.drawStrat;

import java.awt.*;
import java.util.ArrayList;

public class Shape implements DrawingObject{
    private Color color = Color.BLACK;

    private boolean selected;
    private drawStrat drawStrategy;

    private int x,y,width,height;

    public Shape(Point startPoint, Point endPoint,drawStrat drawStrategy) {
        this.drawStrategy = drawStrategy;
        setAfmetingen(startPoint,endPoint);
    }

    private void setAfmetingen(Point start,Point end){
        if(start.x < end.x) this.x = start.x; else this.x = end.x;
        if(start.y < end.y) this.y = start.y; else this.y = end.y;
        this.width = Math.abs(start.x - end.x);
        this.height = Math.abs(start.y - end.y);
    }

    @Override
    public void draw(Graphics graphics){
        drawStrategy.draw(graphics,this);
    }

    @Override
    public void select(){
        this.selected = true;
    }

    @Override
    public void deselect(){
        this.selected = false;
    }


    public boolean getSelected(){
        return selected;
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

    @Override
    public String toString(int identation){
        String toWrite = drawStrategy.toString() + " " + this.x + " " + this.y + " " + this.width + " " + this.height;
        return toWrite;
    }
}
