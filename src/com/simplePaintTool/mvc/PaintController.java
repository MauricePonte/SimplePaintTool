package com.simplePaintTool.mvc;

import com.simplePaintTool.PaintingFrame;
import com.simplePaintTool.commands.AddShapeCommand;
import com.simplePaintTool.commands.Command;
import com.simplePaintTool.shapes.Shape;
import com.simplePaintTool.strategy.EllipseStrat;
import com.simplePaintTool.strategy.RectangleStrat;

import java.awt.*;

import java.util.Stack;

public class PaintController {
    private PaintModel model;
    private PaintingFrame frame;
    private EllipseStrat ellipseStrat = new EllipseStrat();
    private RectangleStrat rectangleStrat = new RectangleStrat();
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
        Shape s = new Shape(p[0],p[1],rectangleStrat);
        executeCommand(new AddShapeCommand(s,model));
    }
    public void btnAddEllipseClicked(Point[] p) {
        Shape s = new Shape(p[0],p[1],ellipseStrat);
        executeCommand(new AddShapeCommand(s,model));
    }
}
