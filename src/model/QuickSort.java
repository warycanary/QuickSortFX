package model;

import java.util.concurrent.ThreadLocalRandom;

/**
 * QuickSorter object for sorting int arrays
 * @author warycanary
 */
public class QuickSort {
    
    /**
     * Quick sort algorithm. Sorts an array from smallest to largest
     * @param array int array to sort
     * @param left minimum index in array to sort (usually 0)
     * @param right maximum index in array to sort (usually array.length - 1)
     */
    public void sort(int[] array, int left, int right) {
        /* If sorted array*/
        if (left < right) {
            int sorted = partition(array, left, right);
            sort(array, left, sorted - 1);
            sort(array, sorted + 1, right);
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
        int i = left + 1, j = right;
        /* Swap the random value to the pivot position */
        swap(array, random(left, right), left);
        /* Loop until j crosses i */
        while (i < j) {
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
            }
        }
        /* Swap the pivot with j */
        swap(array, left, j);
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
}
