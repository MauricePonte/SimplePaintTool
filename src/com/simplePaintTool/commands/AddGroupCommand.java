package com.simplePaintTool.commands;

import com.simplePaintTool.mvc.PaintModel;
import com.simplePaintTool.shapes.DrawingObject;
import com.simplePaintTool.shapes.Group;

public class AddGroupCommand implements Command{
    private Group group;
    private PaintModel model;

    public AddGroupCommand(Group group,PaintModel model){
        this.model = model;
        this.group = group;
        group.setPrimaryObject(true);
    }

    @Override
    public void execute() {
        this.model.addShape2(this.group,this.group.getParent());
    }

    @Override
    public void unexecute() {
        this.model.removeShape2(this.group,this.group.getParent());
    }

}
