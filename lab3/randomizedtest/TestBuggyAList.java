package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
    @Test
    public void testThreeAddTreeRemove() {
        AListNoResizing<Integer> robustList = new AListNoResizing<>();
        BuggyAList<Integer> buggyList = new BuggyAList<>();
        for (int i = 1; i < 4; i += 1) {
            robustList.addLast(i);
            buggyList.addLast(i);
        }
        assertEquals(robustList.size(), buggyList.size());
        for (int j = 0; j < 3; j += 1) {
            int a = robustList.removeLast();
            int b = buggyList.removeLast();
            assertEquals(a, b);
        }
    }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> L1 = new AListNoResizing<>();
        BuggyAList<Integer> L2 = new BuggyAList<>();
        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L1.addLast(randVal);
                L2.addLast(randVal);
            } else if (operationNumber == 1) {
                // size
                int size1 = L1.size();
                int size2 = L2.size();
                assertEquals(size1, size2);
            } else if (operationNumber == 2) {
                // getLast
                if (L1.size() == 0 || L2.size() == 0) {
                    continue;
                }
                int last1 = L1.getLast();
                int last2 = L2.getLast();
                assertEquals(last1, last2);
            } else if (operationNumber == 3) {
                // removeLast
                if (L1.size() == 0 || L2.size() == 0) {
                    continue;
                }
                int remove1 = L1.removeLast();
                int remove2 = L2.removeLast();
                assertEquals(remove1, remove2);
            }
        }
    }
}
