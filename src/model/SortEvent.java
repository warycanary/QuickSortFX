package model;

public class SortEvent {

    private SortEventType eventType;
    private int[] array;
    private boolean[] sorted;
    private int i;
    private int j;
    private int pivot;
    
    public SortEvent(SortEventType eventType, int[] array, boolean[] sorted, int i, int j, int pivot) {
        this.eventType = eventType;
        this.array = array;
        this.sorted = sorted;
        this.i = i;
        this.j = j;
        this.pivot = pivot;
    }
    
    
    public int[] getArray() {
        return array;
    }
    
    public boolean[] getSorted() {
        return sorted;
    }
    
    public int getI() {
        return i;
    }
    
    public int getJ() {
        return j;
    }
    
    public int getPivot() {
        return pivot;
    }
    
    public SortEventType getType() {
        return this.eventType;
    }
}
