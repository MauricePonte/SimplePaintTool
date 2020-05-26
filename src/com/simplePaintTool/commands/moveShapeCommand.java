package com.simplePaintTool.commands;

import com.simplePaintTool.drawingObjectVisitor.MoveObjectVisitor;
import com.simplePaintTool.drawingObjectVisitor.ObjectVisitor;
import com.simplePaintTool.drawingObjectVisitor.UnMoveObjectVisitor;
import com.simplePaintTool.shapes.DrawingObject;

import java.awt.*;
import java.io.IOException;

public class moveShapeCommand implements Command{
    private final DrawingObject shape;
    private final ObjectVisitor doVisitor;
    private final ObjectVisitor undoVisitor;

    public moveShapeCommand(Point[] points, DrawingObject object){
        this.shape = object;
        int newX = points[1].x - points[0].x;
        int newY = points[1].y - points[0].y;
        this.doVisitor = new MoveObjectVisitor(newX,newY);
        this.undoVisitor = new UnMoveObjectVisitor(newX,newY);
    }

    @Override
    public void execute() throws IOException {
        this.shape.accept(doVisitor);
    }

    @Override
    public void unexecute() throws IOException {
        this.shape.accept(undoVisitor);
    }

}
