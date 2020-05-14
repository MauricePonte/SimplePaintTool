package com.simplePaintTool.mvc;

import com.simplePaintTool.shapes.Shape;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PaintView extends JPanel {

    private JPanel mainPanel;
    private Color color = Color.BLACK; // TODO refactor?

    private JToggleButton tglBtnDrawRectanlge;
    private JToggleButton tglBtnDrawEclipse;

    private JButton btnUndo;
    private JButton btnRedo;

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
            ArrayList<Shape> shapes = model.getAllShapes();
            for(Shape s : shapes){s.draw(g);}
        }
    }
}
