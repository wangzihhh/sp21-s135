package deque;
import org.junit.Test;
import static org.junit.Assert.*;
public class ArrayDequeTest {

    @Test
    public void addIsEmptyTest() {
        ArrayDeque<Integer> testDeque = new ArrayDeque<>();
        assertTrue(testDeque.isEmpty());
        testDeque.addFirst(0);
        testDeque.addLast(1);
        assertFalse(testDeque.isEmpty());
    }

    @Test
    public void addSizeGetTest() {
        ArrayDeque<Integer> testDeque = new ArrayDeque<>();
        testDeque.addFirst(3);
        testDeque.addFirst(2);
        testDeque.addFirst(1);
        testDeque.addLast(4);
        testDeque.addLast(5);
        testDeque.addLast(6);
        assertFalse(testDeque.isEmpty());
        assertEquals(6, testDeque.size());
        int ele1 = testDeque.get(0);
        assertEquals(1, ele1);
        int ele6 = testDeque.get(5);
        assertEquals(6, ele6);
    }

    @Test
    public void addEnlargeTest() {
        ArrayDeque<Integer> testDeque = new ArrayDeque<>();
        for (int i = 5; i < 1000; i += 1) {
            testDeque.addLast(i);
        }
        for (int j = 0; j < 5; j += 1) {
            testDeque.addFirst(4 - j);
        }
        assertEquals(1000, testDeque.size());
        int firstEle = testDeque.get(0);
    }

    @Test
    public void removeSizeTest() {
        ArrayDeque<Integer> testDeque = new ArrayDeque<>();
        for (int i = 0; i < 1000; i += 1) {
            testDeque.addLast(i);
        }
        for (int j = 0; j < 800; j += 1) {
            testDeque.removeLast();
        }
        assertEquals(200, testDeque.size());
    }
}
