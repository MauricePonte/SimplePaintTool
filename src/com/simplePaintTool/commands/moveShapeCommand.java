package com.simplePaintTool.commands;

import com.simplePaintTool.shapes.Shape;

public class moveShapeCommand implements Command{
    private Shape shape;
    private int newX, newY;
    private int oldX, oldY;

    public moveShapeCommand(int newX, int newY, Shape s){
        this.shape = s;
        this.newX = newX;
        this.newY = newY;
        this.oldX = this.shape.getX();
        this.oldY = this.shape.getY();
    }

    @Override
    public void execute() {
        this.shape.setX(this.newX);
        this.shape.setY(this.newY);
    }

    @Override
    public void unexecute() {
        this.shape.setX(this.oldX);
        this.shape.setY(this.oldY);
    }
}
