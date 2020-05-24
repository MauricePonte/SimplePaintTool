package com.simplePaintTool.shapes;

import com.simplePaintTool.DrawingObjectVisitor.ObjectVisitor;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public interface DrawingObject {

    void draw(Graphics graphics);
    void select();
    void deselect();
    String toString();
    Group getParent();
    DefaultListModel<DrawingObject> getListInput();
    List<DrawingObject> getShape();
    void accept(ObjectVisitor visitor) throws IOException;
}
