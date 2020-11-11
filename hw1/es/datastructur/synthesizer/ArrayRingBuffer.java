package es.datastructur.synthesizer;

import java.util.Iterator;

//TODO: Make sure to that this class and all of its methods are public
//TODO: Make sure to add the override tag for all overridden methods
//TODO: Make sure to make this class implement BoundedQueue<T>

public class ArrayRingBuffer<T> implements BoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;


    public ArrayRingBuffer(int capacity) {
        rb = (T[]) new Object[capacity];
        first = 0;
        last = 0;
        fillCount = 0;

    }

    @Override
    public int capacity() {
        return rb.length;
    }

    @Override
    public int fillCount() {
        return fillCount;
    }

    @Override
    public void enqueue(T x) {
        if (fillCount == capacity()) {
            throw new RuntimeException("Ring Buffer overflow");
        }
        fillCount += 1;
        if (last + 1 == rb.length) {
            rb[last] = x;
            last = 0;
        } else {
            rb[last] = x;
            last += 1;
        }
    }

    @Override
    public T dequeue() {
        if (fillCount == 0) {
            throw new RuntimeException("Ring Buffer underflow");
        }
        T value = rb[first];
        rb[first] = null;
        if (first + 1 == rb.length) {
            first = 0;
        } else {
            first += 1;

        }
        fillCount -= 1;
        return value;
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T peek() {
        if (fillCount() == 0) {
            throw new RuntimeException("Ring Buffer underflow");
        }
        return rb[first];
    }


    public Iterator<T> iterator() {
        return new ArrayRingIterator();
    }

    private class ArrayRingIterator implements Iterator<T> {
        private int Numbers;
        private int Index;


        public ArrayRingIterator() {
            Numbers = fillCount;
            Index = first;
        }

        public boolean hasNext() {
            return Numbers != 0;
        }

        public T next() {
            T returnItem = rb[Index];
            Numbers -= 1;
            if (Index == rb.length - 1) {
                Index = 0;
            } else {
                Index += 1;
            }
            return returnItem;
        }
    }

    @Override
    public boolean equals(Object obj) {
        ArrayRingBuffer<T> o = (ArrayRingBuffer<T>) obj;
        if (this.getClass() != o.getClass()) {
            return false;
        }
        if (capacity() != o.capacity() || fillCount() != o.fillCount()) {
            return false;
        }
        Iterator<T> seer = o.iterator();

        for (Object i : this) {
            if (seer.hasNext()) {
                if (i != seer.next()) {
                    return false;
                }
            } else
                return false;
        }

        return true;
    }

}


