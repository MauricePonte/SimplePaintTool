package com.simplePaintTool.commands;

import com.simplePaintTool.shapes.DrawingObject;

public interface Command {
    public void execute();
    public void unexecute();
}
