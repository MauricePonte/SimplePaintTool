package com.simplePaintTool.shapes;

import com.simplePaintTool.DrawingObjectVisitor.ObjectVisitor;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public abstract class DrawingObject {
    private int x,y,width,height;
    private boolean primaryObject;

    public void draw(Graphics graphics){};
    public abstract void select();
    public abstract void deselect();
    public abstract String toString();
    public abstract Group getParent();
    public abstract DefaultListModel<DrawingObject> getListInput();
    public abstract List<DrawingObject> getCommandListInput();
    public abstract DrawingObject getShape();
    public abstract void accept(ObjectVisitor visitor) throws IOException;

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
    public boolean getPrimaryObject(){return primaryObject;}
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
    public void setPrimaryObject(boolean Primary){this.primaryObject = Primary;};
}
