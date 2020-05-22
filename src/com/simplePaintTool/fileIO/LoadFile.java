package com.simplePaintTool.fileIO;

import com.simplePaintTool.commands.AddShapeCommand;
import com.simplePaintTool.commands.Command;
import com.simplePaintTool.mvc.PaintModel;
import com.simplePaintTool.shapes.Shape;
import com.simplePaintTool.strategy.EllipseStrat;
import com.simplePaintTool.strategy.RectangleStrat;
import com.simplePaintTool.strategy.drawStrat;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;

public class LoadFile {
    private String fileName;
    private PaintModel model;
    drawStrat rectangleStrat;
    drawStrat ellipseStrat;

    public LoadFile(String fileName,PaintModel model,drawStrat rect,drawStrat ellips){
        this.fileName = fileName + ".txt";
        this.model = model;
        this.rectangleStrat = rect;
        this.ellipseStrat = ellips;
    }

    public Stack<Command> load() throws FileNotFoundException {
        final String lineSep=System.getProperty("line.separator");
        BufferedReader fileReader = new BufferedReader(new FileReader(fileName)); //Opent de reader waar de matchIDs uit worden gelezen.
        String readerLine = null;
        String[] readerTokens;
        int count = 0;
        Stack<Command> loadCommands = new Stack<>();
        try{
            for (readerLine = fileReader.readLine(); readerLine != null; readerLine = fileReader.readLine(),count++){
                readerTokens = readerLine.split(" ");
                if (readerTokens[0].equals("rectangle") || readerTokens[0].equals("ellipse")){
                    String soortShape = readerTokens[0];
                    int x = Integer.parseInt(readerTokens[1]);
                    int y = Integer.parseInt(readerTokens[2]);
                    int width = Integer.parseInt(readerTokens[3]);
                    int height = Integer.parseInt(readerTokens[4]);
                    loadCommands.add(addShape(soortShape,x,y,width,height));
                }
            }
        }
        catch (Exception e){
        }

        return loadCommands;
    }

    private Command addShape(String soortShape, int x, int y, int width, int height){
        Point start = new Point(x,y);
        Point end = new Point(x+width,y+height);
        drawStrat strategy;
        if(soortShape.equals("ellipse")) strategy = ellipseStrat; else strategy = rectangleStrat;
        Shape s = new Shape(start,end,strategy);
        Command c = new AddShapeCommand(s,model);
        return c;
    }
}
