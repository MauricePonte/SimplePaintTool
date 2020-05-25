package com.simplePaintTool.decorator;

import com.simplePaintTool.DrawingObjectVisitor.ObjectVisitor;
import com.simplePaintTool.shapes.DrawingObject;
import com.simplePaintTool.shapes.Group;
import com.simplePaintTool.shapes.Ornament;
import com.simplePaintTool.shapes.Shape;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class RightOrnamentDecorator extends ShapeDecorator{
    private Ornament toBeAdded;

    public RightOrnamentDecorator(DrawingObject shape, Ornament ornament){
        super(shape,ornament);
        toBeAdded = ornament;
    }

    @Override
    public void draw(Graphics graphics) {
        shape.draw(graphics);
        addOrnament(graphics);
    }


    @Override
    public int getX(){
        return shape.getX();
    }
    @Override
    public int getY(){
        return shape.getY();
    }
    @Override
    public int getWidth(){
        return shape.getWidth();
    }
    @Override
    public int getHeight(){
        return shape.getHeight();
    }

    private void addOrnament(Graphics graphics){
        int x = shape.getX() + shape.getWidth() + 2;
        int y = shape.getY() + (shape.getHeight() /2);

        toBeAdded.setX(x);
        toBeAdded.setY(y);
        //shape.setOrnament(ornament);
        toBeAdded.draw(graphics);
    }



}
