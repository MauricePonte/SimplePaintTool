package com.simplePaintTool.mvc;

import com.simplePaintTool.PaintingFrame;
import com.simplePaintTool.commands.AddShapeCommand;
import com.simplePaintTool.commands.Command;
import com.simplePaintTool.fileIO.LoadFile;
import com.simplePaintTool.fileIO.SaveFile;
import com.simplePaintTool.shapes.Shape;
import com.simplePaintTool.strategy.EllipseStrat;
import com.simplePaintTool.strategy.RectangleStrat;
import com.simplePaintTool.strategy.drawStrat;

import java.awt.*;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.FileNotFoundException;
import java.util.Stack;

public class PaintController {
    private PropertyChangeSupport propertyChangeSupport;
    private PaintModel model;
    private PaintingFrame frame;

    drawStrat rectangleStrat;
    drawStrat ellipseStrat;

    private Stack<Command> commands;
    private Stack<Command> undoCommands;

    public PaintController(PaintModel model, PaintingFrame frame) {
        this.model = model;
        this.frame = frame;

        propertyChangeSupport = new PropertyChangeSupport(this);
        // Get strategy pattern through singleton
        rectangleStrat = RectangleStrat.getInstance();
        ellipseStrat = EllipseStrat.getInstance();

        commands = new Stack<>();
        undoCommands = new Stack<>();
    }

    private void executeCommand(Command c){
        c.execute();
        commands.push(c);
        if(!commands.isEmpty()){
            propertyChangeSupport.firePropertyChange("undoBtn on", false,true);
        }
        frame.getView().repaint();
    }

    public void undoCommand(){
        // Haal de eerste command op de stack op en unexecute deze
        commands.peek().unexecute();
        // Haal deze van de commandStack af, en zet deze op de undoCommandsStack
        undoCommands.push(commands.pop());
        // Fire property change via observer pattern
        // De command stack is nu leeg dus moet deze button niet gebruikbaar meer zijn
        if(undoCommands.isEmpty()){
            propertyChangeSupport.firePropertyChange("undoBtn off",false,true );
        }
        if (undoCommands.size() == 1) {
            propertyChangeSupport.firePropertyChange("redoBtn on", false, true);
        }
        // Repaint de shapes
        frame.getView().repaint();
    }

    public void redoCommand(){
        // Haal de eerste command op de stack op en unexecute deze
        undoCommands.peek().execute();
        // Haal deze van de commandStack af, en zet deze op de undoCommandsStack
        commands.push(undoCommands.pop());
        // Fire property change via observer pattern
        // De command stack is nu leeg dus moet deze button niet gebruikbaar meer zijn
        if(commands.isEmpty()){
            propertyChangeSupport.firePropertyChange("commandStack is empty",false,true );
        }
        // Repaint de shapes
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

    public void SaveFileClicked() throws FileNotFoundException {
        SaveFile saveFile = new SaveFile("TestFile",this.model);
        saveFile.save();
    }

    public void loadSaveFile() throws FileNotFoundException {
        this.model.clearCanvas();
        LoadFile loadfile = new LoadFile("TestFile",this.model,this.rectangleStrat,this.ellipseStrat);
        Stack<Command> loadedCommands = loadfile.load();
        while(loadedCommands.size() > 0){
            executeCommand(loadedCommands.lastElement());
            loadedCommands.remove(loadedCommands.lastElement());
        }
    }





    // Listener die luisterd naar de veranderingen in deze class
    public void addPropertyChangedListener(PropertyChangeListener propertyChangeListener) {
        propertyChangeSupport.addPropertyChangeListener(propertyChangeListener);
    }
}