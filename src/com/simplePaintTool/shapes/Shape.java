package com.simplePaintTool.shapes;

import com.simplePaintTool.drawingObjectVisitor.ObjectVisitor;


import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Shape extends DrawingObject {
    private Color color = Color.BLACK;

    private final com.simplePaintTool.strategy.drawStrategy drawStrategy;
    private final Group parent;


    public Shape(Point startPoint, Point endPoint, com.simplePaintTool.strategy.drawStrategy drawStrategy, Group parent) {
        this.drawStrategy = drawStrategy;
        setMeasurements(startPoint,endPoint);
        this.parent = parent;
    }

    private void setMeasurements(Point start, Point end){
        super.setX(Math.min(start.x, end.x));
        super.setY(Math.min(start.y, end.y));
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
    }

    @Override
    public void deselect(){
        this.setColor(Color.black);
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public String toString(){
        return "  ".repeat(Math.max(0, this.parent.groupID + 1)) // Eerst indentation herhalen aanvankelijk van welke groupID hij heeft
                + drawStrategy.toString() + " " + super.getX() + " " + super.getY() + " " + super.getWidth() + " " + super.getHeight(); // Daarna data wegschrijven
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
