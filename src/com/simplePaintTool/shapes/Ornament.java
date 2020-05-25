package com.simplePaintTool.shapes;

import com.simplePaintTool.DrawingObjectVisitor.ObjectVisitor;
import com.simplePaintTool.shapes.DrawingObject;
import com.simplePaintTool.shapes.Group;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class Ornament extends DrawingObject {
    String text;
    String pos;
    int x,y;

    public Ornament(String text,String pos) {
        this.text = text;
        this.pos = pos;
    }

    public String getPos() {
        return pos;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int stringBuffer(){
        return text.length()*2;
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.drawString(text,x,y);
    }

    @Override
    public void select() {

    }

    @Override
    public void deselect() {

    }

    @Override
    public String toString() {
        return "ornament " + this.pos + " " + text;
    }

    @Override
    public Group getParent() {
        return null;
    }

    @Override
    public DefaultListModel<DrawingObject> getListInput() {
        return null;
    }

    @Override
    public List<DrawingObject> getCommandListInput() {
        return null;
    }

    @Override
    public DrawingObject getShape() {
        return this;
    }

    @Override
    public void accept(ObjectVisitor visitor) throws IOException {

    }
}
