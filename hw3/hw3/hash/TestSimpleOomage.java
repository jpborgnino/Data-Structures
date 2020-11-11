package hw3.hash;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;


public class TestSimpleOomage {

    @Test
    public void testHashCodeDeterministic() {
        SimpleOomage so = SimpleOomage.randomSimpleOomage();
        int hashCode = so.hashCode();
        for (int i = 0; i < 100; i += 1) {
            assertEquals(hashCode, so.hashCode());
        }
    }

    @Test
    public void testHashCodePerfect() {
        SimpleOomage ooA = new SimpleOomage(5, 15, 20);
        SimpleOomage ooA2 = new SimpleOomage(5, 10, 25);
        assertFalse(ooA.hashCode() == ooA2.hashCode());
        SimpleOomage ooB = new SimpleOomage(5, 10, 20);
        SimpleOomage ooB2 = new SimpleOomage(5, 10, 20);
        assertFalse(ooB.hashCode() != ooB2.hashCode());
        Set setA = new HashSet();
        for (int i = 0; i <= 250; i += 5) {
            for (int j = 0; j <= 250; j += 5) {
                for (int e = 0; e <= 250; e += 5) {
                    ooB = new SimpleOomage(i, j, e);
                    if (setA.contains(ooB.hashCode())) {
                        System.out.println("i = " + i + " j=" + j + " e=" + e);
                        return;
                    }
                    setA.add(ooB.hashCode());

                }
            }


        }
    }


    @Test
    public void testEquals() {
        SimpleOomage ooA = new SimpleOomage(5, 10, 20);
        SimpleOomage ooA2 = new SimpleOomage(5, 10, 20);
        SimpleOomage ooB = new SimpleOomage(50, 50, 50);
        assertEquals(ooA, ooA2);
        assertNotEquals(ooA, ooB);
        assertNotEquals(ooA2, ooB);
        assertNotEquals(ooA, "ketchup");
    }


    @Test
    public void testHashCodeAndEqualsConsistency() {
        SimpleOomage ooA = new SimpleOomage(5, 10, 20);
        SimpleOomage ooA2 = new SimpleOomage(5, 10, 20);
        HashSet<SimpleOomage> hashSet = new HashSet<>();
        hashSet.add(ooA);
        assertTrue(hashSet.contains(ooA2));
    }


    @Test
    public void testRandomOomagesHashCodeSpread() {
        List<Oomage> oomages = new ArrayList<>();
        int N = 10000;

        for (int i = 0; i < N; i += 1) {
            oomages.add(SimpleOomage.randomSimpleOomage());
        }

        assertTrue(OomageTestUtility.haveNiceHashCodeSpread(oomages, 10));
    }

    /**
     * Calls tests for SimpleOomage.
     */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestSimpleOomage.class);
    }
}