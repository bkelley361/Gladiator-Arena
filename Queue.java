

public class Queue<T> {
    Node head;
    Node tail;
    int size;


    private class Node {
        T data;
        Node next;
    }

    public Queue() {
        head = new Node();
        head = null;
        tail = new Node();
        tail = null;
        size = 0;
        }

    public void enqueue(T i) {
        Node n = new Node();
        n.data = i;
        n.next = null;
        size++;
        if (head != null) {
            tail.next = n;
            tail = n;
        }
        else {
            head = n;
            tail = n;
        }
    }

    public T dequeue() {
        if (head != null) {
            T temp = head.data;
            head = head.next;
            size--;
            if (size == 0) {
                tail = null;
            }
            return temp;
        }
        else {
            throw new IllegalArgumentException("Cannot do that");    
        }
    }

    public T peek() {
        return head.data;
    }

    public boolean isEmpty() {
        if (head != null) {
            return false;
        }
        return true;
    }

    public int size() {
        return size;
    }
}