package com.simplePaintTool.decorator;

import com.simplePaintTool.shapes.DrawingObject;
import com.simplePaintTool.shapes.Group;
import com.simplePaintTool.shapes.Ornament;
import com.simplePaintTool.shapes.Shape;

import javax.swing.*;
import java.awt.*;
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


    private void addOrnament(Graphics graphics){
        int x = shape.getX() + (shape.getWidth() / 2);
        int y = shape.getY();

        toBeAdded.setX(x);
        toBeAdded.setY(y);
        //shape.setOrnament(ornament);
        toBeAdded.draw(graphics);
    }



}
