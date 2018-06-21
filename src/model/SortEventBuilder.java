package model;

public class SortEventBuilder {
    
    private SortEventType eventType;
    private int[] array = null;
    private boolean[] sorted = null;
    private int i = -1;
    private int j = -1;
    private int pivot = -1;
    
    public SortEvent build() {
        return new SortEvent(eventType, array, sorted, i, j, pivot);
    }
    
    public SortEventBuilder newPartition(int[] array, boolean[] sorted) {
        this.eventType = SortEventType.NEW_PARTITION;
        this.array = new int[array.length];
        System.arraycopy(array, 0, this.array, 0, this.array.length);
        this.sorted = new boolean[sorted.length];
        System.arraycopy(sorted, 0, this.sorted, 0, this.sorted.length);
        return this;
    }
    
    public SortEventBuilder selectPivot(int pivot) {
        this.eventType = SortEventType.SELECT_PIVOT;
        this.pivot = pivot;
        return this;
    }
    
    public SortEventBuilder scan(int i, int j) {
        this.eventType = SortEventType.SCAN;
        this.i = i;
        this.j = j;
        return this;
    }
    
    public SortEventBuilder swap(int i, int j) {
        this.eventType = SortEventType.SWAP;
        this.i = i;
        this.j = j;
        return this;
    }
    
    public SortEventBuilder pivotSwap(int i, int j, int pivot) {
        this.eventType = SortEventType.PIVOT_SWAP;
        this.i = i;
        this.j = j;
        this.pivot = pivot;
        return this;
    }

}
