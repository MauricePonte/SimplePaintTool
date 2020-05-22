package com.simplePaintTool.shapes;

import java.awt.*;

public interface DrawingObject {

    void draw(Graphics graphics);
    void select();
    void deselect();
    String toString(int identation);
}
