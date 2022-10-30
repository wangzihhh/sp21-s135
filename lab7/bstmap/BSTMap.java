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


        // The following method are used as static method, but invoke by caller variable

        /** return the node in tree n of key-value pairs whose key is equal to
         * Key. */
       private Node get(Node n, K k) {
           if (n == null) {
               return null;
           }
           int cmp = k.compareTo(n.key);
           if (cmp < 0) {
               return get(n.left, k);
           }
           else if (cmp > 0) {
               return get(n.right, k);
           }
           return n;
       }
        private Node put(Node n, K k, V v) {
            if (n == null) {
                return new Node(k, v, null, null);
            }
            int cmp = k.compareTo(n.key);
            if (cmp < 0) {
                n.left = put(n.left, k, v);
            }
            else if (cmp > 0) {
                n.right = put(n.right, k, v);
            }
            else {
                n.value = v;
            }
            return n;
        }

        private void printInorder(Node n) {
            if (n == null) {
                return;
            }
            printInorder(n.left);
            System.out.print(n.key.toString() + " ");
            printInorder(n.right);
        }
    }


    /** store the size of the BSTMap */
    private int size = 0;
    private Node curTree;

    /** this instance variable has no actual meaning. Only use to call
     * method. e.g. The method by this variable can be seen as static method of
     * inner class Node */
    private Node caller = new Node(null, null, null, null);

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
        return caller.get(this.curTree, key) != null;
    }

    @Override
    public V get(K key) {
        Node lookup = caller.get(curTree, key);
        if (lookup == null) {
            return null;
        }
        return lookup.value;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        Node lookup = caller.get(curTree, key);
        if (lookup == null) {
            size += 1;
        }
        curTree = caller.put(curTree, key, value);
    }

    public void printInOrder() {
        caller.printInorder(this.curTree);
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


}
