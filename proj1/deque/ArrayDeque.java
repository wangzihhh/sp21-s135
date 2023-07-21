package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Iterable<T>, Deque<T> {

    private int size;

    private T[] items;

    private int nextFirstPos; //This indicates the future position for addFirst.

    private int nextLastPos; // This indicates the future position for addLast.

    /** Invariants:
     *  There is tight connection between the change of nextFirst/nextLast and
     *  mod operation after we call addFirst/Last and removeFirst/Last.
     *  Specifically,
     *  nextFirst updates into (nextFirst - 1) mod items.length
     *  nextLast updates into (nextLast + 1) mod items.length.
     *  Similar as Remove operation.
     */

    public ArrayDeque() {
        size = 0;
        items = (T[]) new Object[8];
        nextFirstPos = 3;
        nextLastPos = 4;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void printDeque() {
        if (isEmpty()) {
            return;
        }
        for (T x : this) {
            System.out.println(x + " ");
        }
    }

    public void addFirst(T x) {
        if (needToBeLarge()) {
            int newLength = 2 * items.length;
            resizeItems(newLength);
        }
        items[nextFirstPos] = x;
        nextFirstPos = modLength(nextFirstPos - 1);
        size += 1;
    }

    public void addLast(T x) {
        if (needToBeLarge()) {
            int newLength = 2 * items.length;
            resizeItems(newLength);
        }
        items[nextLastPos] = x;
        nextLastPos = modLength(nextLastPos + 1);
        size += 1;
    }

    public T get(int index) {
        int actualPos = modLength(index + nextFirstPos + 1);
        return items[actualPos];
    }


    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        if (needToBeSmall()) {
            int newLength = items.length / 2;
            resizeItems(newLength);
        }
        int currentFirstPos = getFirstPos();
        T returnItem = items[currentFirstPos];
        items[currentFirstPos] = null;
        nextFirstPos = currentFirstPos;
        size -= 1;
        return returnItem;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        if (needToBeSmall()) {
            int newLength = items.length / 2;
            resizeItems(newLength);
        }
        int currentLastPos = getLastPos();
        T returnItem = items[currentLastPos];
        items[currentLastPos] = null;
        nextLastPos = currentLastPos;
        size -= 1;
        return returnItem;
    }

    public Iterator<T> iterator() {
        return new Seer();
    }




    // The following are private helper methods we will use.

    /** Return the value of Mod(x, items.length) in a common way */
    private int modLength(int x) {
        return Math.floorMod(x, items.length);
    }

    private int getFirstPos() {
        return modLength(nextFirstPos + 1);
    }

    private int getLastPos() {
        return modLength(nextLastPos - 1);
    }
    private boolean needToBeLarge() {
        return size == items.length;
    }

    private boolean needToBeSmall() {
        int len = items.length;
        return (len > 16) && (4 * size < len);
    }

    private void resizeItems(int capacity) {
        int currentLength = size;
        int currentFirst = getFirstPos();
        T[] temp = (T[]) new Object[capacity];
        for (int i = 0; i < currentLength; i += 1) {
            int targetIndex = modLength(currentFirst + i);
            temp[i] = items[targetIndex];
        }
        items = temp;
        nextFirstPos = temp.length - 1;
        nextLastPos = currentLength;
    }

    private class Seer implements Iterator<T> {
        int wisPos;

        public Seer() {
            wisPos = 0;
        }

        @Override
        public boolean hasNext() {
            return wisPos < size;
        }

        @Override
        public T next() {
            T nextVal = get(wisPos);
            wisPos += 1;
            return nextVal;
        }
    }
}
