package com.simplePaintTool.mvc;

import com.simplePaintTool.PaintingFrame;

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
            //case "shape selected":
            //    addListener(frame.getBtnDelete(), frame.getMouseAdapterDelete());
            //    break;
            //case "shape exist":
            //    frame.getTglBtnSelect().setEnabled(true);
            //    break;
            //case "shape unselected":
            //case "shape don't exist":
            //    removeListener(frame.getBtnUpdate(), frame.getMouseAdapterUpdate());
            //    removeListener(frame.getBtnDelete(), frame.getMouseAdapterDelete());
            //    break;
            //case "update/move turn off":
            //    removeListener(frame.getBtnToBack(), frame.getMouseAdapterToBack());
            //    removeListener(frame.getBtnToFront(), frame.getMouseAdapterToFront());
            //    removeListener(frame.getBtnBringToBack(), frame.getMouseAdapterBringToBack());
            //    removeListener(frame.getBtnBringToFront(), frame.getMouseAdapterBringToFront());
            //    removeListener(frame.getBtnUpdate(), frame.getMouseAdapterUpdate());
            //    break;
            //case "update/move turn on":
            //    addListener(frame.getBtnToBack(), frame.getMouseAdapterToBack());
            //    addListener(frame.getBtnToFront(), frame.getMouseAdapterToFront());
            //    addListener(frame.getBtnBringToBack(), frame.getMouseAdapterBringToBack());
            //    addListener(frame.getBtnBringToFront(), frame.getMouseAdapterBringToFront());
            //    addListener(frame.getBtnUpdate(), frame.getMouseAdapterUpdate());
            //    break;

            //case "draw is not empty":
            //    addListener(frame.getBtnSaveDraw(), frame.getMouseAdapterSaveDrawing());
            //    addListener(frame.getBtnNewDraw(), frame.getMouseAdapterNewDraw());
            //    addListener(frame.getBtnUndo(), frame.getMouseAdapterUndo());
            //    addListener(frame.getBtnLog(), frame.getMouseAdapterLog());
            //    break;
            //case "draw is empty":
            //    removeListener(frame.getBtnSaveDraw(), frame.getMouseAdapterSaveDrawing());
            //    removeListener(frame.getBtnNewDraw(), frame.getMouseAdapterNewDraw());
            //    removeListener(frame.getBtnUndo(), frame.getMouseAdapterUndo());
            //    removeListener(frame.getBtnLog(), frame.getMouseAdapterLog());
            //    addListener(frame.getBtnEdgeColor(), frame.getMouseAdapterEdgeColor());
            //    addListener(frame.getBtnInteriorColor(), frame.getMouseAdapterInteriorColor());
            //    frame.getTglBtnDrawCircle().setEnabled(true);
            //    frame.getTglBtnDrawLine().setEnabled(true);
            //    frame.getTglBtnDrawPoint().setEnabled(true);
            //    frame.getTglBtnDrawSquare().setEnabled(true);
            //    frame.getTglBtnDrawRectangle().setEnabled(true);
            //    frame.getTglBtnDrawHexagon().setEnabled(true);
            //    break;
            //case "serialized draw opened":
            //    addListener(frame.getBtnNewDraw(), frame.getMouseAdapterNewDraw());
            //    removeListener(frame.getBtnSaveDraw(), frame.getMouseAdapterSaveDrawing());
            //    removeListener(frame.getBtnUndo(), frame.getMouseAdapterUndo());
            //    removeListener(frame.getBtnLog(), frame.getMouseAdapterLog());
            //    removeListener(frame.getBtnUpdate(), frame.getMouseAdapterUpdate());
            //    removeListener(frame.getBtnDelete(), frame.getMouseAdapterDelete());
            //    removeListener(frame.getBtnRedo(), frame.getMouseAdapterRedo());
            //    removeListener(frame.getBtnToBack(), frame.getMouseAdapterToBack());
            //    removeListener(frame.getBtnToFront(), frame.getMouseAdapterToFront());
            //    removeListener(frame.getBtnBringToBack(), frame.getMouseAdapterBringToBack());
            //    removeListener(frame.getBtnBringToFront(), frame.getMouseAdapterBringToFront());
            //    removeListener(frame.getBtnUpdate(), frame.getMouseAdapterUpdate());
            //    removeListener(frame.getBtnEdgeColor(), frame.getMouseAdapterEdgeColor());
            //    removeListener(frame.getBtnInteriorColor(), frame.getMouseAdapterInteriorColor());
            //    frame.getTglBtnDrawCircle().setEnabled(false);
            //    frame.getTglBtnDrawLine().setEnabled(false);
            //    frame.getTglBtnDrawPoint().setEnabled(false);
            //    frame.getTglBtnDrawSquare().setEnabled(false);
            //    frame.getTglBtnDrawRectangle().setEnabled(false);
            //    frame.getTglBtnDrawHexagon().setEnabled(false);
            //    frame.getTglBtnSelect().setEnabled(false);
            //    break;
    }
}
