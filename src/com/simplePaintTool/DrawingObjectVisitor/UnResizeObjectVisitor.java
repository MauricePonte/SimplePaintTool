package com.simplePaintTool.DrawingObjectVisitor;

import com.simplePaintTool.shapes.Group;
import com.simplePaintTool.shapes.Shape;

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
}
