package com.simplePaintTool.commands;

import com.simplePaintTool.mvc.PaintModel;
import com.simplePaintTool.shapes.Shape;

public class RemoveShapeCommand implements Command{
    private final Shape shape;
    private final PaintModel model;

    public RemoveShapeCommand(Shape shape, PaintModel model,int groupID) {
        this.shape = shape;
        this.model = model;
    }

    @Override
    public void execute() {
        model.removeShape(this.shape,this.shape.getParent());
    }

    @Override
    public void unexecute() {
        model.addShape(this.shape,this.shape.getParent());
    }

}
