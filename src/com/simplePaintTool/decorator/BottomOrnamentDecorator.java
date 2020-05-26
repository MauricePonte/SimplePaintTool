package com.simplePaintTool.decorator;

import com.simplePaintTool.shapes.DrawingObject;
import com.simplePaintTool.shapes.Ornament;

import java.awt.*;

public class BottomOrnamentDecorator extends ShapeDecorator{

    public BottomOrnamentDecorator(DrawingObject shape, Ornament ornament){
        super(shape,ornament);
    }

    @Override
    public void draw(Graphics graphics) {
        shape.draw(graphics);
        addOrnament(graphics);
    }

    @Override
    public int getX(){
        return shape.getX();
    }
    @Override
    public int getY(){
        return shape.getY();
    }
    @Override
    public int getWidth(){
        return shape.getWidth();
    }
    @Override
    public int getHeight(){
        return shape.getHeight();
    }

    private void addOrnament(Graphics graphics){
        int x = shape.getX() + (shape.getWidth() / 2) - ornamentToAdd.stringBuffer();
        int y = shape.getY() + shape.getHeight() + 15;

        ornamentToAdd.setX(x);
        ornamentToAdd.setY(y);
        //shape.setOrnament(ornament);
        ornamentToAdd.draw(graphics);
    }



}
