package byow.lab12;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import java.util.Random;

/**
 *  Draws a world that is mostly empty except for a small region.
 */
public class BoringWorldDemo {
    private static final int WIDTH = 60;
    private static final int HEIGHT = 30;

    public static void main(String[] args) {
        // initialize the tile rendering engine with a window of size WIDTH x HEIGHT
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        // initialize tiles
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }

        // fills in a block 14 tiles wide by 4 tiles tall
        for (int x = 20; x < 35; x += 1) {
            for (int y = 5; y < 10; y += 1) {
                world[x][y] = Tileset.WATER;
            }
        }

        // draws the world to the screen
        world[2][2]=Tileset.FLOWER;
        ter.renderFrame(world);


        Random r = new Random(82731);
        System.out.println(r.nextInt(2));
        System.out.println(r.nextInt(2));
        System.out.println(r.nextInt(2));
        System.out.println(r.nextInt(2));
        r = new Random(82731);
        System.out.println(r.nextInt(2));
        System.out.println(r.nextInt(2));
        System.out.println(r.nextInt(2));
        System.out.println(r.nextInt(2));
    }


}