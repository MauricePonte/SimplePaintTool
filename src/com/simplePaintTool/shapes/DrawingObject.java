package com.simplePaintTool.shapes;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public abstract class DrawingObject {
    private int x,y,width,height;

    public void draw(Graphics graphics){};
    public abstract void select();
    public abstract void deselect();
    public abstract String toString(int identation);
    public abstract Group getParent();
    public abstract DefaultListModel<DrawingObject> getListInput();
    public abstract List<DrawingObject> getCommandListInput();
    public abstract DrawingObject getShape();

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
