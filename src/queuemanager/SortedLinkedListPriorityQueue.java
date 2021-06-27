package queuemanager;


/**
 * 
 * @author roryk
 * @param <T> 
 * 
 * References:
 *  "Add" method was developed using the following reference: https://stackoverflow.com/questions/27347940/insert-into-sorted-linked-list
 *  "Head" method reused from SortedArrayPriorityQueue file
 * 
 */



public class SortedLinkedListPriorityQueue<T> implements PriorityQueue<T> {

    

    /**
     * variable "head" stores list item at the head of the queue
     */    
    private PriorityItem<T> head;
    private PriorityItem<T> tail;
    private int tailIndex;
    
    
    /**
     * Sets head and tail to null
     */
    public SortedLinkedListPriorityQueue() {
        head = null;
        tail = null;
        tailIndex = -1;
    }
    
    
    public T head() throws QueueUnderflowException{
        if (isEmpty()) {
            throw new QueueUnderflowException();
        } else {
            return ((PriorityItem<T>) head).getItem();
        }
    }

    /**
     * Adds an item to the list (queue)
     * @param item
     * @param priority
     * @throws QueueOverflowException 
     */
    @Override
    public void add(T item, int priority) throws QueueOverflowException {
        
        PriorityItem addNewItem = new PriorityItem(item, priority);
        if (head == null){
            head = addNewItem;
        }
        // insert in head if x is lower than the head
        else if(head.getPriority() < priority){            
            this.head = head;
        }
        // find the first Item in the queue which value is lower than x (or the tail)
        else{
             PriorityItem existing;
            for (existing = head; existing.getNextPriorityItem() != null && priority >= existing.getNextPriorityItem().getPriority(); existing = existing.getNextPriorityItem());
            // remove duplicates
            if (priority != existing.getNextPriorityItem().getPriority()) {
                addNewItem.setNextPriorityItem(existing.getNextPriorityItem());
                existing.setNextPriorityItem(addNewItem);
            }
             
        }
    }
    

    @Override
    public void remove() throws QueueUnderflowException {
        // Throws PriQUnderflowException if this priority queue is empty;
        // otherwise, removes element with highest priority from this 
        // priority queue and returns it.
        if (isEmpty()) {
            throw new QueueUnderflowException();
        } else {
            // if there is no item after head set head to null else focus on next item in queue
            if(head.getNextPriorityItem() == null) {
                head = null;
            }
            else {
                head = new PriorityItem<T>(head.getNextPriorityItem().getItem(), head.getNextPriorityItem().getPriority(), head.getNextPriorityItem().getNextPriorityItem());
            }         
        }
    }

    /**
     * @return Returns true if this priority queue is empty; otherwise, returns false.
     */
    @Override
    public boolean isEmpty() {
        return (head == null);
    }

    // error here to print queue
    @Override
    public String toString() {
        String result = "[";
        for (PriorityItem<T> node = head; node != null; node = node.getNextPriorityItem())
        {
            if (node != head)
            {
                result += ", ";
            }
            result = result + "(" + node.getItem() + " ," + node.getPriority() + ")";
        }
        result = result + "]";

        return result;
        
        
        

    }
}

