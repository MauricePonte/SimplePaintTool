package com.simplePaintTool.fileIO;

import com.simplePaintTool.drawingObjectVisitor.ObjectVisitor;
import com.simplePaintTool.drawingObjectVisitor.SaveObjectVisitor;
import com.simplePaintTool.mvc.PaintModel;

import java.io.*;

public class SaveFile {
    private final String fileName;
    private final PaintModel model;

    public SaveFile(String fileName,PaintModel model){
        this.fileName = fileName + ".txt";
        this.model = model;
    }

    public void save() throws IOException {
        BufferedWriter fileWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName)));
        ObjectVisitor saveFile = new SaveObjectVisitor(fileWriter);

        model.GetMainGroup().accept(saveFile);
        fileWriter.close();
        System.out.println("File is saved");
    }
}
