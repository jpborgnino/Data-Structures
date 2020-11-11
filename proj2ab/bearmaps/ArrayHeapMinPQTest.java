package bearmaps;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;


public class ArrayHeapMinPQTest {


    @Test
    public void testArray() {
        ArrayHeapMinPQ a = new ArrayHeapMinPQ();
        List numbers = new ArrayList<>();
        List strings = new ArrayList();
        List mstrings = new ArrayList();
        for (int i = 0; i < 1000000; i++) {
            numbers.add((double) i);
            strings.add(Double.toString(i));
        }

        Collections.shuffle(numbers);
        for (int i = 0; i < 1000000; i++) {
            mstrings.add(numbers.get(i).toString());
        }
        double time = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            a.add(mstrings.get(i), (double) numbers.get(i));
        }
        for (int i = 0; i < 100000; i++) {
            String m = (String) a.removeSmallest();
            String b = (String) strings.get(i);
            if (i == 95) {
                int p = 2;
            }
            assertEquals(m, b);
        }
        double finaltime = System.currentTimeMillis();
        double delta = finaltime - time;
        System.out.println(delta / 100000);


    }

    @Test
    public void testArray2() {
        ArrayHeapMinPQ a = new ArrayHeapMinPQ();
        a.add("0", 0);
        a.add("1", 1);
        a.add("2", 2);
        a.add("3", 3);
        a.add("02", 0);
        a.add("1,2", 1);
        a.add("2,2", 2);
        a.add("3,3", 3);
        assertEquals(a.removeSmallest(), "0");

    }

    @Test
    public void testArray3() {
        ArrayHeapMinPQ a = new ArrayHeapMinPQ();
        double[] w = new double[200000];
        for (int i = 0; i < 200000; i++) {
            double ran = StdRandom.uniform() * 200000;
            w[i] = ran;
            a.add(ran, ran);
        }
        Arrays.sort(w);
        double time = System.currentTimeMillis();
        for (int i = 0; i < 200000; i++) {
            assertTrue(a.contains(w[i]));
            double b = (double) a.removeSmallest();
            assertFalse(a.contains(w[i]));
            assertTrue(b == w[i]);


        }
        double finaltime = System.currentTimeMillis();
        double delta = finaltime - time;
        System.out.println(delta / 100000);


    }

    @Test
    public void testArray4() {
        ArrayHeapMinPQ a = new ArrayHeapMinPQ();
        double[] w = new double[200000];
        for (int i = 0; i < 200000; i++) {
            double ran = StdRandom.uniform() * 2000;
            w[i] = ran;
            a.add(ran, ran);
        }
        Arrays.sort(w);
        double time = System.currentTimeMillis();
        for (int i = 0; i < 199999; i++) {
            String s = Integer.toString(-i);
            a.changePriority(w[199999 - i], -1 * w[199999 - i]);
            assertTrue((double) a.removeSmallest() == w[199999 - i]);
            assertTrue(a.size() == 199999 - i);


        }
        double finaltime = System.currentTimeMillis();
        double delta = finaltime - time;
        System.out.println(delta / 100000);


    }

    @Test
    public void testArray5() {
        ArrayHeapMinPQ a = new ArrayHeapMinPQ();
        a.add(2, 3);
        a.add(1, 3);
        a.add(3, 3);
        a.add(4, 3);
        a.add(5, 2);
        a.add(0, 3);
        a.add(-2, 3);
        a.add(-4, 3);
        a.size();
        a.removeSmallest();
        a.removeSmallest();
        a.removeSmallest();
        a.removeSmallest();
        a.removeSmallest();
        a.removeSmallest();
        a.removeSmallest();
        a.removeSmallest();
        assertTrue(a.size() == 0);
        a.add(-22222, 33);
        a.add(1, 3);
        a.add(3, 3);
        a.add(4, 3);
        a.add(5, 2);
        a.add(0, 3);
        a.add(-2, 3);
        a.add(-4, 3);


    }

    @Test
    public void testArray6() {
        ArrayHeapMinPQ a = new ArrayHeapMinPQ();
        a.add(1, -99);
        a.add(2, 2);
        a.add(3, 3);
        a.add(4, 4);
        a.add(5, 5);
        a.changePriority(1, 99);
    }


}

