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

    
    protected LLNode<T> previous;   // node preceding location
    protected int numElements = 0; // number of elements in this queue
    private int tailIndex;

    /**
     * variable "head" stores list item at the head of the queue
     */    
    private PriorityItem<T> head;
    private PriorityItem<T> tail;
    
    
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
            for (existing = head; existing.getPriorityItem() != null && priority >= existing.getPriorityItem().getPriority(); existing = existing.getPriorityItem());
            // remove duplicates
            if (priority != existing.getPriorityItem().getPriority()) {
                addNewItem.setPriorityItem(existing.getPriorityItem());
                existing.setPriorityItem(addNewItem);
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
            if(head.getPriorityItem() == null) {
                head = null;
            }
            else {
                head = new PriorityItem<T>(head.getPriorityItem().getItem(), head.getPriorityItem().getPriority(), head.getPriorityItem().getPriorityItem());
            }         
        }
    }

    public int size() {
        // Returns the number of elements on this priority queue. 
        return numElements;
    }

    /**
     * @return Returns true if this priority queue is empty; otherwise, returns false.
     */
    public boolean isEmpty() {
        return (head == null);
    }

    public boolean isFull() {
        // This priority queue is unbounded so always returns false.
        return false;
    }

    public String toString() {
        // Returns a nicely formatted string that represents this priority queue.
        String result = "";
        if (isEmpty()) {
            return "Queue is empty.";
        }
        LLNode<T> currNode = front;

        while (currNode != null) {
            result += currNode.getInfo().toString() + ", ";
            currNode = currNode.getLink();
        }
        return result;
    }
}

