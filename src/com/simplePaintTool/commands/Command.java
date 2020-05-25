package com.simplePaintTool.commands;

import com.simplePaintTool.shapes.DrawingObject;

import java.io.IOException;

public interface Command {
    public void execute() throws IOException;
    public void unexecute() throws IOException;
}
