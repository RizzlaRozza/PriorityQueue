/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package queuemanager;

/**
 *
 * @author roryk
 */
public class UnsortedLinkedListPriorityQueue<T> implements PriorityQueue<T> {
    
    private PriorityItem<T> head;
    private int qSize = 0;
    
    
    public UnsortedLinkedListPriorityQueue() {
        head = null;
    }
    
    
    // method reused from SortedArrayPriorityQueue.java file provided for assessment
    @Override
    public T head() throws QueueUnderflowException {
        if (isEmpty()) {
            throw new QueueUnderflowException();
        }
        else {
            return ((PriorityItem<T>) head).getItem();
        }
    }
    
    
    /**
     * Enqueue method creates a new item and increases the size of the queue
     * @param item
     * @param priority
     * @throws QueueOverflowException 
     */
    @Override
    public void add(T item, int priority) throws QueueOverflowException {
        head = new PriorityItem<T>(item, priority, head);
        qSize++;
    }
    
    
    
    @Override
    public void remove() throws QueueUnderflowException {
        
        int topPriority;
        int counter = 0;
        int locationOfTopPriority = 0;
        
        // container used to compare with queue items
        PriorityItem container = head;
        
        // result used to set final result after calculations
        PriorityItem result = head;
        
        if (isEmpty()) {
            throw new QueueUnderflowException();
        } 
        else {            
            if(head.getNextPriorityItem() == null) {
                head = null;
            } 
            else {
                // new priority item
                topPriority = container.getPriority();
                
                // loop through queue to find the highest priority item, 
                while (counter <= qSize) {
                    
                    /*  */
                    if (topPriority <= container.getPriority()) {
                        topPriority = container.getPriority();
                        container = container.getNextPriorityItem();
                        locationOfTopPriority = counter;
                    } else {
                        container = container.getNextPriorityItem();
                    }
                    counter++;
                }
                
                result = head.getNextPriorityItem();
                
                counter = 0;                            
                while (counter < (locationOfTopPriority))
                {
                    result = result.getNextPriorityItem();
                    counter++;
                }
                result.setNextPriorityItem(result.getNextPriorityItem().getNextPriorityItem());
                qSize--;
            }
            
        } // end of isEmpty check
        
    }

    
    @Override
    public boolean isEmpty() {
        return (head == null);
    }
    
    
    @Override
    public String toString() {
        String result = "[";
        for (PriorityItem<T> node = head; node != null; node = node.getNextPriorityItem()) {
            if (node != head) {
                result += ", ";
            }
            result = result + "(" + node.getItem() + " ," + node.getPriority() + ")";
        }
        result = result + "]";

        return result;
    }
    
}
