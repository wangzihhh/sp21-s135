package bstmap;

import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V>{
    private class Node {

        /** store the key of key-value pair of this node in the tree */
        private K key;
        /** store the value of key-value pair of this node in the tree */
        private V value;
        /** store the left subtree of this node in the tree */
        private Node left;
        /** store the right subtree of this node in the tree */
        private Node right;
        private Node(K k, V v, Node l, Node r) {
            key = k;
            value = v;
            left = l;
            right = r;
        }
    }


    /** store the size of the BSTMap */
    private int size = 0;
    private Node curTree;


    /** Using sentinel to construct an empty BSTMap
     *  The actual map starts from the right subtree */
    public BSTMap() {
        size = 0;
        curTree = null;
    }
    @Override
    public void clear() {
        size = 0;
        curTree = null;
    }

    @Override
    public boolean containsKey(K key) {
        Node targetTree = findNode(key, curTree);
        return (targetTree != null);
    }

    @Override
    public V get(K key) {
        Node targetTree = findNode(key, curTree);
        if (targetTree == null) {
            return null;
        }
        return targetTree.value;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        if (!containsKey(key)) {
            size += 1;
        }
        curTree = put(curTree, key, value);
    }

    public void printInOrder() {

    }
    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        return null;
    }


    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }

    /** return the subNode in the Node whose root equals key */
    private Node findNode(K key, Node n) {
        if (n == null) {
            return null;
        }
        int cmp = key.compareTo(n.key);
        if (cmp == 0) {
            return n;
        }
        else if (cmp < 0) {
            return findNode(key, n.left);
        }
        return findNode(key, n.right);
    }

    /** put a key-value pair into a Node and return it. */
    private Node put(Node n, K key, V value) {
        if (n == null) {
            return new Node(key, value, null, null);
        }
        int cmp = key.compareTo(n.key);
        if (cmp < 0) {
            n.left = put(n.left, key, value);
        }
        else if (cmp > 0) {
            n.right = put(n.right, key, value);
        }
        else {
            n.value = value;
        }
        return n;
    }

}
