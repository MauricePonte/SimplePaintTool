package com.simplePaintTool.commands;
import com.simplePaintTool.mvc.PaintModel;
import com.simplePaintTool.shapes.Shape;

public class AddShapeCommand implements Command {
    private final Shape shape;
    private final PaintModel model;

    public AddShapeCommand(Shape shape, PaintModel model) {
        this.shape = shape;
        this.model = model;
        shape.setPrimaryObject(true);
    }

    @Override
    public void execute() {
        this.model.addShape(this.shape,this.shape.getParent());
    }

    @Override
    public void unexecute() {
        this.model.removeShape(this.shape,this.shape.getParent());
    }


}
