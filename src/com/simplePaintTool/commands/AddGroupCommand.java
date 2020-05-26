package com.simplePaintTool.commands;

import com.simplePaintTool.mvc.PaintModel;
import com.simplePaintTool.shapes.Group;

public class AddGroupCommand implements Command{
    private final Group group;
    private final PaintModel model;

    public AddGroupCommand(Group group,PaintModel model){
        this.model = model;
        this.group = group;
        group.setPrimaryObject(true);
    }

    @Override
    public void execute() {
        this.model.addShape(this.group,this.group.getParent());
    }

    @Override
    public void unexecute() {
        this.model.removeShape(this.group,this.group.getParent());
    }

}
