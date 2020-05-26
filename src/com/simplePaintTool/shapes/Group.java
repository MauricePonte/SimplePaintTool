package com.simplePaintTool.shapes;

import com.simplePaintTool.drawingObjectVisitor.ObjectVisitor;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Group extends DrawingObject {

    final List<DrawingObject> ChildrenObjects;

    final int groupID;
    private final Group parent;

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
        for(DrawingObject d:ChildrenObjects){
            d.select();
        }
    }

    @Override
    public void deselect() {
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

    public int getIndexOfChild(DrawingObject d){
        return ChildrenObjects.indexOf(d);
    }
    public void insertChildAtIndex(DrawingObject d,int i){
        ChildrenObjects.add(i,d);
    }

    @Override
    public String toString(){
        StringBuilder returnString = new StringBuilder();
        if(this.parent != null){
            returnString.append("  ".repeat(Math.max(0, this.parent.groupID + 1)));
        }
        returnString.append("group ").append(ChildrenObjects.size());
        return returnString.toString();
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
    public List<DrawingObject> getCommandListInput() {
        List<DrawingObject> list = new ArrayList<>();
        for(DrawingObject d: ChildrenObjects){
            list.addAll(d.getCommandListInput());
        }
        return list;
    }


    @Override
    public DrawingObject getShape() {
        return this;
    }

    //accept voor ObjectVisitor
    @Override
    public void accept(ObjectVisitor visitor) throws IOException {
        visitor.visit(this);
        for (DrawingObject d:ChildrenObjects){
            d.accept(visitor);
        }

    }

    @Override
    public int getX(){
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int width = gd.getDisplayMode().getWidth();
        int result = width / 2;

        if(!ChildrenObjects.isEmpty()){
            for(DrawingObject d:ChildrenObjects){
                if(d.getX() < result)result = d.getX();
            }
        }
        return result - 10;
    }

    @Override
    public int getY(){
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int height = gd.getDisplayMode().getHeight();
        int result = height / 2;

        if(!ChildrenObjects.isEmpty()){
            for(DrawingObject d:ChildrenObjects){
                if(d.getY() < result)result = d.getY();
            }
        }
        return result - 10;
    }

    @Override
    public int getWidth(){
        int bigX = 0;
        for(DrawingObject d:ChildrenObjects){
            if(d.getX()+d.getWidth() > bigX) bigX = d.getX()+d.getWidth();
        }
        return bigX-this.getX();
    }

    @Override
    public int getHeight(){
        int bigY = 0;
        for(DrawingObject d: ChildrenObjects){
            if(d.getY()+d.getHeight() > bigY) bigY = d.getY()+d.getHeight();
        }
        return bigY-this.getY();
    }
}
