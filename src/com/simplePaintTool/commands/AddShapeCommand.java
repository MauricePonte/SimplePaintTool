package com.simplePaintTool.commands;
import com.simplePaintTool.mvc.PaintModel;
import com.simplePaintTool.shapes.DrawingObject;
import com.simplePaintTool.shapes.Group;
import com.simplePaintTool.shapes.Shape;

public class AddShapeCommand implements Command {
    private Shape shape;
    private PaintModel model;

    public AddShapeCommand(Shape shape, PaintModel model) {
        this.shape = shape;
        this.model = model;
        shape.setPrimaryObject(true);
    }

    @Override
    public void execute() {
        this.model.addShape2(this.shape,this.shape.getParent());
    }

    @Override
    public void unexecute() {
        this.model.removeShape2(this.shape,this.shape.getParent());
    }


}
