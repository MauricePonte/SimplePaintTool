package com.simplePaintTool;

import com.simplePaintTool.mvc.PaintController;
import com.simplePaintTool.mvc.PaintModel;

public class main {

    public static void main(String [] args)
    {
        PaintingFrame frame = new PaintingFrame();
        frame.setVisible(true);
        frame.setTitle("PaintTool by Maurice Ponte & Tjeerd van Gelder");
        PaintModel model = new PaintModel();
        frame.getView().setModel(model);
        frame.setController(new PaintController(model,frame));
    }
}
