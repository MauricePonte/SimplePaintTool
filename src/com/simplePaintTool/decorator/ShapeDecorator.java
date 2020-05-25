package com.simplePaintTool.decorator;

import com.simplePaintTool.DrawingObjectVisitor.ObjectVisitor;
import com.simplePaintTool.shapes.DrawingObject;
import com.simplePaintTool.shapes.Group;
import com.simplePaintTool.shapes.Ornament;
import com.simplePaintTool.shapes.Shape;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public abstract class ShapeDecorator extends DrawingObject {
    protected DrawingObject shape;
    protected Ornament[] ornaments = new Ornament[4];
    protected Ornament ornamentje;

    public ShapeDecorator(DrawingObject s, Ornament ornament){
        this.shape = s;
        ornamentje = ornament;
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
    public String toString() {
        String returnString = "";
        if(this.getParent() != null){
            for(int i = 0; i <= this.getParent().getGroupID();i++){
                returnString += "  ";
            }
        }
        return returnString + ornamentje.toString();
    }

    @Override
    public Group getParent() {
        return shape.getParent();
    }

    @Override
    public DefaultListModel<DrawingObject> getListInput() {
        DefaultListModel<DrawingObject> placeholder = new DefaultListModel<>();
        if(shape instanceof Shape){

        }
        placeholder.addElement(this);
        DefaultListModel<DrawingObject> wat = shape.getListInput();
        for(int i = 0 ; i < wat.size();i++){
            placeholder.addElement(wat.get(i));
        }

        return placeholder;
    }

    @Override
    public List<DrawingObject> getCommandListInput() {
        List<DrawingObject> list = new ArrayList<>();
        list.add(this);
        return list;
    }

    @Override
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
