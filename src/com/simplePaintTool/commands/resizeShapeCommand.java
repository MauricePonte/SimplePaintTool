package com.simplePaintTool.commands;

import com.simplePaintTool.DrawingObjectVisitor.ObjectVisitor;
import com.simplePaintTool.DrawingObjectVisitor.ResizeObjectVisitor;
import com.simplePaintTool.DrawingObjectVisitor.UnResizeObjectVisitor;
import com.simplePaintTool.shapes.DrawingObject;
import com.simplePaintTool.shapes.Shape;

import java.awt.*;
import java.io.IOException;
import java.util.List;

public class resizeShapeCommand implements Command{
    private DrawingObject shape;
    private ObjectVisitor doVisitor;
    private ObjectVisitor undoVisitor;

    public resizeShapeCommand(Point[] points, DrawingObject object){
        this.shape = object;
        int newHeight = points[1].y - points[0].y;
        int newWidth = points[1].x - points[0].x;
        this.doVisitor = new ResizeObjectVisitor(newHeight,newWidth);
        this.undoVisitor = new UnResizeObjectVisitor(newHeight,newWidth);
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
