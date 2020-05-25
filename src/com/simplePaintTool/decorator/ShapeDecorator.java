package com.simplePaintTool.decorator;

import com.simplePaintTool.shapes.DrawingObject;
import com.simplePaintTool.shapes.Group;
import com.simplePaintTool.shapes.Ornament;

import javax.swing.*;
import java.util.List;


public abstract class ShapeDecorator extends DrawingObject {
    protected DrawingObject shape;
    protected Ornament[] ornaments = new Ornament[4];

    public ShapeDecorator(DrawingObject s, Ornament ornament){
        this.shape = s;
        if(ornament.getPos().equals("top")){
            ornaments[0] = ornament;
        }else if(ornament.getPos().equals("bottom")){
            ornaments[1] = ornament;
        }else if(ornament.getPos().equals("left")){
            ornaments[2] = ornament;
        }else if(ornament.getPos().equals("right")){
            ornaments[3] = ornament;
        }

    }

    @Override
    public void select() {
        shape.select();
    }

    @Override
    public void deselect() {
        shape.deselect();
    }

    @Override
    public String toString(int identation) {
        return shape.toString(identation);
    }

    @Override
    public Group getParent() {
        return shape.getParent();
    }

    @Override
    public DefaultListModel<DrawingObject> getListInput() {
        return shape.getListInput();
    }

    @Override
    public List<DrawingObject> getCommandListInput() {
        return shape.getCommandListInput();
    }

    @Override
    public DrawingObject getShape() {
        return shape;
    }
}
