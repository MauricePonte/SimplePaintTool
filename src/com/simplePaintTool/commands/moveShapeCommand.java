package com.simplePaintTool.commands;

import com.simplePaintTool.DrawingObjectVisitor.MoveObjectVisitor;
import com.simplePaintTool.DrawingObjectVisitor.ObjectVisitor;
import com.simplePaintTool.DrawingObjectVisitor.UnMoveObjectVisitor;
import com.simplePaintTool.shapes.DrawingObject;
import com.simplePaintTool.shapes.Shape;

import java.awt.*;
import java.io.IOException;
import java.util.List;

public class moveShapeCommand implements Command{
    private DrawingObject shape;
    private ObjectVisitor doVisitor;
    private ObjectVisitor undoVisitor;

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
