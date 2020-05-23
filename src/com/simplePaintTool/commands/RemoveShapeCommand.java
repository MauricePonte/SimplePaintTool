package com.simplePaintTool.commands;

import com.simplePaintTool.mvc.PaintModel;
import com.simplePaintTool.shapes.DrawingObject;
import com.simplePaintTool.shapes.Shape;

public class RemoveShapeCommand implements Command{
    private Shape shape;
    private PaintModel model;
    private int groupID;

    public RemoveShapeCommand(Shape shape, PaintModel model,int groupID) {
        this.shape = shape;
        this.model = model;
        this.groupID = groupID;
    }

    @Override
    public void execute() {
        //model.removeShape(this.shape,this.groupID);
        model.removeShape2(this.shape,this.shape.getParent());
    }

    @Override
    public void unexecute() {
        //model.addShape(this.shape,this.groupID);
        model.addShape2(this.shape,this.shape.getParent());
    }

}
