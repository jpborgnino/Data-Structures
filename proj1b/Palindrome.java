public class Palindrome {

    /* Splits word into its components and puts them in a deque */
    public Deque<Character> wordToDeque(String word) {
        String A = word;
        int i = 0;
        Deque P = new ArrayDeque();
        int b = A.length();

        while (i < b) {
            String mychar = Character.toString(A.charAt(i));
            P.addLast(mychar);
            i += 1;
        }
        return P;

    }

    /* returns true if word is a palindrome */
    public boolean isPalindrome(String word) {
        String pal = word;
        boolean val = true;
        if (pal.length() <= 1) {
            return true;
        } else {
            Palindrome c = new Palindrome();
            Deque originalword = c.wordToDeque(pal);
            while ((originalword.size() != 0) && (originalword.size() != 1)) {
                String L = String.valueOf(originalword.removeFirst());
                String R = String.valueOf(originalword.removeLast());
                if (!L.equals(R)) {
                    return false;
                }
            }
        }
        return val;
    }

    /* Test wether a word is a palindrome using the conditions set by cc */
    public boolean isPalindrome(String word, CharacterComparator cc) {
        String pal = word;
        boolean val = true;
        if (word.length() <= 1) {
            return true;
        } else {
            Palindrome c = new Palindrome();
            Deque originalword = c.wordToDeque(pal);
            while ((originalword.size() != 0) && (originalword.size() != 1)) {
                String L = String.valueOf(originalword.removeFirst());
                String R = String.valueOf(originalword.removeLast());
                char char1 = L.charAt(0);
                char char2 = R.charAt(0);

                if (!(cc.equalChars(char1, char2))) {
                    return false;
                }
            }
        }
        return val;
    }


}



