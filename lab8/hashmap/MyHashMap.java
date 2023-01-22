package hashmap;

import java.util.*;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {


    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;
        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    private int numOfElem = 0;
    private int initialSize = 16;
    private double maxLoad = 0.75;
    private Set<K> setOfKey = new HashSet<>();

    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        return new Node(key, value);
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return new LinkedList<>();
    }

    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     *
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the table to create
     */
    private Collection<Node>[] createTable(int tableSize) {
        return new Collection[tableSize];
    }

    /** initial the table with specific bucket type */
    private void initiateTable(Collection<Node>[] table) {
        for (int i = 0; i < table.length; i += 1) {
            table[i] = createBucket();
        }
    }


    /** Return the bucket in the given table according to the hashcode of key */
    private Collection<Node> getBucket(Collection<Node>[] table, K key) {
        int hashcode = key.hashCode();
        int index = Math.floorMod(hashcode, table.length);
        return table[index];
    }


    /** Return the Iterator of the bucket in the given table according to the
     * hashcode of key
     */
    private Iterator<Node> getIterator(Collection<Node>[] table, K key) {
        return getBucket(table, key).iterator();
    }


    /** Return the Node in the given hashTable according to the key
     *  If there is not such Node, return null */
    private Node getNode(Collection<Node>[] table, K key) {
        Iterator<Node> targetIterator = getIterator(table, key);
        while (targetIterator.hasNext()) {
            Node testNode = targetIterator.next();
            if (testNode.key.equals(key)) {
                return testNode;
            }
        }
        return null;
    }

    /** Add Node into table without considering repeating */
    private void putIntoTable(Collection<Node>[] table, Node n) {
        Collection<Node> targetBucket = getBucket(table, n.key);
        targetBucket.add(n);
    }



    private Collection<Node>[] resizeTable(int capacity) {
        Collection<Node>[] tempTable = createTable(capacity);
        initiateTable(tempTable);
        for(Collection<Node> bucket : buckets) {
            Iterator<Node> chain = bucket.iterator();
            while (chain.hasNext()) {
                Node targetNode = chain.next();
                putIntoTable(tempTable, targetNode);
            }
        }
        return tempTable;
    }






    /** Constructors */
    public MyHashMap() {
        buckets = createTable(this.initialSize);
        initiateTable(buckets);
    }

    public MyHashMap(int initialSize) {
        buckets = createTable(initialSize);
        initiateTable(buckets);
    }

    /**
     * MyHashMap constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialSize initial size of backing array
     * @param maxLoad maximum load factor
     */
    public MyHashMap(int initialSize, double maxLoad) {
        this.maxLoad = maxLoad;
        buckets = createTable(initialSize);
        initiateTable(buckets);
    }

    @Override
    public void clear() {
        numOfElem = 0;
        initiateTable(buckets);
    }

    @Override
    public boolean containsKey(K key) {
        Node n = getNode(buckets, key);
        return (n != null);
    }

    @Override
    public V get(K key) {
        Node targetNode = getNode(buckets, key);
        if (targetNode == null) {
            return null;
        }
        return targetNode.value;
    }

    @Override
    public int size() {
        return numOfElem;
    }

    @Override
    public void put(K key, V value) {
        setOfKey.add(key);
        Collection<Node> targetBucket = getBucket(buckets, key);
        if (!containsKey(key)) {
            numOfElem += 1;
            targetBucket.add(createNode(key, value));
        }
        else {
            Iterator<Node> targetIterator = targetBucket.iterator();
            while (targetIterator.hasNext()) {
                Node targetNode = targetIterator.next();
                if (targetNode.key.equals(key)) {
                    targetNode.value = value;
                    break;
                }
            }
        }
        if (numOfElem > buckets.length * maxLoad) {
            buckets = resizeTable(2*buckets.length);
        }
    }

    @Override
    public Set<K> keySet() {
        return setOfKey;
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        return setOfKey.iterator();
    }
}
