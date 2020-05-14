package com.simplePaintTool.mvc;

import com.simplePaintTool.PaintingFrame;
import com.simplePaintTool.commands.Command;

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

}
