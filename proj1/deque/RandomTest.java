package deque;
import static org.junit.Assert.*;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;


/** This method assume that LinkedListDeque is correctly implemented, and use
 * RandomTest to verify the implementation of ArrayDeque.
 */
public class RandomTest {

    @Test
    public void addSizeRemoveTest() {
        int N = 5000;
        LinkedListDeque<Integer> L1 = new LinkedListDeque<>();
        ArrayDeque<Integer> L2 = new ArrayDeque<>();
        for (int i = 0; i < N; i += 1) {
            int operator = StdRandom.uniform(0,5);
            if (operator == 0) {
                L1.addFirst(i);
                L2.addFirst(i);
            }
            else if (operator == 1) {
                L1.addLast(i);
                L2.addLast(i);
            }
            else if (operator == 2) {
                int num1 = L1.size();
                int num2 = L2.size();
                assertEquals(num1, num2);
            }
            else if (operator == 3 && L1.size() > 0 && L2.size() > 0) {
                int removeNum1 = L1.removeFirst();
                int removeNum2 = L2.removeFirst();
                assertEquals(removeNum1, removeNum2);
                int size1 = L1.size();
                int size2 =L2.size();
                assertEquals(size1, size2);
            }
            else if (operator == 4 && L1.size() > 0 && L2.size() > 0) {
                int removeNum1 = L1.removeLast();
                int removeNum2 = L2.removeLast();
                assertEquals(removeNum1, removeNum2);
                int size1 = L1.size();
                int size2 =L2.size();
                assertEquals(size1, size2);
            }
        }
    }


}
