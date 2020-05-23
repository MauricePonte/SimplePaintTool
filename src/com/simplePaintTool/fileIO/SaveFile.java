package com.simplePaintTool.fileIO;

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

    public void save() throws FileNotFoundException {
        final String lineSep=System.getProperty("line.separator");
        BufferedWriter fileWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName)));
        try{
            /*
            for (DrawingObject s: model.getAllShapes()) {
                fileWriter.write(s.toString(0)+lineSep);
            }
             */
            fileWriter.write(model.GetMainGroup().toString(0));
            fileWriter.close();
            System.out.println("File is saved");
        }
        catch (Exception e){
        }
    }
}
