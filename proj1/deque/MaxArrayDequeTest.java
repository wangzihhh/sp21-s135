package deque;
import static org.junit.Assert.*;
import org.junit.Test;
public class MaxArrayDequeTest {
    @Test
    public void comparatorTest() {
        Dog d1 = new Dog("doge", 20);
        Dog d2 = new Dog("ben", 50);
        Dog d3 = new Dog("hape", 1);

        MaxArrayDeque<Dog> dogDeque = new MaxArrayDeque<>(Dog.getSizeComparator());
        dogDeque.addLast(d1);
        dogDeque.addLast(d2);
        dogDeque.addLast(d3);

        Dog testDog1 = dogDeque.max();
        assertEquals(d2, testDog1);

        Dog testDog2 = dogDeque.max(Dog.getNameComparator());
        assertEquals(d3, testDog2);
    }
}
