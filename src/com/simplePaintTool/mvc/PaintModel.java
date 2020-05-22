package com.simplePaintTool.mvc;

import com.simplePaintTool.shapes.Shape;

import java.util.ArrayList;

public class PaintModel {
    private ArrayList<Shape> shapes;

    public PaintModel(){
        shapes = new ArrayList<Shape>();
    }

    /**
     * Add a shape to the model
     * @param s shape to add
     */
    public void addShape(Shape s){
        shapes.add(s);
    }

    /**
     * Remove a shape from the model
     * @param s shape to remove
     */
    public void removeShape(Shape s){
        shapes.remove(s);
    }

    /**
     * Return all shapes in this model
     * @return All shapes
     */
    public ArrayList<Shape> getAllShapes(){
        return shapes;
    }

    public void clearCanvas(){
        this.shapes.clear();
    }
}
