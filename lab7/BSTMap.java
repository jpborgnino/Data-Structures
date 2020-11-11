import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>,V> implements Map61B<K,V> {
    private Node Root;
    private int size;

    private class Node {
        private K Key;
        private Node Right;
        private Node Left;
        private V Value;

        public Node(K i, V value, Node r, Node left) {
            Key = i;
            Value = value;
            Right = r;
            Left = left;
        }


    }

    public BSTMap() {
        size = 0;
        Root = null;
    }

    @Override
    public void clear() {
        size = 0;
        Root = null;

    }

    /* Returns true if this map contains a mapping for the specified key. */
    public boolean containsKey(K key) {
        if (Root == null) {return false;}
        return helperContains(key,Root);

    }
    private boolean helperContains(K key, Node root){
        if (root == null){
            return false;
        }
        double b = root.Key.compareTo(key);
        if (b == 0) {
            return true;}
        else if(b > 0) {
            return helperContains(key,root.Left);}
        else {
           return helperContains(key, root.Right);
        }

    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    public V get(K key) {
        if (size == 0) {return null;}
        if (!containsKey(key)) {return null;}
        return getHelper(key,this.Root);

    }
    private V getHelper(K key, Node root) {
        double b = root.Key.compareTo(key);
        if (b == 0) {
            return root.Value;}
        else if(b > 0) {
            return getHelper(key,root.Left);}
        else {
            return getHelper(key, root.Right);
        }


    }

    /* Returns the number of key-value mappings in this map. */
    public int size() {
        return this.size;
    }

    /* Associates the specified value with the specified key in this map. */
    public void put(K key, V value) {
        if (size == 0) {
            Root = new Node(key, value, null, null);
            this.size += 1;
            return;
        }

        put(key, value, this.Root);

    }

    private void put(K key, V value, Node root) {
        double b = root.Key.compareTo(key);
        if (root.Right == null && b < 0) {
            root.Right = new Node(key, value, null, null);
            this.size += 1;
        } else if (root.Left == null && b > 0) {
            root.Left = new Node(key, value, null, null);
            this.size += 1;
        } else if (b < 0) {
            put(key, value, root.Right);
        } else if (b > 0){
            put(key, value, root.Left);
        }
    }

    /* Returns a Set view of the keys contained in this map. */
    public Set<K> keySet() {
        throw new UnsupportedOperationException("invalid");
    }

    /* Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException. */
    public V remove(K key) {
        throw new UnsupportedOperationException("invalid");
    }

    public V remove(K key, V value) {
        throw new UnsupportedOperationException("invalid");
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException("invalid");
    }

}
