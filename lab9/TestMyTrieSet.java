import static org.junit.Assert.*;
import org.junit.Test;
import java.util.List;

/**
 * Created by Jenny Huang on 3/12/19.
 */
public class TestMyTrieSet {

    // assumes add/contains work
    @Test
    public void mytest(){
            String s = "HiHhi13121231".replaceAll("\\d","").toLowerCase();
            s = s.toLowerCase();
            int x =2;
        MyTrieSet a = new MyTrieSet();
        a.add("   ");
        a.add("AB");
        a.add("ABC");
        a.add("B");
        a.add("BAAB");
        a.contains("BAAB");

    }



    @Test
    public void sanityClearTest() {
        MyTrieSet t = new MyTrieSet();
        for (int i = 0; i < 10000; i++) {
            t.add("hi" + i);
            //make sure put is working via contains
            assertTrue(t.contains("hi" + i));
        }
        List prefix = t.keysWithPrefix("hi5");
                t.clear();

        for (int i = 0; i < 455; i++) {
            assertFalse(t.contains("hi" + i));
        }

    }

    // assumes add works
    @Test
    public void sanityContainsTest() {
        MyTrieSet t = new MyTrieSet();
        assertFalse(t.contains("waterYouDoingHere"));
        t.add("Water YouDoingHere");
        t.add("WaterouDoingHere");
        t.add("Wates oouDoingHere");
        t.add("Water You DoingHere");
        assertTrue(t.contains("Water YouDoingHere"));
        List a = t.keysWithPrefix("W");
    }

    // assumes add works
    @Test
    public void sanityPrefixTest() {
        String[] saStrings = new String[]{"same", "sam", "sad", "sap"};
        String[] otherStrings = new String[]{"a", "awls", "hello"};

        MyTrieSet t = new MyTrieSet();
        for (String s: saStrings) {
            t.add(s);
        }
        for (String s: otherStrings) {
            t.add(s);
        }

        List<String> keys = t.keysWithPrefix("sa");
        for (String s: saStrings) {
            assertTrue(keys.contains(s));
        }
        for (String s: otherStrings) {
            assertFalse(keys.contains(s));
        }
    }

    public static void main(String[] args) {
        jh61b.junit.TestRunner.runTests(TestMyTrieSet.class);
    }



}
