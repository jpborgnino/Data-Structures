import java.security.Key;
import java.util.*;
import java.util.ArrayList;

public class MyHashMap <K,V> implements Map61B<K,V>{
private double loadFactor = 0.75;
private int arraySize = 16;
private HashSet<V> valMap;
private HashSet<K> keySet = new HashSet<K>();
private ArrayList[] map;

    public MyHashMap() {
        map = new ArrayList[16];
        for (int i=0;i < 16; i++){
            map[i] = new ArrayList();
        }

    }
    public MyHashMap(int initialSize) {
        map = new ArrayList[initialSize];
        for (int i=0;i < initialSize; i++){
            map[i] = new ArrayList();
        }
    }

    public MyHashMap(int initialSize, double loadFactor) {
        map = new ArrayList[initialSize];
        this.loadFactor = loadFactor;
        arraySize = initialSize;
        for (int i=0;i < initialSize; i++){
            map[i] = new ArrayList();
        }
    }



    private class Node{
        private K key;
        private V value;
        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
    public void clear(){
        for (int i=0;i < arraySize; i++) {
            map[i] = new ArrayList();
        }


        keySet = new HashSet<>();

    }

    /** Returns true if this map contains a mapping for the specified key. */
    public boolean containsKey(K key) {
        int index =  Math.abs(key.hashCode() % arraySize);
        ArrayList bucket = map[index];
        Node p;
        for(int i = 0; i < bucket.size(); i++){
            p = (Node) bucket.get(i);
            if( p.key == key){
                return true;
            }
        }
        return false;
    }

    private boolean containsKeyupdate(K key,V value) {
        int index =  Math.abs(key.hashCode() % arraySize);
        ArrayList bucket = map[index];
        Node p;
        for(int i = 0; i < bucket.size(); i++){
            p = (Node) bucket.get(i);
            if( p.key == key ){
                p.value = value;
                return true;
            }
        }
        return false;
    }


    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    public V get(K key) {
        int index =  Math.abs(key.hashCode() % arraySize);
        ArrayList bucket = map[index];
        Node p;
        for(int i = 0; i < bucket.size(); i++){
            p = (Node) bucket.get(i);
            if( p.key == key ){
                return p.value;
            }
        }
        return null;
    }

    /** Returns the number of key-value mappings in this map. */
    public int size() {
        return keySet.size();
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key,
     * the old value is replaced.
     */
    public void put(K key, V value) {
        if (containsKeyupdate(key,value)) {
            return;
        }
        int index =  Math.abs(key.hashCode() % arraySize);
        ArrayList bucket = map[index];
        bucket.add(new Node(key,value));
        keySet.add(key);
        if(bucket.size() / arraySize > 0.75) {
            resize();
        }


    }
    private void resize(){
        arraySize = arraySize * 2;
        ArrayList[] tranfer = new ArrayList[arraySize];
        ArrayList bucket;
        for (int i=0;i < arraySize; i++){
            tranfer[i] = new ArrayList();
        }
        for(int i = 0; i < map.length; i++){
            bucket = map[i];
            for(int j = 0; j < bucket.size(); j++){
                Node P = (Node) bucket.get(j);
                tranfer[Math.abs(P.key.hashCode() % arraySize)].add(bucket.get(j));
            }


        }
    map = tranfer;
    }




    @Override
    public Iterator<K> iterator() {
        return keySet.iterator();
    }

    /** Returns a Set view of the keys contained in this map. */
    public Set<K> keySet() {
        return keySet;

    }

    /**
     * Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    public V remove(K key) {
        throw new UnsupportedOperationException("It be that way sometimes");
    }

    /**
     * Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.
     */
    public V remove(K key, V value) {
        throw new UnsupportedOperationException("It be that way sometimes");
    }


}
