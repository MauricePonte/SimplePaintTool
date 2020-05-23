package com.simplePaintTool.fileIO;

import com.simplePaintTool.commands.AddGroupCommand;
import com.simplePaintTool.commands.AddShapeCommand;
import com.simplePaintTool.commands.Command;
import com.simplePaintTool.mvc.PaintModel;
import com.simplePaintTool.shapes.Group;
import com.simplePaintTool.shapes.Shape;
import com.simplePaintTool.strategy.EllipseStrat;
import com.simplePaintTool.strategy.RectangleStrat;
import com.simplePaintTool.strategy.drawStrat;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;
import java.util.List;

public class LoadFile {
    private String fileName;
    private PaintModel model;
    drawStrat rectangleStrat;
    drawStrat ellipseStrat;
    List<Group> groups = new ArrayList<>();

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
        int indentationcount;
        int groupCounter = -1;

        Stack<Command> loadCommands = new Stack<>();
        try{
            for (readerLine = fileReader.readLine(); readerLine != null; readerLine = fileReader.readLine(),count++){
                readerTokens = readerLine.split(" ");
                indentationcount = 0;
                for(int i = 0 ; i < readerTokens.length;i++){
                    if(readerTokens[i].isBlank()){
                        indentationcount++;
                    }
                }
                if (readerTokens[indentationcount].equals("rectangle") || readerTokens[indentationcount].equals("ellipse")){
                    String soortShape = readerTokens[indentationcount];
                    int x = Integer.parseInt(readerTokens[indentationcount+1]);
                    int y = Integer.parseInt(readerTokens[indentationcount+2]);
                    int width = Integer.parseInt(readerTokens[indentationcount+3]);
                    int height = Integer.parseInt(readerTokens[indentationcount+4]);
                    loadCommands.add(addShape(soortShape,x,y,width,height,groupCounter));
                }

                if (readerTokens[indentationcount].equals("group")){
                    groupCounter++;
                    loadCommands.add(addGroup(groupCounter));
                }
            }
        }
        catch (Exception e){
        }
        return loadCommands;
    }

    private Command addShape(String soortShape, int x, int y, int width, int height,int groupID){
        Point start = new Point(x,y);
        Point end = new Point(x+width,y+height);
        drawStrat strategy;
        if(soortShape.equals("ellipse")) strategy = ellipseStrat; else strategy = rectangleStrat;
        Shape s = new Shape(start,end,strategy,groups.get(groupID));
        Command c = new AddShapeCommand(s,model,groupID);
        return c;
    }

    private Command addGroup(int id){
        Group group;
        if(groups.isEmpty()){
            group = new Group(id,null);
        } else{
            group = new Group(id,groups.get(id-1));
        }
        groups.add(group);
        Command c = new AddGroupCommand(group,model,id--);
        return c;
    }

    private Command addOrnament(){
        return null;
    }
}
