public class OffByN implements CharacterComparator {
    private int value;

    public OffByN(int N) {
        value = N;

    }

    @Override
    public boolean equalChars(char x, char y) {
        int diff = java.lang.Math.abs(x - y);
        return (diff == value);
    }
    public boolean equalChars(char x) {
        return true;
    }

}




