import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;


public class GladiatorArenaTest {
    @Test
    public void QueueTest1() {
        Queue<String> q = new Queue<>();
        assertEquals(0, q.size());
    }
    @Test
    public void QueueTest2() {
        Queue<String> q = new Queue<>();
        q.enqueue("J");
        assertEquals(1, q.size());
    }
    @Test
    public void QueueTest3() {
        Queue<String> q = new Queue<>();
        q.enqueue("J");
        q.enqueue("H");
        assertEquals(2, q.size());
        assertEquals("J", q.peek());
    }
    @Test
    public void QueueTest4() {
        Queue<String> q = new Queue<>();
        q.enqueue("J");
        q.enqueue("H");
        assertEquals(2, q.size());
        q.dequeue();
        assertEquals("H", q.peek());
    }

    @Test
    public void QueueTest5() {
        Queue<String> q = new Queue<>();
        assertEquals(true, q.isEmpty());
    }

    @Test
    public void QueueTest6() {
        Queue<String> q = new Queue<>();
        q.enqueue("J");
        q.enqueue("H");
        assertEquals(2, q.size());
        q.dequeue();
        q.dequeue();
        assertEquals(null, q.dequeue());
    }



}
