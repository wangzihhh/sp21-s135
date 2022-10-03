package deque;
public class ArrayDeque<T> {
    private int size;
    private int nextfront = 6;
    private int nextback = 7;
    private T[] items;

    // Creates an empty ArrayDeque.
    public ArrayDeque() {
        items = (T[]) new Object[8];
        nextfront = 3;
        nextback = 4;
        size = 0;
    }

    /** This is a helper method for addLast method
     *  It updates nextback variable when we call addLast
     */
    public void updateNextback() {
        if (nextback < items.length - 1) {
            nextback += 1;
        }
        else {
            nextback = 0;
        }
    }

    /** This is a helper method for addFirst method
     *  It updates nextfront variable when we call addFirst
     */
    public void updateNextfront() {
        if (nextfront > 0) {
            nextfront -= 1;
        }
        else {
            nextfront = items.length - 1;
        }
    }

    // Return the number of items in ArrayDeque.
    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    /** Return true if the ArrayDeque is full, not any space available
     *  Otherwise false
     *  This method is used to check whether we should call resize to enlarge
     */
    public boolean isFull() {
        return size() == items.length;
    }

    private static int mod(int a, int b) {
        if ((a % b) < 0) {
            return a % b + b;
        }
        return a % b;
    }

    // Resize the length of items in ArrayDeque.
    public void resize(int capacity) {
        T[] temp = (T[]) new Object[capacity];
        for (int i = 0; i < size; i += 1) {
            temp[i] = items[mod(nextfront + 1 + i, items.length)];
        }
        items = temp;
        nextback = size;
        nextfront = items.length - 1;
    }
    public void addFirst(T item) {
        if (isFull()) {
            resize(2 * size);
        }
        size += 1;
        items[nextfront] = item;
        nextfront = mod(nextfront - 1, items.length);
    }

    public void addLast(T item) {
        if (isFull()) {
            resize(2 * size);
        }
        size += 1;
        items[nextback] = item;
        nextback = mod(nextback + 1, items.length);
    }

    /** This method is used to check whether there is too much
     * waste in memory after we call remove method
     * when it returns true, we should resize items to save memory
     */

    public boolean needSmaller() {
        return (items.length / size) > 4;
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        size -= 1;
        int currentFront = mod(nextfront + 1, items.length);
        T removeVal = items[currentFront];
        items[currentFront] = null;
        nextfront = currentFront;
        if (needSmaller()) {
            resize(items.length/2);
        }
        return removeVal;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        size -= 1;
        int currentBack = mod(nextback - 1, items.length);
        T removeVal = items[currentBack];
        items[currentBack] = null;
        nextback = currentBack;
        if (needSmaller()) {
            resize(items.length/2);
        }
        return removeVal;
    }

    public T get(int index) {
        return items[mod(nextfront+index+1, items.length)];
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
}
