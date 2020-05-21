package com.simplePaintTool.strategy;

import com.simplePaintTool.shapes.Shape;

import java.awt.*;

public interface drawStrat {
    void draw(Graphics g, Shape s);
    String toString(Shape s);
}
