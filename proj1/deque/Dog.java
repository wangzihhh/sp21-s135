package deque;
import java.util.Comparator;
public class Dog {
    public int size;
    public String name;

    public Dog(String n, int s) {
        name = n;
        size = s;
    }

    private static class NameComparator implements Comparator<Dog> {
        public int compare(Dog a, Dog b) {
            return a.name.compareTo(b.name);
        }
    }

    public static NameComparator getNameComparator() {
        return new NameComparator();
    }

    private static class SizeComparator implements Comparator<Dog> {
        public int compare(Dog a, Dog b) {
            return a.size - b.size;
        }
    }

    public static SizeComparator getSizeComparator() {
        return new SizeComparator();
    }
}
