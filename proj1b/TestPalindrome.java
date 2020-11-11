import org.junit.Test;

import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();
    static CharacterComparator cc = new OffByOne();
    static CharacterComparator ee = new OffByN(2);

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testisPalindrome() {
        assertFalse(palindrome.isPalindrome("Ma dam"));
        assertTrue(palindrome.isPalindrome(""));
        assertFalse(palindrome.isPalindrome("we"));
        assertFalse(palindrome.isPalindrome("fhsdkjfhskdfjsdhfksjdhfsdkjfsdhfkdjshs"));
        assertTrue(palindrome.isPalindrome("saGas"));
        assertTrue(palindrome.isPalindrome("TOOT"));
        assertFalse(palindrome.isPalindrome("Racecar"));


    }

    @Test
    public void testisPalindrome2() {
        assertTrue(palindrome.isPalindrome("", cc));
        assertTrue(palindrome.isPalindrome("hg", cc));
        assertTrue(palindrome.isPalindrome("ThongS", cc));
        assertFalse(palindrome.isPalindrome("eEEe", cc));
        assertFalse(palindrome.isPalindrome("people", cc));

    }

    @Test
    public void testisPalindrome4() {
        assertTrue(palindrome.isPalindrome("%", ee));
        assertTrue(palindrome.isPalindrome("BlonD", ee));
        assertTrue(palindrome.isPalindrome("o", ee));


    }
}
