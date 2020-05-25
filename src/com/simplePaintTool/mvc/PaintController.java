package com.simplePaintTool.mvc;

import com.simplePaintTool.DrawingObjectVisitor.ObjectVisitor;
import com.simplePaintTool.DrawingObjectVisitor.SaveObjectVisitor;
import com.simplePaintTool.mvc.PaintingFrame;
import com.simplePaintTool.commands.*;
import com.simplePaintTool.decorator.*;
import com.simplePaintTool.fileIO.LoadFile;
import com.simplePaintTool.fileIO.SaveFile;
import com.simplePaintTool.shapes.DrawingObject;
import com.simplePaintTool.shapes.Group;
import com.simplePaintTool.shapes.Ornament;
import com.simplePaintTool.shapes.Shape;
import com.simplePaintTool.strategy.EllipseStrategy;
import com.simplePaintTool.strategy.RectangleStrategy;
import com.simplePaintTool.strategy.drawStrategy;

import javax.swing.*;
import java.awt.*;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;
import java.util.List;

public class PaintController {
    private PropertyChangeSupport propertyChangeSupport;
    private PaintModel model;
    private PaintingFrame frame;

    private DrawingObject selectedObject;

    private int groupCount;

    drawStrategy rectangleStrat;
    drawStrategy ellipseStrat;

    private Stack<Command> commands;
    private Stack<Command> undoCommands;

    public PaintController(PaintModel model, PaintingFrame frame) {
        this.model = model;
        this.frame = frame;

        propertyChangeSupport = new PropertyChangeSupport(this);
        // Get strategy pattern through singleton
        rectangleStrat = RectangleStrategy.getInstance();
        ellipseStrat = EllipseStrategy.getInstance();

        commands = new Stack<>();
        undoCommands = new Stack<>();
        groupCount = 0;
        this.setList(model.getList());
    }

    private void executeCommand(Command c) throws IOException {
        c.execute();
        commands.push(c);
        if(!commands.isEmpty()){
            propertyChangeSupport.firePropertyChange("undoBtn on", false,true);
        }
        this.setList(model.getList());
        frame.getView().repaint();
    }

    public void undoCommand() throws IOException {
        // Haal de eerste command op de stack op en unexecute deze
        commands.peek().unexecute();
        // Haal deze van de commandStack af, en zet deze op de undoCommandsStack
        undoCommands.push(commands.pop());
        // Fire property change via observer pattern
        // De command stack is nu leeg dus moet deze button niet gebruikbaar meer zijn
        if(commands.isEmpty()){
            propertyChangeSupport.firePropertyChange("undoBtn off",true,false );
        }
        if (!undoCommands.isEmpty()) {
            propertyChangeSupport.firePropertyChange("redoBtn on", false, true);
        }
        // Repaint de shapes
        this.setList(model.getList());
        frame.getView().repaint();
    }

    public void redoCommand() throws IOException {
        // Haal de eerste command op de stack op en unexecute deze
        undoCommands.peek().execute();
        // Haal deze van de commandStack af, en zet deze op de undoCommandsStack
        commands.push(undoCommands.pop());
        // Fire property change via observer pattern
        // De command stack is nu leeg dus moet deze button niet gebruikbaar meer zijn
        if(undoCommands.isEmpty()){
            propertyChangeSupport.firePropertyChange("redoBtn off",true,false );
        }
        if(!commands.isEmpty()){
            propertyChangeSupport.firePropertyChange("redoBtn off",true,false );
        }
        // Repaint de shapes
        this.setList(model.getList());
        frame.getView().repaint();
    }

    public void btnAddRectangleClicked(Point[] p) throws IOException {
        if(selectedObject instanceof Group){
            Shape s = new Shape(p[0],p[1],rectangleStrat,(Group) selectedObject);
            executeCommand(new AddShapeCommand(s,model));
        }else{
            System.out.println("Geen group/hoogste Ornamen van group geselecteerd");
        }
    }

    public void btnAddEllipseClicked(Point[] p) throws IOException {
        if(selectedObject instanceof Group){
            Shape s = new Shape(p[0],p[1],ellipseStrat,(Group) selectedObject);
            executeCommand(new AddShapeCommand(s,model));
        }else{
            System.out.println("Geen group/hoogste Ornamen van group geselecteerd");
        }
    }

    public void btnResizeClicked(Point[] p) throws IOException {
        executeCommand(new resizeShapeCommand(p,selectedObject));
    }

    public void btnMoveClicked(Point[] p) throws IOException {
        executeCommand(new moveShapeCommand(p,selectedObject));
    }

    public void btnAddGroupClicked() throws IOException {
        if(selectedObject instanceof Group){
            Group s = new Group(((Group) selectedObject).getGroupID()+1,(Group) selectedObject);
            executeCommand(new AddGroupCommand(s,model));
        }else{
            System.out.println("Geen group/hoogste Ornamen van group geselecteerd");
        }
    }



    // TODO here is my shit code
    public void btnAddOrnamentClicked() throws IOException {
        if(selectedObject.getPrimaryObject()) {
            OrnamentOptionsPanel ornamentOptionsPanel = new OrnamentOptionsPanel();
            ornamentOptionsPanel.setVisible(true);
            if (ornamentOptionsPanel.isConfirmed()) {
                String pos = ornamentOptionsPanel.getOrnamentPos();
                // hier creeëren we een nieuw ornament.
                Ornament ornament = new Ornament('"' + ornamentOptionsPanel.getOrnamentText() + '"', ornamentOptionsPanel.getOrnamentPos());
                ShapeDecorator newDecorator = null;
                //hier wordt op basis van een switch case een ShapeDecorator aangemaakt.
                switch (pos) {
                    case "top":
                        newDecorator = new TopOrnamentDecorator(selectedObject, ornament);
                        break;
                    case "bottom":
                        newDecorator = new BottomOrnamentDecorator(selectedObject, ornament);
                        break;
                    case "left":
                        newDecorator = new LeftOrnamentDecorator(selectedObject, ornament);
                        break;
                    case "right":
                        newDecorator = new RightOrnamentDecorator(selectedObject, ornament);
                        break;
                    default:
                        break;
                }

                if (newDecorator != null) {
                    executeCommand(new AddOrnamentCommand(newDecorator, model, selectedObject));
                }
            }
        }
    }

    public void SaveFileClicked() throws IOException {
        SaveFile saveFile = new SaveFile("TestFile",this.model);
        saveFile.save();
    }

    public void loadSaveFile() throws IOException {
        this.model.clearCanvas();
        LoadFile loadfile = new LoadFile("TestFile",this.model,this.rectangleStrat,this.ellipseStrat);
        //Stack<Command> loadedCommands = loadfile.load();
        Stack<Command> loadedCommands = loadfile.initialGroup();
        while(loadedCommands.size() > 0){
            executeCommand(loadedCommands.firstElement());
            loadedCommands.remove(loadedCommands.firstElement());
        }
        System.out.println("The file has been loaded");
    }

    //functies voor de JList om objecten te adden removen en deselecteren.
    public void setList(DefaultListModel<DrawingObject> list){
        frame.setList(list);
    }

    public void deselectAll(){
        this.model.deselectAllObjects();
    }

    public void setSelectedObject(DrawingObject d){
        this.selectedObject = d;
    }
    //functies voor de JList om objecten te adden removen en deselecteren.



    // Listener die luisterd naar de veranderingen in deze class
    public void addPropertyChangedListener(PropertyChangeListener propertyChangeListener) {
        propertyChangeSupport.addPropertyChangeListener(propertyChangeListener);
    }


}