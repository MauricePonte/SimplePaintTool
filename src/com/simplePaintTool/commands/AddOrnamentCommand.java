package com.simplePaintTool.commands;

import com.simplePaintTool.mvc.PaintModel;
import com.simplePaintTool.shapes.DrawingObject;

public class AddOrnamentCommand  implements Command {
        private final DrawingObject ornament;
        private final PaintModel model;
        private final DrawingObject original;

        //ook wel switch shape command, vervangt een shape voor een shapedecorator.
        public AddOrnamentCommand(DrawingObject ornament, PaintModel model,DrawingObject original) {
            this.ornament = ornament;// dit is geen ornament maar een Decorator.
            this.model = model;
            this.original = original;// dit is het orginele object.
            ornament.setPrimaryObject(true);
        }

        @Override
        public void execute() {
            //this.model.removeShape2(this.ornament.getShape(),this.ornament.getParent());
            //this.model.addShape2(this.ornament,this.ornament.getParent());
            this.model.addDecorator(ornament,original,this.ornament.getParent());
            this.original.setPrimaryObject(false);
        }

        @Override
        public void unexecute() {
            this.model.removeDecorator(ornament,original,this.ornament.getParent());
            this.original.setPrimaryObject(true);
        }


}


