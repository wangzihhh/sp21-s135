package deque;

public class LinkedListDeque<T> {

    /** This is a nested class: a StuffNode with 2 pointers. */
    private class StuffNode {
        T stuff;
        StuffNode left;
        StuffNode right;

        // Create an empty StuffNode
        StuffNode() {
            stuff = null;
            left = this;
            right = this;
        }

        StuffNode(T x, StuffNode l, StuffNode r) {
            stuff = x;
            left = l;
            right = r;
        }

    }

    private int size;
    private StuffNode sentinel;

    /** The right pointer of sentinel points at the Node of the first element;
     *  The left pointer of sentinel points at the Node of the last element.
     */

    // Create an empty LinkedListDeque with sentinel.
    public LinkedListDeque() {
        size = 0;
        sentinel = new StuffNode();
    }

    public void addFirst(T item) {
        size += 1;
        sentinel.right.left = new StuffNode(item, sentinel, sentinel.right);
        sentinel.right = sentinel.right.left;
    }

    public void addLast(T item) {
        size += 1;
        sentinel.left.right = new StuffNode(item, sentinel.left, sentinel);
        sentinel.left = sentinel.left.right;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        if (size == 0) {
            return;
        }
        for (int index = 0; index < size; index += 1) {
            System.out.print(get(index) + " ");
        }
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        size -= 1;
        T firstItem = sentinel.right.stuff;
        sentinel.right = sentinel.right.right;
        sentinel.right.left = sentinel;
        return firstItem;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        size -= 1;
        T lastItem = sentinel.left.stuff;
        sentinel.left = sentinel.left.left;
        sentinel.left.right = sentinel;
        return lastItem;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        StuffNode p = sentinel;
        for (int i = 0; i <= index; i += 1) {
            p = p.right;
        }
        return p.stuff;
    }
}
