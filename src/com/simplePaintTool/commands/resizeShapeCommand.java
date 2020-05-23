package com.simplePaintTool.commands;

import com.simplePaintTool.shapes.DrawingObject;
import com.simplePaintTool.shapes.Shape;

import java.awt.*;
import java.util.List;

public class resizeShapeCommand implements Command{
    private List<DrawingObject> shapes;
    private int newHeight, newWidth;

    public resizeShapeCommand(Point[] points, List<DrawingObject> objects){
        this.shapes = objects;
        this.newHeight = points[1].y - points[0].y;
        this.newWidth = points[1].x - points[0].x;
    }

    @Override
    public void execute() {
        for(DrawingObject s: shapes){
            if(s instanceof Shape){
                int width = ((Shape) s).getWidth() + this.newWidth;
                int height = ((Shape) s).getHeight() + this.newHeight;

                if(width <= 10) width = 10;
                if(height <= 10) height = 10;

                ((Shape) s).setWidth(width);
                ((Shape) s).setHeight(height);

            }
        }
    }

    @Override
    public void unexecute() {
        for(DrawingObject s: shapes){
            if(s instanceof Shape){
                ((Shape) s).setHeight(((Shape) s).getHeight() - this.newHeight);
                ((Shape) s).setWidth(((Shape) s).getWidth() - this.newWidth);
            }
        }
    }


}
