package deque;
import org.junit.Test;
import static org.junit.Assert.*;
public class MaxArrayDequeTest {

    @Test
    public void DogTest() {
        Dog d1 = new Dog("adog", 1, 100);
        Dog d2 = new Dog("bdog", 2, 102);
        Dog d3 = new Dog("cdog", 3, 103);
        Dog d4 = new Dog("ddog", 4, 104);
        MaxArrayDeque<Dog> dogDeque = new MaxArrayDeque<>(Dog.getNameCmp());
        dogDeque.addLast(d1);
        dogDeque.addLast(d2);
        dogDeque.addLast(d3);
        dogDeque.addLast(d4);
        assertEquals(d4, dogDeque.max());
        assertEquals(d4, dogDeque.max(Dog.getAgeCmp()));
        assertEquals(d4, dogDeque.max(Dog.getWeightCmp()));
    }
}
