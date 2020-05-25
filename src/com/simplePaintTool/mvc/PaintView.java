package com.simplePaintTool.mvc;

import com.simplePaintTool.shapes.DrawingObject;
import com.simplePaintTool.shapes.Shape;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PaintView extends JPanel {
    private PaintModel model;
    public PaintView(){}

    public void setModel(PaintModel model) { this.model = model; }
    /**
     * When the model changes, this function will paint all shapes. Triggerd by PaintController.java
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if(model != null){
            /*
            ArrayList<DrawingObject> shapes = model.getAllShapes();
            for(DrawingObject s : shapes){s.draw(g);}
             */
            model.GetMainGroup().draw(g);
        }
    }
}
