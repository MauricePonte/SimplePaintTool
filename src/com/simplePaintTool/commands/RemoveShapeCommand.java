package com.simplePaintTool.commands;

import com.simplePaintTool.mvc.PaintModel;
import com.simplePaintTool.shapes.Shape;

public class RemoveShapeCommand implements Command{
    private Shape shape;
    private PaintModel model;

    public RemoveShapeCommand(Shape shape, PaintModel model) {
        this.shape = shape;
        this.model = model;
    }

    @Override
    public void execute() {
        model.removeShape(this.shape);
    }

    @Override
    public void unexecute() {
        model.addShape(this.shape);
    }
}
