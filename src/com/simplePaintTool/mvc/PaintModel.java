package com.simplePaintTool.mvc;

import com.simplePaintTool.shapes.DrawingObject;
import com.simplePaintTool.shapes.Group;
import com.simplePaintTool.shapes.Shape;

import javax.swing.*;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class PaintModel {
    private DrawingObject mainGroup;

    public PaintModel(){
        mainGroup = new Group(0,null);
        mainGroup.setPrimaryObject(true);
    }


    //test add
    public void addShape2(DrawingObject shape,Group parent){
        try{
            parent.addChild(shape);
        }catch (Exception e){
            mainGroup = shape;
        }
    }

    public void removeShape2(DrawingObject shape, Group parent){
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

    /**
     * Return all shapes in this model
     * @return All shapes
     */
    public DrawingObject GetMainGroup(){
        return this.mainGroup;
    }

    public DefaultListModel<DrawingObject> getList(){
        DefaultListModel<DrawingObject> list = mainGroup.getListInput();
        return list;
    }

    /*
    public ArrayList<DrawingObject> getAllShapes(){
        return shapes;
    }

     */

    public void clearCanvas(){
        //this.shapes.clear();
        mainGroup = null;
    }

    public void deselectAllObjects(){
        /*for (DrawingObject d: shapes){
            d.deselect();
        }
         */
        mainGroup.deselect();
    }


}
