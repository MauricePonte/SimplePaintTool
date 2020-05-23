package com.simplePaintTool.shapes;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Group implements DrawingObject {

    List<DrawingObject> ChildrenObjects;

    int groupID;
    boolean selected;
    private Group parent;

    public Group(int id,Group parent){
        this.groupID = id;
        this.ChildrenObjects = new ArrayList<>();
        this.parent = parent;
    }

    @Override
    public void draw(Graphics graphics) {
        for(DrawingObject d:this.ChildrenObjects){
            d.draw(graphics);
        }
    }

    @Override
    public void select() {
        this.selected = true;
        for(DrawingObject d:ChildrenObjects){
            d.select();
        }
    }

    @Override
    public void deselect() {
        this.selected = false;
        for(DrawingObject d:ChildrenObjects){
            d.deselect();
        }
    }

    public void addChild(DrawingObject d){
        this.ChildrenObjects.add(d);
    }

    public void removeChild(DrawingObject d){
        this.ChildrenObjects.remove(d);
    }

    //geeft het groupID terug zodat de DrawingObjects in de juiste groepen terecht komen.
    public int getGroupID(){
        return this.groupID;
    }

    @Override
    public String toString(int identation){
        final String lineSep=System.getProperty("line.separator");
        String toWrite = "";
        for (int i = 0; i < identation; i++){
            toWrite += "  ";
        }
        toWrite += "group " + ChildrenObjects.size();
        identation++;
        for (DrawingObject d:ChildrenObjects){
            toWrite += lineSep+d.toString(identation);
        }
        return toWrite;
    }

    @Override
    public String toString(){
        String returnString = "";
        if(this.parent != null){
            for(int i = 0; i <= this.parent.groupID;i++){
                returnString += "  ";
            }
        }
        returnString += "group " + ChildrenObjects.size();
        return returnString;
    }

    @Override
    public Group getParent(){
        return this.parent;
    }

    @Override
    public DefaultListModel<DrawingObject> getListInput(){
        DefaultListModel<DrawingObject> placeholder = new DefaultListModel<>();
        placeholder.addElement(this);

        for (DrawingObject d: ChildrenObjects) {
            DefaultListModel<DrawingObject> newList = d.getListInput();
            for (int i = 0; i < newList.size(); i++) {
                placeholder.addElement(newList.get(i));
            }
        }
        return placeholder;
    }

    @Override
    public List<DrawingObject> getShape() {
        List<DrawingObject> list = new ArrayList<>();
        for(DrawingObject d: ChildrenObjects){
            list.addAll(d.getShape());
        }
        return list;
    }
}
