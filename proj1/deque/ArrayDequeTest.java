package deque;

import net.sf.saxon.ma.arrays.ArrayFunctionSet;
import org.checkerframework.checker.units.qual.A;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;
public class ArrayDequeTest {
    @Test
    public void addFirstTest() {
        ArrayDeque<Integer> testDeque = new ArrayDeque<>();
        for (int i = 0; i < 10; i += 1) {
            testDeque.addLast(i);
        }
    }

    @Test
    public void addLastTest() {
        ArrayDeque<Integer> testDeque = new ArrayDeque<>();
        for (int i = 0; i < 10; i += 1) {
            testDeque.addLast(i);
        }
        testDeque.addFirst(-1);
    }

    @Test
    public void removeFirstTest() {
        ArrayDeque<Integer> testDeque = new ArrayDeque<>();
        for (int i = 0; i < 200; i += 1) {
            testDeque.addLast(i);
        }
        for (int j = 0; j < 180; j += 1) {
            testDeque.removeFirst();
        }
        int removeval = testDeque.removeFirst();
        assertEquals(19, testDeque.size());
        assertEquals(180, removeval);
    }

    @Test
    public void getTest() {
        ArrayDeque<Integer> testDeque = new ArrayDeque<>();
        for (int i = 0; i < 201; i += 1) {
            testDeque.addLast(i);
        }
        for (int j = -1; j > -101; j-= 1) {
            testDeque.addFirst(j);
        }
        int getVal = testDeque.get(200);
        assertEquals(100, getVal);
    }
}
