package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.QuickSort;
import model.SortEvent;
import java.util.Arrays;

/**
 * TODO: Learn FXML and create the main interface in that
 */
public class QuickSortFX extends Application {
    
    /**
     * My Components
     */
    private BoxPane boxPane;
    private QuickSort sorter;
    
    public QuickSortFX() {
        sorter = new QuickSort();
        sorter.addCallback(new Callback(this));
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        /* Setup main scene */
        BorderPane layout = new BorderPane();
        Scene scene = new Scene(layout, 500, 250);
        
        /* Layouts and Panes */
        boxPane = new BoxPane();
        
        /* Components */
        Button button = new Button("Start Sort");
        button.setOnAction(e -> quickSort());
        
        layout.setCenter(boxPane);
        layout.setBottom(button);
    
        /* Setup Stage */
        primaryStage.setTitle("QuickSortFX");
        primaryStage.setScene(scene);
        primaryStage.show();
    
    }
    
    public void quickSort() {
        new Thread(() -> {
            int[] array = {3, 5, 1, 4, 6, 2};
            System.out.println(Arrays.toString(array));
            sorter.start(array, 0, array.length - 1);
            System.out.println(Arrays.toString(array));
        }).start();
    }
    
    public void displayEvent(SortEvent event) {
        switch (event.getType()) {
            case NEW_PARTITION:
                for (int i = 0; i < event.getArray().length; i++) {
                    boxPane.setSquareValue(i, event.getArray()[i]);
                }
                break;
            case SELECT_PIVOT:
                boxPane.setSquareColor(event.getPivot());
                break;
            case SCAN:
                break;
            case SWAP:
                break;
            case PIVOT_SWAP:
                break;
        }
    }
    
    
    public BoxPane getBoxPane() {
        return boxPane;
    }
    
}
