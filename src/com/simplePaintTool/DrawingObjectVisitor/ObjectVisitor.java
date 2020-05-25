package com.simplePaintTool.DrawingObjectVisitor;

import com.simplePaintTool.decorator.ShapeDecorator;
import com.simplePaintTool.shapes.DrawingObject;
import com.simplePaintTool.shapes.Group;
import com.simplePaintTool.shapes.Ornament;
import com.simplePaintTool.shapes.Shape;

import java.io.IOException;

public interface ObjectVisitor {
    void visit(Group g) throws IOException;
    void visit(Shape s) throws IOException;
    void visit(ShapeDecorator s) throws IOException;

}
