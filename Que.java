
/**
 * Write a description of class Que here.
 *
 * @author  (your name here)
 * @version (today's date)
 */
public class Que<T> implements QueueInterface<T> {
    
    private Node<T> front;
    private Node<T> rear;
    private int size;
    
    // Constructor
    public Que() {
        front = null;
        rear = null;
        size = 0;
    }
    
    // Node class for Queue
    private static class Node<T> {
        private T data;
        private Node<T> next;
        
        public Node(T data, Node<T> next) {
            this.data = data;
            this.next = next;
        }
    }
    
    // Returns the logical size of the queue
    public int size() {
        return size;
    }
    
    // Tests if the queue is empty
    public boolean empty() {
        return front == null;
    }
    
    // Adds an item onto the rear of the queue
    public T add(T item) {
        Node<T> newNode = new Node<T>(item, null);
        if (rear == null) {
            front = newNode;
            rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
        size++;
        return item;
    }
    
    // Looks at the object at the front of the queue
    // without removing it from the queue
    public T peek() throws QueueUnderflowException {
        if (front == null) {
            throw new QueueUnderflowException("Queue is empty.");
        }
        return front.data;
    }
    
    // Removes the object at the front of the queue 
    // and returns that object as the value of this function
    public T remove() throws QueueUnderflowException {
        if (front == null) {
            throw new QueueUnderflowException("Queue is empty.");
        }
        T data = front.data;
        front = front.next;
        if (front == null) {
            rear = null;
        }
        size--;
        return data;
    }
    
    // Removes all of the elements from the queue
    public void clear() {
        front = null;
        rear = null;
        size = 0;
    }
    
}
