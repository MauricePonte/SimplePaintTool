package com.simplePaintTool.shapes;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public interface DrawingObject {

    void draw(Graphics graphics);
    void select();
    void deselect();
    String toString(int identation);
    Group getParent();
    DefaultListModel<DrawingObject> getListInput();
    List<DrawingObject> getShape();
}
