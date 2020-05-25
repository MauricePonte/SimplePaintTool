package com.simplePaintTool.mvc;

import com.simplePaintTool.mvc.PaintController;
import com.simplePaintTool.mvc.PaintObserver;
import com.simplePaintTool.mvc.PaintView;
import com.simplePaintTool.shapes.DrawingObject;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;

public class PaintingFrame extends JFrame {
    private JPanel mainPanel;
    private PaintView view;
    private PaintController controller;

    private final ButtonGroup buttonsGroup;
    private JToggleButton tglBtnResize;
    private JToggleButton tglBtnMove;
    private JToggleButton tglBtnDrawRectangle;
    private JToggleButton tglBtnDrawElipse;

    private JButton btnGroup;
    private JButton btnUndo;
    private JButton btnRedo;
    private JButton btnSave;
    private JButton btnLoad;

    private JButton btnAddOrnament;

    private JLabel label = new JLabel("Selecteer een object");
    private DefaultListModel<DrawingObject> dataModel = new DefaultListModel();
    private JList<DrawingObject> drawingObjectJList = new JList<>(dataModel);
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
        JPanel listPanel = new JPanel();
        listPanel.setBackground(Color.BLUE);

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

        //resize button
        tglBtnResize = new JToggleButton("Resize selection");
        buttonsGroup.add(tglBtnResize);
        buttonsPanel.add(tglBtnResize);
        //move button
        tglBtnMove = new JToggleButton("Move selection");
        buttonsGroup.add(tglBtnMove);
        buttonsPanel.add(tglBtnMove);

        // Hier instancieren we overige buttons
        //addGroup button
        btnGroup = new JButton("Add Group");
        btnGroup.setEnabled(true);
        buttonsPanel.add(btnGroup);
        // Undo button
        btnUndo = new JButton("Undo");
        btnUndo.setEnabled(false);
        buttonsPanel.add(btnUndo);
        // Redo button
        btnRedo = new JButton("Redo");
        btnRedo.setEnabled(false);
        buttonsPanel.add(btnRedo);
        //save button
        btnSave = new JButton("Save");
        btnSave.setEnabled(true);
        buttonsPanel.add(btnSave);
        //load button
        btnLoad = new JButton("Load");
        btnLoad.setEnabled(true);
        buttonsPanel.add(btnLoad);
        // Add ornament
        btnAddOrnament = new JButton("Add ornament");
        btnAddOrnament.setEnabled(true);
        buttonsPanel.add(btnAddOrnament);

        //poging tot JList met alle shapes enzo.
        //ListListener called select op het geselecteerde element
        drawingObjectJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listPanel.add(drawingObjectJList);
        drawingObjectJList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(e.getValueIsAdjusting()){
                    controller.deselectAll();
                    DrawingObject d = dataModel.get(drawingObjectJList.getSelectedIndex());
                    d.select();
                    controller.setSelectedObject(d);
                    view.repaint();
                }
            }
        });

        mainPanel.add(listPanel,BorderLayout.WEST);

        //Action listener van de load button
        btnLoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controller.loadSaveFile();
                } catch (FileNotFoundException ex) {
                }
            }
        });
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controller.SaveFileClicked();
                } catch (FileNotFoundException ex) {
                }
            }
        });
        btnGroup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.btnAddGroupClicked();
            }
        });
        btnAddOrnament.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.btnAddOrnamentClicked();
            }
        });

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
                if(validateDrawing(pointers)){
                    if (tglBtnDrawRectangle.isSelected()) controller.btnAddRectangleClicked(pointers);
                    if (tglBtnDrawElipse.isSelected()) controller.btnAddEllipseClicked(pointers);
                    if (tglBtnResize.isSelected()) controller.btnResizeClicked(pointers);
                    if (tglBtnMove.isSelected()) controller.btnMoveClicked(pointers);
                }
            }
        });

    }

    public PaintView getView() {
        return view;
    }

    public void setList(DefaultListModel<DrawingObject> list){
        dataModel.clear();
        for (int i = 0 ; i < list.size() ; i++){
            dataModel.addElement(list.get(i));
        }
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

    private boolean validateDrawing(Point[] points){
        int x1 = points[0].x;
        int x2 = points[1].x;
        int y1 = points[0].y;
        int y2 = points[1].y;

        if(Math.abs(x1-x2) > 10 && Math.abs(y1-y2) > 10) return true; else return false;

    }

}
