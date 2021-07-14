import java.util.*;

public class Queue<T> {
    
    int index = 0;
    int length = 0;
    int front = 0;
    int back = 0;
    LinkedList<T> queue = new LinkedList<T>(); 

    public Queue() {
        
    }

    public void enqueue(T item) {
        queue.addLast(item);
        index += 1;
        front += 1;
    }

    public T dequeue() {
        T last;
        if ( ! queue.isEmpty()) {
            last = queue.getFirst();
            queue.removeFirst();
        }
        else {
            return null;
        }
        front -= 1;
        index -= 1;

        return last;
    }

    public T peek() {
        return queue.getFirst();
    }

    public int size() {
        return queue.size();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}