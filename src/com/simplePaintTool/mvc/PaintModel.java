package com.simplePaintTool.mvc;

import com.simplePaintTool.shapes.DrawingObject;
import com.simplePaintTool.shapes.Group;
import com.simplePaintTool.shapes.Shape;

import javax.swing.*;
import java.util.ArrayList;

public class PaintModel {
    private Group mainGroup;
    //private ArrayList<DrawingObject> shapes;

    public PaintModel(){
        //shapes = new ArrayList<>();
        //shapes.add(new Group(0));
        mainGroup = new Group(0,null);
    }

    /**
     * Add a shape to the model
     * @param  shape to add
     */
    /*
    public void addShape(DrawingObject s,int groupID){
        if(shapes.isEmpty()){
            shapes.add(s);
        }else {
            for (DrawingObject d : shapes) {
                if (d instanceof Group) {
                    if (((Group) d).getGroupID() == groupID) {
                        ((Group) d).addChild(s);
                    }
                }
            }
        }
    }

     */

    //test add
    public void addShape2(DrawingObject shape,Group parent){
        try{
            parent.addChild(shape);
        }catch (Exception e){
            mainGroup = (Group) shape;
        }
    }

    public void removeShape2(DrawingObject shape, Group parent){
        parent.removeChild(shape);
    }
    /**
     * Remove a shape from the model
     * @param s shape to remove
     */
    /*
    public void removeShape(Shape s,int groupID){
        for (DrawingObject d: shapes) {
            if (((Group) d).getGroupID() == groupID){
                ((Group) d).removeChild(s);
            }
        }
    }
     */

    /**
     * Return all shapes in this model
     * @return All shapes
     */
    public Group GetMainGroup(){
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
