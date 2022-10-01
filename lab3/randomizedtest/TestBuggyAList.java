package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
    @Test
    public void testThreeAddThreeRemove() {
        AListNoResizing<Integer> correctList = new AListNoResizing<>();
        BuggyAList<Integer> testList = new BuggyAList<>();

        correctList.addLast(10);
        correctList.addLast(8);
        correctList.addLast(5);

        testList.addLast(10);
        testList.addLast(8);
        testList.addLast(5);

        assertEquals(correctList.size(), testList.size());
        assertEquals(correctList.removeLast(), testList.removeLast());
        assertEquals(correctList.removeLast(), testList.removeLast());
        assertEquals(correctList.removeLast(), testList.removeLast());
    }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> testList = new BuggyAList<>();
        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                testList.addLast(randVal);
            } else if (operationNumber == 1) {
                // size
                int size = L.size();
                int testSize = testList.size();
                assertEquals(size, testSize);
            } else if (operationNumber == 2) {
                if (L.size() == 0 && testList.size() == 0) {
                    continue;
                }
                int removeVal =L.removeLast();
                int testRemoveVal = testList.removeLast();
                assertEquals(removeVal, testRemoveVal);
            } else {
                if (L.size() == 0 && testList.size() == 0) {
                    continue;
                }
                int lastVal = L.getLast();
                int testLastVal = testList.getLast();
                assertEquals(lastVal, testLastVal);
            }
        }
    }
}
