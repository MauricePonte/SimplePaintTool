package com.simplePaintTool.DrawingObjectVisitor;

import com.simplePaintTool.shapes.Group;
import com.simplePaintTool.shapes.Shape;

import java.io.IOException;

public interface ObjectVisitor {
    void visit(Group g) throws IOException;
    void visit(Shape s) throws IOException;


}
