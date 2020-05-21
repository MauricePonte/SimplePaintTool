package com.simplePaintTool.mvc;

import com.simplePaintTool.PaintingFrame;
import com.simplePaintTool.commands.AddShapeCommand;
import com.simplePaintTool.commands.Command;
import com.simplePaintTool.shapes.Rectangle;
import com.simplePaintTool.shapes.Shape;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.ArrayList;
import java.util.Stack;

public class PaintController {
    private PaintModel model;
    private PaintingFrame frame;
    private Stack<Command> commands;
    private Stack<Command> undoCommands;

    public PaintController(PaintModel model, PaintingFrame frame) {
        this.model = model;
        this.frame = frame;

        commands = new Stack<>();
        undoCommands = new Stack<>();
    }

    private void executeCommand(Command c){
        c.execute();
        commands.push(c);

        frame.getView().repaint();
    }

    public void btnAddRectangleClicked(Point[] p) {
        Shape s = new Rectangle(p[0],p[1]);
        executeCommand(new AddShapeCommand(s,model));
    }
}
