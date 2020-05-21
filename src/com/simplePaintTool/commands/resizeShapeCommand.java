package com.simplePaintTool.commands;

import com.simplePaintTool.shapes.Shape;

import java.util.List;

public class resizeShapeCommand implements Command{
    private Shape shape;
    private int newHeight, newWidth;

    resizeShapeCommand(int newHeight,int newWidth,Shape s){
        this.shape = s;
        this.newHeight = newHeight;
        this.newWidth = newWidth;
    }

    @Override
    public void execute() {
        shape.setHeight(shape.getHeight() + this.newHeight);
        shape.setWidth(shape.getWidth() + this.newWidth);
    }

    @Override
    public void unexecute() {
        shape.setHeight(shape.getHeight() - this.newHeight);
        shape.setWidth(shape.getWidth() - this.newWidth);
    }

}
