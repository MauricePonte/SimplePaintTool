package com.simplePaintTool;

import com.simplePaintTool.mvc.PaintController;
import com.simplePaintTool.mvc.PaintView;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PaintingFrame extends JFrame {
    private JPanel mainPanel;
    private PaintView view;
    private PaintController controller;

    private final ButtonGroup buttonsGroup;
    private JToggleButton tglBtnSelect;
    private JToggleButton tglBtnDrawRectangle;
    private JToggleButton tglBtnDrawElipse;

    private JButton btnUndo;
    private JButton btnRedo;

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

        // Comment here
        tglBtnDrawRectangle = new JToggleButton();
        //tglBtnDrawRectangle.setFont(new Font("Dotum", Font.BOLD, 12));
        tglBtnDrawRectangle.setText("Rectangle");
        buttonsGroup.add(tglBtnDrawRectangle);
        //tglBtnDrawRectangle.setCursor(toolkit.createCustomCursor(image1 , new java.awt.Point(tglBtnDrawSquare.getX(), tglBtnDrawSquare.getY()), "img"));
        buttonsPanel.add(tglBtnDrawRectangle);

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
            }
            /*
            @Override
            public void mouseClicked(MouseEvent click) {
                if (tglBtnDrawRectangle.isSelected()){

                    controller.btnAddRectangleClicked(getCoordinaten());
                }
                /*if (tglBtnDrawPoint.isSelected()) controller.btnAddPointClicked(click);
                else if (tglBtnDrawLine.isSelected()) controller.btnAddLineClicked(click);
                else if (tglBtnDrawSquare.isSelected()) controller.btnAddSquareClicked(click);
                else if (tglBtnDrawRectangle.isSelected()) controller.btnAddRectangleClicked(click);
                else if (tglBtnDrawCircle.isSelected()) controller.btnAddCircleClicked(click);
                else if (tglBtnDrawHexagon.isSelected()) controller.btnAddHexagonClicked(click);
                else if (tglBtnSelect.isSelected()) controller.btnSelectShapeClicked(click);
            }
            */
        });
    }

    private Point[] getCoordinaten(){
        final Point[] pointers = new Point[2];

        view.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                pointers[0] = new Point(e.getX(), e.getY());

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                pointers[1] = new Point(e.getX(), e.getY());

            }
        });

        return pointers;
    }


    public PaintView getView() {
        return view;
    }
    public void setController(PaintController controller){
        this.controller = controller; 
    }


}
