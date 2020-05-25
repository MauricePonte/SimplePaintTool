package com.simplePaintTool.mvc;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class PaintObserver implements PropertyChangeListener {
    PaintingFrame frame;

    public PaintObserver(PaintingFrame frame){
        this.frame = frame;
    }

    // Method om een listener aan een button toe te voegen en te enablen
    public void addListener(JButton b, MouseAdapter a){
        if(!b.isEnabled()){
            b.setEnabled(true);
            b.addMouseListener(a);
        }
    }
    // Method om listener er vanaf te halen
    public void removeListener(JButton b, MouseAdapter a){
        if(b.isEnabled()){
            b.setEnabled(false);
            b.removeMouseListener(a);
        }
    }

    // Luisterd naar events van het model en geeft deze door aan de controller
    // Op deze manier passen we functionaliteit van knoppen aan, op basis van wat er in het model gebeurt
    @Override
    public void propertyChange(PropertyChangeEvent event) {
        switch(event.getPropertyName()) {
            case "undoBtn off":
                removeListener(frame.getBtnUndo(), frame.getMouseAdapterUndo());
                break;
            case "undoBtn on":
                addListener(frame.getBtnUndo(), frame.getMouseAdapterUndo());
                break;
            case "redoBtn off":
                removeListener(frame.getBtnRedo(), frame.getMouseAdapterRedo());
                break;
            case "redoBtn on":
                addListener(frame.getBtnRedo(), frame.getMouseAdapterRedo());
                break;
        }
    }
}
