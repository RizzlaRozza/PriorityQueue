/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package queuemanager;

import java.util.Arrays;

/**
 *
 * @author roryk
 */
public class UnorderedArray<T> implements PriorityQueue<T> {
    
    int[] arrayPriority = new int[] {1, 2, 3};
    
    private T[] arrayElements = (T[]) new Object[8];
    
    private int priorityIndex = arrayPriority.length;
    
    /**
     * The index of the last item stored.
     *
     * This is equal to the item count minus one.
     */
    private int tailIndex;
    
    
    
    
    // add - get array, add the new item to the end.  Get length of array, add item to array length + 1;
    
    public void enqueue(T name, int priority){
        
        //  create new array from old array and allocate one more element
        arrayPriority = Arrays.copyOf(arrayPriority, arrayPriority.length + 1); 
        arrayPriority[arrayPriority.length - 1] = priority;        
        arrayElements[arrayElements.length - 1] = name;
        
    }
    
    public T dequeue(int size) {
        
        // find out which array element has the highest priority and assign the index number to "priorityIndex"
        for(int i = 0; i < arrayElements.length; i++){
            if(i < priorityIndex){
                priorityIndex = i;
            }  
        }
        
        // create a new array with a capacity of 1 less element than original
        int[] newArray = new int[arrayPriority.length - 1];
        
        // Copy the elements except the index from original array to the new array
        for (int i = 0, k = 0; i < arrayPriority.length; i++) {
  
            // if the index is the removal element index
            if (i == priorityIndex) {
                continue;
            }
  
            // if the index is not the removal element index
            newArray[k++] = arrayPriority[i];
        }
        
        return priorityIndex;
    }
    
    @Override
    public boolean isEmpty() {
        return tailIndex < 0;
    }
    
    
    
    
}
