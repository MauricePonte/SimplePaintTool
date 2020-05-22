package com.simplePaintTool;

import com.simplePaintTool.mvc.PaintController;
import com.simplePaintTool.mvc.PaintObserver;
import com.simplePaintTool.mvc.PaintView;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PaintingFrame extends JFrame {
    private JPanel mainPanel;
    private PaintView view;
    private PaintController controller;

    //Standaard buttons aanmaken
    private final ButtonGroup buttonsGroup;
    private JToggleButton tglBtnSelect;
    private JToggleButton tglBtnDrawRectangle;
    private JToggleButton tglBtnDrawElipse;
    private JButton btnUndo;
    private JButton btnRedo;
    // Mouse adapters voor buttons
    private MouseAdapter mouseAdapterUndo;
    private MouseAdapter mouseAdapterRedo;

    public PaintingFrame(){
        // Comment here
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        mainPanel = new JPanel();
        mainPanel.setBackground(Color.GRAY);
        mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        mainPanel.setLayout(new BorderLayout(0, 0));
        setContentPane(mainPanel);

        // Comment here
        JPanel buttonsPanelForDrawing = new JPanel();
        buttonsPanelForDrawing.setBackground(Color.GRAY);
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBackground(Color.GRAY);

        // Comment here
        view = new PaintView();
        view.setBackground(Color.WHITE);
        mainPanel.add(view, BorderLayout.CENTER);
        mainPanel.add(buttonsPanel, BorderLayout.NORTH);
        buttonsGroup = new ButtonGroup();

        // Hier instancieren we Toggle buttons
        // Rectanlge button
        tglBtnDrawRectangle = new JToggleButton("Rectangle");
        buttonsGroup.add(tglBtnDrawRectangle);
        buttonsPanel.add(tglBtnDrawRectangle);
        // Ellipse button
        tglBtnDrawElipse = new JToggleButton("Elipse");
        buttonsGroup.add(tglBtnDrawElipse);
        buttonsPanel.add(tglBtnDrawElipse);

        // Hier instancieren we overige buttons
        // Undo button
        btnUndo = new JButton("Undo");
        btnUndo.setEnabled(false);
        buttonsPanel.add(btnUndo);
        // Redo button
        btnRedo = new JButton("Redo");
        btnRedo.setEnabled(false);
        buttonsPanel.add(btnRedo);

        //Mouse adapters om controller functionaliteit aan te roepen in deze frame
        mouseAdapterRedo = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                controller.redoCommand();
            }
        };
        mouseAdapterUndo = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                controller.undoCommand();
            }
        };

        // Mouse listener gedeelte. Hier handelen we muis inputs af, opbasis van welke knop geselecteerd is
        view.addMouseListener(new MouseAdapter() {
            final Point[] pointers = new Point[2];
            @Override
            public void mousePressed(MouseEvent e) {
                pointers[0] = new Point(e.getX(), e.getY());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                pointers[1] = new Point(e.getX(), e.getY());
                if (tglBtnDrawRectangle.isSelected()) controller.btnAddRectangleClicked(pointers);
                else if(tglBtnDrawElipse.isSelected()) controller.btnAddEllipseClicked(pointers);
            }
        });
    }

    public PaintView getView() {
        return view;
    }
    public void setController(PaintController controller){
        this.controller = controller;
        controller.addPropertyChangedListener(new PaintObserver(this));
    }

    // Getters voor Observer
    public JButton getBtnUndo() {
        return btnUndo;
    }
    public JButton getBtnRedo() {
        return btnRedo;
    }

    public MouseAdapter getMouseAdapterUndo() {
        return mouseAdapterUndo;
    }
    public MouseAdapter getMouseAdapterRedo() {
        return mouseAdapterRedo;
    }
}
