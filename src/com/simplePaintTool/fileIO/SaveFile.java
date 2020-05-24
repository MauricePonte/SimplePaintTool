package com.simplePaintTool.fileIO;

import com.simplePaintTool.DrawingObjectVisitor.ObjectVisitor;
import com.simplePaintTool.DrawingObjectVisitor.SaveObjectVisitor;
import com.simplePaintTool.mvc.PaintModel;
import com.simplePaintTool.shapes.DrawingObject;
import com.simplePaintTool.shapes.Shape;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SaveFile {
    private String fileName;
    private PaintModel model;

    public SaveFile(String fileName,PaintModel model){
        this.fileName = fileName + ".txt";
        this.model = model;
    }

    public void saveFile(){
        //ObjectVisitor save = new SaveObjectVisitor(BufferedWriter fileWriter);
        //model.GetMainGroup().accept(save);
    }
    public void save() throws IOException {
        BufferedWriter fileWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName)));
        ObjectVisitor saveFile = new SaveObjectVisitor(fileWriter);
        model.GetMainGroup().accept(saveFile);
        fileWriter.close();
        System.out.println("File is saved");
    }
}
