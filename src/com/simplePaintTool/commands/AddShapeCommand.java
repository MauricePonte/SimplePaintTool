package com.simplePaintTool.commands;
import com.simplePaintTool.mvc.PaintModel;
import com.simplePaintTool.shapes.Shape;

public class AddShapeCommand implements Command {
    private Shape shape;
    private PaintModel model;

    public AddShapeCommand(Shape shape, PaintModel model) {
        this.shape = shape;
        this.model = model;
    }

    @Override
    public void execute() {
        model.addShape(this.shape);
    }

    @Override
    public void unexecute() {
        model.removeShape(this.shape);
    }
}
