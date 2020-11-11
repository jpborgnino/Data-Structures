package byow.Core;

import byow.InputDemo.InputSource;
import byow.InputDemo.KeyboardInputSource;
import edu.princeton.cs.introcs.StdDraw;

public class java {
    public static void main(String[] args) {
        int totalCharacters = 0;
        StdDraw.clear();
        InputSource inputSource = new KeyboardInputSource();

        while (inputSource.possibleNextInput()) {
            totalCharacters += 1;
            char c = inputSource.getNextKey();
            }
        }
                }




