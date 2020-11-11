package creatures;

import huglife.Creature;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;

import java.awt.Color;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.Map;

/**
 * An implementation of a motile pacifist photosynthesizer.
 *
 * @author Josh Hug
 */
public class Plip extends Creature {

    /**
     * red color.
     */
    private int r;
    /**
     * green color.
     */
    private int g;
    /**
     * blue color.
     */
    private int b;

    /**
     * creates plip with energy equal to E.
     */
    public Plip(double e) {
        super("plip");
        r = 0;
        g = 0;
        b = 0;
        energy = e;
    }

    /**
     * creates a plip with energy equal to 1.
     */
    public Plip() {
        this(1);
    }

    /**
     * Should return a color with red = 99, blue = 76, and green that varies
     * linearly based on the energy of the Plip. If the plip has zero energy,
     * it should have a green value of 63. If it has max energy, it should
     * have a green value of 255. The green value should vary with energy
     * linearly in between these two extremes. It's not absolutely vital
     * that you get this exactly correct.
     */
    public Color color() {
        g = (int) (96 * energy + 63);
        r = 99;
        b = 76;

        return color(r, g, b);
    }

    /**
     * Do nothing with C, Plips are pacifists.
     */
    public void attack(Creature c) {
        // do nothing.
    }

    /**
     * Plips should lose 0.15 units of energy when moving. If you want to
     * to avoid the magic number warning, you'll need to make a
     * private static final variable. This is not required for this lab.
     */
    public void move() {
        energy = energy - 0.2;
        if (energy < 0) { energy = 0; }
    }


    /**
     * Plips gain 0.2 energy when staying due to photosynthesis.
     */
    public void stay() {
        energy = energy + 0.40;
        if (energy>2) { energy = 2; }
    }

    /**
     * Plips and their offspring each get 50% of the energy, with none
     * lost to the process. Now that's efficiency! Returns a baby
     * Plip.
     */
    public Plip replicate() {
        this.energy = this.energy / 2;
        Plip Baby = new Plip(energy);
        return Baby;
    }

    /**
     * Plips take exactly the following actions based on NEIGHBORS:
     * 1. If no empty adjacent spaces, STAY.
     * 2. Otherwise, if energy >= 1, REPLICATE towards an empty direction
     * chosen at random.
     * 3. Otherwise, if any Cloruses, MOVE with 50% probability,
     * towards an empty direction chosen at random.
     * 4. Otherwise, if nothing else, STAY
     * <p>
     * Returns an object of type Action. See Action.java for the
     * scoop on how Actions work. See SampleCreature.chooseAction()
     * for an example to follow.
     */
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        // Rule 1
        Deque<Direction> Checker = new ArrayDeque<>();
        Checker.addLast(Direction.TOP);
        Checker.addLast(Direction.BOTTOM);
        Checker.addLast(Direction.LEFT);
        Checker.addLast(Direction.RIGHT);
        int[] IndexEmpty = new int[4];
        boolean AllOccupied = true;
        boolean Clorus = false;
        int index = 0;
        int counter = 0;
        for (Iterator u = Checker.iterator(); u.hasNext(); ) {
            String Occupant = neighbors.get(u.next()).name();
            if (Occupant == "empty") {
                AllOccupied = false;
                IndexEmpty[counter] = index;
                index += 1;
                counter += 1;
            } else {
                if (Occupant == "clorus") {
                    Clorus = true;
                }
                index += 1;
            }
        }
        int[] IndexEmptyDirection = new int[counter];
        System.arraycopy(IndexEmpty, 0, IndexEmptyDirection, 0, counter);


        if (AllOccupied) {
            return new Action(Action.ActionType.STAY);
        }

        if (energy >= 1) {
            int RandomIndex = (int) (Math.random() * IndexEmptyDirection.length);
            int Position = IndexEmptyDirection[RandomIndex];
            for (Iterator i = Checker.iterator(); i.hasNext(); ) {
                Direction D = (Direction) i.next();
                if (Position == 0) {
                    return new Action(Action.ActionType.REPLICATE, D);
                }
                Position -= 1;

            }
        }

        if (Clorus) {
            int random = (int) (Math.random() * 2);
            if (random == 1) {
                return new Action(Action.ActionType.STAY);
            } else {
                int RandomIndex = (int) (Math.random() * IndexEmptyDirection.length);
                int Position = IndexEmptyDirection[RandomIndex];
                for (Iterator t = Checker.iterator(); t.hasNext(); ) {
                    Direction D = (Direction) t.next();
                    if (Position == 0) {
                        return new Action(Action.ActionType.MOVE, D);
                    }
                    Position -= 1;

                }
            }


        }
    return new Action(Action.ActionType.STAY);
    }
}

