package view;

import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class BoxPane extends FlowPane {

    private List<StackPane> boxes;
    private List<Label> labels;
    private List<Rectangle> squares;
    
    public BoxPane() {
        boxes = new ArrayList<>();
        labels = new ArrayList<>();
        squares = new ArrayList<>();
    }

    public void initialiseBoxes(int length) {
        this.getChildren().clear();
        boxes.clear();
        labels.clear();
        squares.clear();
        for (int i = 0; i < length; i++) {
            StackPane square = newSquare();
            boxes.add(square);
            this.getChildren().add(square);
        }
    }

    public StackPane newSquare() {
        StackPane stack = new StackPane();
        Label label = new Label();
        label.setStyle("-fx-text-fill: #ffffff;");
        Rectangle square = new Rectangle(75, 75);
        labels.add(label);
        squares.add(square);
        stack.getChildren().addAll(square, label);
        return stack;
    }
    
    public void setSquareValue(int index, int value) {
        labels.get(index).setText(String.valueOf(value));
    }

    public void setSquareColor(int index) {
        squares.get(index).setStyle("-fx-fill:#ff0000");
    }

}
