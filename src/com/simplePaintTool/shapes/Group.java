package com.simplePaintTool.shapes;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Group implements DrawingObject {

    List<DrawingObject> ChildrenObjects = new ArrayList<>();
    boolean selected;

    @Override
    public void draw(Graphics graphics) {
        for(DrawingObject d:ChildrenObjects){
            d.draw(graphics);
        }
    }

    @Override
    public void select() {
        this.selected = true;
        for(DrawingObject d:ChildrenObjects){
            d.select();
        }
    }

    @Override
    public void deselect() {

    }

    @Override
    public String toString(int identation){
        String toWrite = "leeg";
        return toWrite;
    }

}
