package com.simplePaintTool.fileIO;

import com.simplePaintTool.commands.AddGroupCommand;
import com.simplePaintTool.commands.AddOrnamentCommand;
import com.simplePaintTool.commands.AddShapeCommand;
import com.simplePaintTool.commands.Command;
import com.simplePaintTool.decorator.*;
import com.simplePaintTool.mvc.PaintModel;
import com.simplePaintTool.shapes.DrawingObject;
import com.simplePaintTool.shapes.Group;
import com.simplePaintTool.shapes.Ornament;
import com.simplePaintTool.shapes.Shape;
import com.simplePaintTool.strategy.drawStrategy;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Stack;
import java.util.List;

public class LoadFile {
    private final String fileName;
    private final PaintModel model;
    final drawStrategy rectangleStrat;
    final drawStrategy ellipseStrat;
    private final Stack<Command> loadCommands;

    public LoadFile(String fileName,PaintModel model,drawStrategy rect,drawStrategy ellips) {
        this.fileName = fileName + ".txt";
        this.model = model;
        this.rectangleStrat = rect;
        this.ellipseStrat = ellips;
        this.loadCommands = new Stack<>();
    }

    private void addShape(String[] readerLine, int indentation, Group parent, List<String[]> stringArrays){

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
        if(stringArrays.size() >0 ) loadCommands.addAll(addOrnaments(stringArrays,s));
    }

    private void addGroup(int indentation, Group parent, int lineCount) throws IOException {
        BufferedReader fileReader = new BufferedReader(new FileReader(fileName));

        String readerLine;
        String[] readerTokens;
        int counter = 0;
        int groupID = (indentation+2) / 2;
        boolean keepReading = true;
        List<String[]> stringArrays = new ArrayList<>();

        while((readerLine = fileReader.readLine()) != null && keepReading){
            readerTokens = readerLine.split(" ");
            if(counter > lineCount) {
                if (readerTokens.length > indentation) {
                    if (readerTokens[indentation].equals("group")) {
                        keepReading = false;
                    } else {
                        int indentationcount = 0;
                        for (String readerToken : readerTokens) {
                            if (readerToken.isBlank()) {
                                indentationcount++;
                            }
                        }
                        if (indentationcount == (indentation + 2)) {
                            if(readerTokens[indentationcount].equals("ornament")){
                                String[] ornament = new String[2];
                                ornament[0] = readerTokens[indentationcount+1];
                                ornament[1] = readerTokens[indentationcount+2];
                                stringArrays.add(ornament);
                            }

                            if (readerTokens[indentationcount].equals("group")) {
                                Group newGroup = new Group(groupID, parent);
                                Command c = new AddGroupCommand(newGroup, model);
                                loadCommands.add(c);
                                if(stringArrays.size() >0 ) loadCommands.addAll(addOrnaments(stringArrays,newGroup));
                                stringArrays.clear();
                                addGroup(indentationcount, newGroup, counter);
                            }
                            if (readerTokens[indentationcount].equals("ellipse") ||
                                    readerTokens[indentationcount].equals("rectangle")) {
                                addShape(readerTokens, indentationcount, parent,stringArrays);
                                stringArrays.clear();
                            }
                        }
                    }
                }
            }
            counter++;
        }
        fileReader.close();
    }

    public Stack<Command> initialGroup() throws IOException {
        BufferedReader fileReader = new BufferedReader(new FileReader(fileName));
        String[] readerTokens;
        boolean keepReading = true;
        Group firstGroup = new Group(0,null);
        List<String[]> stringArrays = new ArrayList<>();

        while(keepReading){
            readerTokens = fileReader.readLine().split(" ");

            if(readerTokens[0].equals("ornament")){
                String[] ornament = new String[2];
                ornament[0] = readerTokens[1];
                ornament[1] = readerTokens[2];
                stringArrays.add(ornament);
            }
            if (readerTokens[0].equals("group")) {
                Command c = new AddGroupCommand(firstGroup,model);
                loadCommands.add(c);
                keepReading = false;
                if(stringArrays.size() >0 ) loadCommands.addAll(addOrnaments(stringArrays,firstGroup));
                stringArrays.clear();
                addGroup(0,firstGroup,loadCommands.size()-1);
            }

        }
        fileReader.close();
        return loadCommands;
    }

    private Stack<Command> addOrnaments(List<String[]> stringArrayList, DrawingObject parent){
        Stack<Command> ornamentCommands = new Stack<>();
        DrawingObject myParent = parent;
        ShapeDecorator newDecorator = null;

        while(stringArrayList.size() > 0){
            int lastArray = stringArrayList.size() - 1;
            Ornament ornament = new Ornament(stringArrayList.get(lastArray)[1],stringArrayList.get(lastArray)[0]);
            switch (ornament.getPos()) {
                case "top":
                    newDecorator = new TopOrnamentDecorator(myParent, ornament);
                    break;
                case "bottom":
                    newDecorator = new BottomOrnamentDecorator(myParent, ornament);
                    break;
                case "left":
                    newDecorator = new LeftOrnamentDecorator(myParent, ornament);
                    break;
                case "right":
                    newDecorator = new RightOrnamentDecorator(myParent, ornament);
                    break;
                default:
                    break;
            }
            assert newDecorator != null;
            Command c = new AddOrnamentCommand(newDecorator,model,myParent);
            ornamentCommands.add(c);
            myParent = newDecorator;
            stringArrayList.remove(lastArray);
        }

        return ornamentCommands;
    }

}



