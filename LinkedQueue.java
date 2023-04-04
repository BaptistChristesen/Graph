
/**
 * Write a description of class iunui here.
 *
 * @author  (your name here)
 * @version (today's date)
 */
public class LinkedQueue<T> implements QueueInterface<T> {
    private Node<T> frontNode;
    private Node<T> backNode;
    private int numElements;

    public LinkedQueue() {
        frontNode = null;
        backNode = null;
        numElements = 0;
    }

    public int size() {
        return numElements;
    }

    public boolean empty() {
        return numElements == 0;
    }

    public T add(T item) {
        Node<T> newNode = new Node<>(item, null);

        if (empty()) {
            frontNode = newNode;
        } else {
            backNode.setNextNode(newNode);
        }

        backNode = newNode;
        numElements++;

        return item;
    }

    public T peek() throws QueueUnderflowException {
        if (empty()) {
            throw new QueueUnderflowException("Queue is empty");
        }

        return frontNode.getData();
    }

    public T remove() throws QueueUnderflowException {
        if (empty()) {
            throw new QueueUnderflowException("Queue is empty");
        }

        T front = frontNode.getData();
        frontNode = frontNode.getNextNode();
        numElements--;

        if (empty()) {
            backNode = null;
        }

        return front;
    }

    public void clear() {
        frontNode = null;
        backNode = null;
        numElements = 0;
    }

    private class Node<T> {
        private T data;
        private Node<T> next;

        private Node(T dataPortion) {
            this(dataPortion, null);
        }

        private Node(T dataPortion, Node<T> nextNode) {
            data = dataPortion;
            next = nextNode;
        }

        private T getData() {
            return data;
        }

        private void setData(T newData) {
            data = newData;
        }

        private Node<T> getNextNode() {
            return next;
        }

        private void setNextNode(Node<T> nextNode) {
            next = nextNode;
        }
    }
}
