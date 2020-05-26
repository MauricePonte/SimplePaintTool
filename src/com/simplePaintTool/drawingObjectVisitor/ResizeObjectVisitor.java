package com.simplePaintTool.drawingObjectVisitor;

import com.simplePaintTool.decorator.ShapeDecorator;
import com.simplePaintTool.shapes.Group;
import com.simplePaintTool.shapes.Shape;

public class ResizeObjectVisitor implements ObjectVisitor{

    private final int newHeight;
    private final int newWidth;

    public ResizeObjectVisitor(int newHeight,int newWidth){
        this.newHeight = newHeight;
        this.newWidth = newWidth;
    }

    @Override
    public void visit(Group g) {

    }

    @Override
    public void visit(Shape s) {
        int width =  s.getWidth() + this.newWidth;
        int height = s.getHeight() + this.newHeight;

        if(width <= 10) width = 10;
        if(height <= 10) height = 10;

        s.setWidth(width);
        s.setHeight(height);
    }

    @Override
    public void visit(ShapeDecorator s) {

    }

}
