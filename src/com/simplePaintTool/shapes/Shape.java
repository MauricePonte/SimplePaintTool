package com.simplePaintTool.shapes;

import com.simplePaintTool.DrawingObjectVisitor.ObjectVisitor;


import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Shape extends DrawingObject {
    private Color color = Color.BLACK;

    private boolean selected;

    private com.simplePaintTool.strategy.drawStrategy drawStrategy;
    private Group parent;


    public Shape(Point startPoint, Point endPoint, com.simplePaintTool.strategy.drawStrategy drawStrategy, Group parent) {
        this.drawStrategy = drawStrategy;
        setMeasurements(startPoint,endPoint);
        this.parent = parent;
    }

    private void setMeasurements(Point start, Point end){
        if(start.x < end.x) super.setX(start.x); else super.setX(end.x);
        if(start.y < end.y) super.setY(start.y); else super.setY(end.y);
        super.setWidth(Math.abs(start.x - end.x));
        super.setHeight(Math.abs(start.y - end.y));
    }

    @Override
    public void draw(Graphics graphics){
        graphics.setColor(this.color);
        drawStrategy.draw(graphics,this);
    }

    @Override
    public void select(){
        this.setColor(Color.red);
        this.selected = true;
    }

    @Override
    public void deselect(){
        this.setColor(Color.black);
        this.selected = false;
    }

    public com.simplePaintTool.strategy.drawStrategy getDrawStrategy(){
        return drawStrategy;
    }

    public Color getColor() {
        return this.color;
    }
    public void setColor(Color color) {
        this.color = color;
    }



    @Override
    public String toString(){
        String returnString = "";
        for(int i = 0; i <= this.parent.groupID;i++){
            returnString += "  ";
        }
        returnString += drawStrategy.toString() + " " + super.getX() + " " + super.getY() + " " + super.getWidth() + " " + super.getHeight();
        return returnString;
    }

    @Override
    public Group getParent(){
        return this.parent;
    }

    @Override
    public DefaultListModel<DrawingObject> getListInput(){
        DefaultListModel<DrawingObject> placeholder = new DefaultListModel<>();
        placeholder.addElement(this);
        return placeholder;
    }

    @Override
    public java.util.List<DrawingObject> getCommandListInput() {
        List<DrawingObject> list = new ArrayList<>();
        list.add(this);
        return list;
    }

    //accept voor ObjectVisitor
    @Override
    public void accept(ObjectVisitor visitor) throws IOException {
        visitor.visit(this);
    }

    @Override
    public DrawingObject getShape() {
        return this;
    }
}
