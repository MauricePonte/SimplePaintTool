package com.simplePaintTool.commands;

import com.simplePaintTool.shapes.DrawingObject;
import com.simplePaintTool.shapes.Shape;

import java.awt.*;
import java.util.List;

public class moveShapeCommand implements Command{
    private List<DrawingObject> shapes;
    int newX,newY;

    public moveShapeCommand(Point[] points, List<DrawingObject> objects){
        this.shapes = objects;
        this.newX = points[1].x - points[0].x;
        this.newY = points[1].y - points[0].y;
    }

    @Override
    public void execute() {
        for(DrawingObject d:shapes){
            if(d instanceof Shape){
                ((Shape) d).setX(((Shape) d).getX() + newX);
                ((Shape) d).setY(((Shape) d).getY() + newY);
            }
        }
    }

    @Override
    public void unexecute() {
        for(DrawingObject d:shapes){
            if(d instanceof Shape){
                ((Shape) d).setX(((Shape) d).getX() - newX);
                ((Shape) d).setY(((Shape) d).getY() - newY);
            }
        }
    }

}
