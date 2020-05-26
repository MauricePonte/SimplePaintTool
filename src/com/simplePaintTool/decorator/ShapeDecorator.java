package com.simplePaintTool.decorator;

import com.simplePaintTool.drawingObjectVisitor.ObjectVisitor;
import com.simplePaintTool.shapes.DrawingObject;
import com.simplePaintTool.shapes.Group;
import com.simplePaintTool.shapes.Ornament;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public abstract class ShapeDecorator extends DrawingObject {
    protected final DrawingObject shape;
    //protected final Ornament[] ornaments = new Ornament[4];
    protected final Ornament ornamentToAdd;

    public ShapeDecorator(DrawingObject s, Ornament ornament){
        this.shape = s;
        this.ornamentToAdd = ornament;

        //TODO Dit was het plan.
        /*
        switch (ornament.getPos()) {
            case "top":
                ornaments[0] = ornament;
                break;
            case "bottom":
                ornaments[1] = ornament;
                break;
            case "left":
                ornaments[2] = ornament;
                break;
            case "right":
                ornaments[3] = ornament;
                break;
        }
         */
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
    public String toString() {
        StringBuilder returnString = new StringBuilder();
        if(this.getParent() != null){
            returnString.append("  ".repeat(Math.max(0, this.getParent().getGroupID() + 1)));
        }
        return returnString + ornamentToAdd.toString();
    }

    @Override
    public Group getParent() {
        return shape.getParent();
    }

    @Override
    public DefaultListModel<DrawingObject> getListInput() {
        DefaultListModel<DrawingObject> placeholder = new DefaultListModel<>();
        placeholder.addElement(this);
        DefaultListModel<DrawingObject> wat = shape.getListInput();
        for(int i = 0 ; i < wat.size();i++){
            placeholder.addElement(wat.get(i));
        }

        return placeholder;
    }


    public List<DrawingObject> getCommandListInput() {
        List<DrawingObject> list = new ArrayList<>();
        list.add(this);
        return list;
    }


    public DrawingObject getShape() {
        return shape;
    }

    //accept voor ObjectVisitor
    @Override
    public void accept(ObjectVisitor visitor) throws IOException {
        visitor.visit(this);
        shape.accept(visitor);
    }
}
