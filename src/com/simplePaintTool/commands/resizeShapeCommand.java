package com.simplePaintTool.commands;

import com.simplePaintTool.drawingObjectVisitor.ObjectVisitor;
import com.simplePaintTool.drawingObjectVisitor.ResizeObjectVisitor;
import com.simplePaintTool.drawingObjectVisitor.UnResizeObjectVisitor;
import com.simplePaintTool.shapes.DrawingObject;

import java.awt.*;
import java.io.IOException;

public class resizeShapeCommand implements Command{
    private final DrawingObject shape;
    private final ObjectVisitor doVisitor;
    private final ObjectVisitor undoVisitor;

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
