package com.simplePaintTool.fileIO;

import com.simplePaintTool.commands.AddGroupCommand;
import com.simplePaintTool.commands.AddShapeCommand;
import com.simplePaintTool.commands.Command;
import com.simplePaintTool.mvc.PaintModel;
import com.simplePaintTool.shapes.Group;
import com.simplePaintTool.shapes.Shape;
import com.simplePaintTool.strategy.drawStrategy;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Stack;
import java.util.List;

public class LoadFile {
    private String fileName;
    private PaintModel model;
    drawStrategy rectangleStrat;
    drawStrategy ellipseStrat;
    private Stack<Command> loadCommands;

    public LoadFile(String fileName,PaintModel model,drawStrategy rect,drawStrategy ellips) throws FileNotFoundException {
        this.fileName = fileName + ".txt";
        this.model = model;
        this.rectangleStrat = rect;
        this.ellipseStrat = ellips;
        this.loadCommands = new Stack<>();
    }

    public Stack<Command> load() throws IOException {
        Group firstGroup = new Group(0,null);
        Command firstGroupCommand = new AddGroupCommand(firstGroup,model);
        loadCommands.add(firstGroupCommand);
        addGroup2(0,firstGroup,0);

        return loadCommands;
    }

    private void addShape2(String[] readerLine,int indentation,Group parent){

        String soortShape = readerLine[indentation];
        int x = Integer.parseInt(readerLine[indentation+1]);
        int y = Integer.parseInt(readerLine[indentation+2]);
        int width = Integer.parseInt(readerLine[indentation+3]);
        int height = Integer.parseInt(readerLine[indentation+4]);
        Point start = new Point(x,y);
        Point end = new Point(x+width,y+height);
        drawStrategy strategy;
        if(soortShape.equals("ellipse")) strategy = ellipseStrat; else strategy = rectangleStrat;
        Shape s = new Shape(start,end,strategy,parent);
        Command c = new AddShapeCommand(s,model);
        loadCommands.add(c);
    }

    private void addGroup2(int indentation,Group parent,int lineCount) throws IOException {
        BufferedReader fileReader = new BufferedReader(new FileReader(fileName));

        String readerLine = null;
        String[] readerTokens;
        int counter = 0;
        int start = lineCount;
        int groupID = (indentation+2) / 2;
        boolean keepReading = true;

        while((readerLine = fileReader.readLine()) != null && keepReading){
            readerTokens = readerLine.split(" ");
            if(counter > start) {
                if (readerTokens.length > indentation) {
                    if (readerTokens[indentation].equals("group")) {
                        keepReading = false;
                    } else {
                        int indentationcount = 0;
                        for (int i = 0; i < readerTokens.length; i++) {
                            if (readerTokens[i].isBlank()) {
                                indentationcount++;
                            }
                        }
                        if (indentationcount == (indentation + 2)) {
                            if (readerTokens[indentationcount].equals("group")) {
                                Group newGroup = new Group(groupID, parent);
                                Command c = new AddGroupCommand(newGroup, model);
                                loadCommands.add(c);
                                addGroup2(indentationcount, newGroup, counter);
                            }
                            if (readerTokens[indentationcount].equals("ellipse") ||
                                    readerTokens[indentationcount].equals("rectangle")) {
                                addShape2(readerTokens, indentationcount, parent);
                            }
                        }
                    }
                }
            }
            counter++;
        }
        fileReader.close();
    }
    // TODO wtf is going on ova here
    /*
    private Command addOrnament(){
        return null;
    }

    private Command addGroup(int id){
        Group group;
        if(groups.isEmpty()){
            group = new Group(id,null);
        } else{
            group = new Group(id,groups.get(id-1));
        }
        groups.add(group);
        Command c = new AddGroupCommand(group,model);
        return c;
    }

    private Command addOrnament(){
        return null;
    }

     */
}
