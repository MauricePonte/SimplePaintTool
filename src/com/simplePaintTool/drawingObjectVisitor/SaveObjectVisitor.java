package com.simplePaintTool.drawingObjectVisitor;

import com.simplePaintTool.decorator.*;
import com.simplePaintTool.shapes.Group;
import com.simplePaintTool.shapes.Shape;

import java.io.*;

public class SaveObjectVisitor implements ObjectVisitor {
    final String lineSep=System.getProperty("line.separator");
    private final BufferedWriter fileWriter;

    public SaveObjectVisitor(BufferedWriter fileWriter) {
        this.fileWriter = fileWriter;
    }

    @Override
    public void visit(Group g) throws IOException {
        fileWriter.write(g.toString() + lineSep);
    }

    @Override
    public void visit(Shape s) throws IOException {
        fileWriter.write(s.toString() + lineSep);
    }

    @Override
    public void visit(ShapeDecorator s) throws IOException {
        fileWriter.write(s.toString() + lineSep);
    }

}
