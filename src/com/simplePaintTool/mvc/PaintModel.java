package com.simplePaintTool.mvc;

import com.simplePaintTool.shapes.DrawingObject;
import com.simplePaintTool.shapes.Group;

import javax.swing.*;

public class PaintModel {
    private DrawingObject mainGroup;

    public PaintModel(){
        mainGroup = new Group(0,null);
        mainGroup.setPrimaryObject(true);
    }

    public void addShape(DrawingObject shape, Group parent){
        try{
            parent.addChild(shape);
        }catch (Exception e){
            mainGroup = shape;
        }
    }

    public void removeShape(DrawingObject shape, Group parent){
        parent.removeChild(shape);
    }

    public void addDecorator(DrawingObject decorator,DrawingObject original,Group parent){
        try{
            int i = parent.getIndexOfChild(original);
            parent.insertChildAtIndex(decorator,i);
            parent.removeChild(original);
        }catch (Exception e){
            mainGroup = decorator;
        }
    }

    public void removeDecorator(DrawingObject decorator,DrawingObject original,Group parent){
        try{
            int i = parent.getIndexOfChild(decorator);
            parent.insertChildAtIndex(original,i);
            parent.removeChild(decorator);
        }catch (Exception e){
            mainGroup = original;
        }

    }

    public DrawingObject GetMainGroup(){
        return this.mainGroup;
    }

    public DefaultListModel<DrawingObject> getList(){
        return mainGroup.getListInput();
    }

    public void clearCanvas(){
        mainGroup = null;
    }

    public void deselectAllObjects(){
        mainGroup.deselect();
    }


}
