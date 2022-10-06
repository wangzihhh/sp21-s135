package deque;
import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {

    private class StuffNode {
        private T item;
        private StuffNode front;
        private StuffNode back;

        public StuffNode(T i, StuffNode f, StuffNode b) {
            item = i;
            front = f;
            back = b;
        }
    }

    private int size;
    private StuffNode sentinel;

    // Create an empty LinkedListDeque with sentinel.
    public LinkedListDeque() {
        sentinel = new StuffNode(null, null, null);
        sentinel.front = sentinel;
        sentinel.back =sentinel;
        size = 0;
    }

    // Add an item of type T to the front of the LinkedListDeque. (item is not null)
    public void addFirst(T item) {
        sentinel.back.front = new StuffNode(item, sentinel, sentinel.back);
        sentinel.back = sentinel.back.front;
        size += 1;
    }

    // Add an item of type T to the back of the LinkedListDeque. (item is not null)
    public void addLast(T item) {
        sentinel.front.back = new StuffNode(item, sentinel.front, sentinel);
        sentinel.front = sentinel.front.back;
        size += 1;
    }

    // Return the number of items in the LinkedListDeque.
    public int size() {
        return size;
    }

    //Removes and returns the item at the front of the LinkedListDeque.
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        size -= 1;
        T removeVal = sentinel.back.item;
        sentinel.back = sentinel.back.back;
        sentinel.back.front = sentinel;
        return removeVal;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        size -= 1;
        T removeVal = sentinel.front.item;
        sentinel.front = sentinel.front.front;
        sentinel.front.back = sentinel;
        return removeVal;
    }

    // Return the items at given index, if no such item exists, return null
    public T get(int index) {
        if (isEmpty()) {
            return null;
        }
        if (index < 0 || index >= size) {
            return null;
        }
        StuffNode p = sentinel.back;
        int i = index;
        while (i != 0) {
            p = p.back;
            i -= 1;
        }
        return p.item;
    }

    // Do the same functionality as get method but using recursion
    public T getRecursive(int index) {
        if (isEmpty()) {
            return null;
        }
        if (index < 0 || index >= size) {
            return null;
        }
        return helpGetRecursive(sentinel.back, index);
    }

    /** Helper function for getRecursive method
     s represents a StuffNode without sentinel.
     */
    private T helpGetRecursive(StuffNode s, int index) {
        if (index == 0) {
            return s.item;
        }
        return helpGetRecursive(s.back, index - 1);
    }

    public void printDeque() {
        for (int i = 0; i < size(); i += 1) {
            if (i == size() - 1) {
                System.out.print(get(i));
            } else {
                System.out.print(get(i) + " ");
            }
        }
        System.out.println();
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (! (o instanceof Deque)) {
            return false;
        }
        Deque<T> cmpDeque = (Deque<T>) o;
        if (size() != cmpDeque.size()) {
            return false;
        }
        if (isEmpty() && cmpDeque.isEmpty()) {
            return true;
        }
        for (int i = 0; i < size(); i += 1) {
            if (! get(i).equals(cmpDeque.get(i))) {
                return false;
            }
        }
        return true;
    }

    public Iterator<T> iterator() {
        return new dequeIterator();
    }

    private class dequeIterator implements Iterator<T> {
        private int wispos = 0;
        public boolean hasNext() {
            return wispos < size;
        }

        public T next() {
            T returnVal = get(wispos);
            wispos += 1;
            return returnVal;
        }
    }
}
