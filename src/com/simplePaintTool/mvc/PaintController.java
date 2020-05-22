package com.simplePaintTool.mvc;

import com.simplePaintTool.PaintingFrame;
import com.simplePaintTool.commands.AddShapeCommand;
import com.simplePaintTool.commands.Command;
import com.simplePaintTool.commands.resizeShapeCommand;
import com.simplePaintTool.fileIO.LoadFile;
import com.simplePaintTool.fileIO.SaveFile;
import com.simplePaintTool.shapes.Shape;
import com.simplePaintTool.strategy.EllipseStrat;
import com.simplePaintTool.strategy.RectangleStrat;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.io.FileNotFoundException;
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
        Shape s = new Shape(p[0],p[1],new RectangleStrat());
        executeCommand(new AddShapeCommand(s,model));
    }

    public void btnAddEllipseClicked(Point[] p) {
        Shape s = new Shape(p[0],p[1],new EllipseStrat());
        executeCommand(new AddShapeCommand(s,model));

    }

    public void SaveFileClicked() throws FileNotFoundException {
        SaveFile saveFile = new SaveFile("TestFile",this.model);
        saveFile.save();
    }


    public void loadSaveFile() throws FileNotFoundException {
        this.model.clearCanvas();
        LoadFile loadfile = new LoadFile("TestFile",this.model);
        Stack<Command> loadedCommands = loadfile.load();
        while(loadedCommands.size() > 0){
            executeCommand(loadedCommands.lastElement());
            loadedCommands.remove(loadedCommands.lastElement());
        }
    }
}
