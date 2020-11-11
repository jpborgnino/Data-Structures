package es.datastructur.synthesizer;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {

        BoundedQueue<Integer> arb = new ArrayRingBuffer<>(8);
        arb.enqueue(1);
        arb.enqueue(2);
        arb.enqueue(3);
        arb.enqueue(4);
        arb.enqueue(5);
        arb.enqueue(6);
        arb.enqueue(7);
        arb.enqueue(3);


        BoundedQueue<Integer> arc = new ArrayRingBuffer<>(8);
        arc.enqueue(1);
        arc.enqueue(2);
        arc.enqueue(3);
        arc.dequeue();
        arc.enqueue(4);
        arc.dequeue();
        arc.enqueue(5);
        arc.enqueue(6);
        arc.dequeue();
        arc.enqueue(7);
        arc.enqueue(3);
        assertTrue(arb.equals(arc));




    }

    }



