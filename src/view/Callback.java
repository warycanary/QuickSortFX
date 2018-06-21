package view;

import javafx.application.Platform;
import model.SortEvent;

public class Callback {
    
    QuickSortFX quickSortFX;
    
    public Callback(QuickSortFX quickSortFX) {
        this.quickSortFX = quickSortFX;
    }
    
    public void sendListSize(int size) {
        Platform.runLater(() -> quickSortFX.getBoxPane().initialiseBoxes(size));
    }
    
    public void displaySortEvent(SortEvent event) {
        Platform.runLater(() -> quickSortFX.displayEvent(event));
    }
}
