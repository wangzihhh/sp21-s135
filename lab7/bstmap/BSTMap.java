package bstmap;

import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V>{
    /** The nested class we use to represent the BST Node.
     *  For the purpose of avoiding naked recursive data structure.  */
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


    /** The instance variable of our BSTMap */

    /** Store the size of the BSTMap. */
    private int size = 0;
    /** store the naked BST structure. */
    private Node curTree;

    public BSTMap() {     // Constructor of the empty BSTMap.
        size = 0;
        curTree = null;
    }

    /** The following are the helper methods we need to build up the functionality
     * of BSTMap. */

    /** Return the subTree of n s.t. the key of root of this subTree
     *  equals to key. Return null if there is no such subTree. */
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

    /** put a key-value pair into a Tree and return the updated Tree. */
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

    /** Print the given Tree according to the order of key. */
    private void printNode(Node n) {
        if (n == null) {
            return;
        }
        printNode(n.left);
        System.out.print(n.key.toString());
        printNode(n.right);
    }

    /** Return 0 if this node has no child; 1 if it only has 1 left child; 2 if it
     *  only has right child; 3 if it has 2 children */
    private int rootType(Node n) {
        int i = 0;
        if (n.left != null) {
            i += 1;
        }
        if (n.right != null) {
            i += 2;
        }
        return i;
    }

    /** Return the successor of the Tree when we delete the Node with 2 children */
    private Node HibbardNode(Node n) {
        Node temp = n.left;
        while (temp.right != null) {
            temp = temp.right;
        }
        return temp;
    }

    /** Delete the key-value pair in a BST and return it */
    private Node helpRemove(Node n, K key) {
        if (n == null) {
            return null;
        }
        int cmp = key.compareTo(n.key);
        if (cmp == 0) {
            int indicator = rootType(n);
            if (indicator == 0) {
                n = null;
            }
            else if (indicator == 1) {
                n = n.left;
            }
            else if (indicator == 2) {
                n = n.right;
            }
            else {
                Node temp = HibbardNode(n);
                n.key = temp.key;
                n.value = temp.value;
                n.left = helpRemove(n.left, temp.key);
            }
        }
        else if (cmp < 0) {
            n.left = helpRemove(n.left, key);
        }
        else {
            n.right = helpRemove(n.right, key);
        }
        return n;
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
        printNode(curTree);
    }
    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key) {
        V returnVal = get(key);
        curTree = helpRemove(curTree, key);
        return returnVal;
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
