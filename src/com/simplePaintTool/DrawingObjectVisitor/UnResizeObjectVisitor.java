package com.simplePaintTool.DrawingObjectVisitor;

import com.simplePaintTool.decorator.ShapeDecorator;
import com.simplePaintTool.shapes.DrawingObject;
import com.simplePaintTool.shapes.Group;
import com.simplePaintTool.shapes.Ornament;
import com.simplePaintTool.shapes.Shape;

import java.io.IOException;

public class UnResizeObjectVisitor implements ObjectVisitor{
    private int newHeight,newWidth;

    public UnResizeObjectVisitor(int newHeight,int newWidth){
        this.newHeight = newHeight;
        this.newWidth = newWidth;
    }

    @Override
    public void visit(Group g) {

    }

    @Override
    public void visit(Shape s) {
        s.setHeight(s.getHeight() - this.newHeight);
        s.setWidth(s.getWidth() - this.newWidth);
    }

    @Override
    public void visit(ShapeDecorator s) throws IOException {

    }


}
