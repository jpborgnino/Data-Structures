package bearmaps;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertTrue;


public class KDTreeTest {

    @Test
    public void test1() {
        List<Point> l = new ArrayList<Point>();
        l.add(new Point(0, 0));
        l.add(new Point(0, 0));
        l.add(new Point(0, 0));
        l.add(new Point(0, 0));
        KDTree a = new KDTree(l);
        NaivePointSet b = new NaivePointSet(l);
        Point mine = a.nearest(0, 0);
        Point actual = b.nearest(0, 0);
        assertTrue(mine.equals(actual));


    }

    @Test
    public void test2() {
        List a = new ArrayList();
        for (int i = 0; i < 100000; i++) {
            double x = Math.pow(-1, StdRandom.uniform(1, 3)) * StdRandom.uniform() * 2;
            double y = Math.pow(-1, StdRandom.uniform(1, 3)) * StdRandom.uniform() * 2;
            a.add(new Point(x, y));
        }
        NaivePointSet o = new NaivePointSet(a);
        Stopwatch ww = new Stopwatch();
        for (int i = 0; i < 10000; i++) {
            double x = Math.pow(-1, StdRandom.uniform(1, 3)) * StdRandom.uniform() * 2;
            double y = Math.pow(-1, StdRandom.uniform(1, 3)) * StdRandom.uniform() * 2;
            Point oo = o.nearest(x, y);

        }
        System.out.println(ww.elapsedTime());
        KDTree p = new KDTree(a);
        Stopwatch tt = new Stopwatch();

        for (int i = 0; i < 10000; i++) {
            double x = Math.pow(-1, StdRandom.uniform(1, 3)) * StdRandom.uniform() * 2;
            double y = Math.pow(-1, StdRandom.uniform(1, 3)) * StdRandom.uniform() * 2;
            Point pp = p.nearest(x, y);

        }
        System.out.println(tt.elapsedTime());


    }

    @Test
    public void test3() {
        ArrayList a = new ArrayList();
        for (int i = 0; i < 100000; i++) {
            double x = Math.pow(-1, StdRandom.uniform(1, 3)) * StdRandom.uniform() * 10;
            double y = Math.pow(-1, StdRandom.uniform(1, 3)) * StdRandom.uniform() * 10;
            a.add(new Point(x, y));
        }
        NaivePointSet o = new NaivePointSet(a);
        KDTree p = new KDTree(a);
        Stopwatch ww = new Stopwatch();
        for (int i = 0; i < 10000; i++) {
            double x = Math.pow(-1, StdRandom.uniform(1, 3)) * StdRandom.uniform() * 10;
            double y = Math.pow(-1, StdRandom.uniform(1, 3)) * StdRandom.uniform() * 10;
            Point pp = p.nearest(x, y);
            Point oo = o.nearest(x, y);
            assertTrue(oo.equals(pp));


        }
    }
    @Test
    public void test5(){
        HashMap a = new HashMap();
        a.put(2,5);
        a.put("h","hh");

    }


}
