package com.simplePaintTool.commands;

import com.simplePaintTool.mvc.PaintModel;
import com.simplePaintTool.shapes.DrawingObject;

public class AddOrnamentCommand  implements Command {
        private DrawingObject ornament;
        private PaintModel model;

        public AddOrnamentCommand(DrawingObject ornament, PaintModel model) {
            this.ornament = ornament;
            this.model = model;
        }

        @Override
        public void execute() {
            this.model.removeShape2(this.ornament.getShape(),this.ornament.getParent());
            this.model.addShape2(this.ornament,this.ornament.getParent());
        }

        @Override
        public void unexecute() {
            this.model.addShape2(this.ornament.getShape(),this.ornament.getParent());
            this.model.removeShape2(this.ornament,this.ornament.getParent());
        }


}


