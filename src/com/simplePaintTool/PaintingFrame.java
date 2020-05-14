package com.simplePaintTool;

import com.simplePaintTool.mvc.PaintController;
import com.simplePaintTool.mvc.PaintView;

import javax.swing.*;
import java.awt.*;

public class PaintingFrame extends JFrame {
    private JPanel mainPanel;
    private PaintView view;
    private PaintController controller;

    public PaintingFrame(){
        mainPanel = new JPanel();
        setContentPane(mainPanel);

        view = new PaintView();
        mainPanel.add(view, BorderLayout.CENTER);
    }

    public PaintView getView() {
        return view;
    }
    public void setController(PaintController controller){
        this.controller = controller; 
    }
}
