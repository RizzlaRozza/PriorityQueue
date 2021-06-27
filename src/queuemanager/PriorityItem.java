package queuemanager;

/**
 * A wrapper for bundling up an item and its integer priority.
 * 
 * @param <T>
 */
public class PriorityItem<T> {

    private T item;
    private final int priority;
    
    private PriorityItem<T> next;
    
    public PriorityItem(T item, int priority) {
        this.item = item;
        this.priority = priority;
    }

    public PriorityItem(T item, int priority, PriorityItem T) {
        this.item = item;
        this.priority = priority;
        // overload construstor to include the 'next' variable
        this.next = next;
    }

    public T getItem() {
        return item;
    }

    public int getPriority() {
        return priority;
    }
    
    public PriorityItem<T> getPriorityItem() {
        return next;
    }
    
    public void setPriorityItem(T item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "(" + getItem() + ", " + getPriority() + ")";
    }
}
