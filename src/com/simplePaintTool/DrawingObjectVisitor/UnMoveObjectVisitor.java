package com.simplePaintTool.DrawingObjectVisitor;

import com.simplePaintTool.decorator.ShapeDecorator;
import com.simplePaintTool.shapes.DrawingObject;
import com.simplePaintTool.shapes.Group;
import com.simplePaintTool.shapes.Ornament;
import com.simplePaintTool.shapes.Shape;

import java.io.IOException;

public class UnMoveObjectVisitor implements ObjectVisitor{

    private int newX,newY;

    public UnMoveObjectVisitor(int newX,int newY){
        this.newX = newX;
        this.newY = newY;
    }

    @Override
    public void visit(Group g) {

    }

    @Override
    public void visit(Shape s) {
        s.setX(s.getX() - newX);
        s.setY(s.getY() - newY);
    }

    @Override
    public void visit(ShapeDecorator s) throws IOException {

    }

}
