package model;

import view.Callback;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * QuickSorter object for sorting int arrays
 * @author warycanary
 */
public class QuickSort {
    
    /**
     * Callbacks to be notified when sorting is done
     */
    private List<Callback> callbacks = new ArrayList<>();
    
    /**
     * Sleep time
     */
    private int time = 200;
    
    /**
     * Quick sort algorithm. Sorts an array from smallest to largest
     * @param array int array to sort
     * @param left minimum index in array to sort (usually 0)
     * @param right maximum index in array to sort (usually array.length - 1)
     */
    public void start(int[] array, int left, int right) {
        /* Stores each index in the array that has been sorted */
        boolean[] sorted = new boolean[array.length];
        for (Callback callback : callbacks) {
            callback.sendListSize(array.length);
        }
        sort(array, left, right, sorted);
    }
    
    /**
     * This method is called recursively on each partition
     * @param array int array to sort
     * @param left minimum index in array to sort (usually 0)
     * @param right maximum index in array to sort (usually array.length - 1)
     */
    private void sort(int[] array, int left, int right, boolean[] sorted) {
        sendEvent(new SortEventBuilder().newPartition(array, sorted).build());
        /* If sorted array*/
        if (left < right) {
            int split = partition(array, left, right);
            sorted[split] = true;
            sort(array, left, split - 1, sorted);
            sort(array, split + 1, right, sorted);
        /* If single index partition, mark sorted */
        } else if (left == right){
            sorted[left] = true;
        }
    }
    
    /**
     * Sort the array partition. All values smaller to the left of the pivot
     * and all values larger to the right of the pivot
     * @param array array to be sorted
     * @param left the left index of the partition and the pivot
     * @param right the right index of the partition
     * @return the index of the sorted element
     */
    private int partition(int[] array, int left, int right) {
        int i = left + 1, j = right, random = random(left, right);
        
        /* Add pivot information to events */
        sendEvent(new SortEventBuilder().selectPivot(random).build());
        
        /* Swap the random value to the pivot position */
        swap(array, random, left);
    
        /* Add new pivot information to events */
        sendEvent(new SortEventBuilder().selectPivot(left).build());
        
        /* Adds i and j starting position to events */
        sendEvent(new SortEventBuilder().scan(i, j).build());
        
        /* Loop until j crosses i */
        while (i <= j) {
            /* Increment i if not out of bounds, i is not greater than j
             * and value of i is less than the value of the pivot */
            while (i <= right && i <= j && array[i] <= array[left]) {
                i++;
            }
            /* Increment j if not out of bounds, j is not greater than i
             * and value of j is greater than the value of the pivot */
            while (j >= left + 1 && i <= j && array[j] >= array[left]) {
                j--;
            }
            /* Swap i and j unless crossed */
            if (i < j) {
                swap(array, i, j);
                /* Adds i and j starting position to events */
                sendEvent(new SortEventBuilder().swap(i, j).build());
            }
        }
        /* Swap the pivot with j */
        swap(array, left, j);
    
        /* Adds i and j ending position to events */
        sendEvent(new SortEventBuilder().pivotSwap(i, j, left).build());
        
        return j;
    }
    
    /**
     * Swaps the values at the index positions i and j in the array
     * @param array an array to swap values
     * @param i index of value to swap with j
     * @param j index of value to swap with i
     */
    private void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
    
    /**
     * Generates a random number between min and max. Used for
     * randomly picking a pivot
     * @param min minimum value that falls inside a partition
     * @param max maximum value that falls inside a partition
     * @return a random value between min and max inclusive
     */
    private int random(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
    
    /**
     * Adds a callback to the callback collection
     * @param callback notifies that sorting is complete
     */
    public void addCallback(Callback callback) {
        this.callbacks.add(callback);
    }
    
    private void setTime(int time) {
        this.time = time;
    }
    
    /**
     * Sleep for specified time and send event
     */
    private void sendEvent(SortEvent event) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        for (Callback callback : callbacks) {
            callback.displaySortEvent(event);
        }
    }
}
