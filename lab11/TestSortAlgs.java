import edu.princeton.cs.algs4.Queue;

import org.junit.Test;

public class TestSortAlgs {

    @Test
    public void testQuickSort() {
        Queue i = new Queue();
        i.enqueue(7);
        i.enqueue(7);
        i.enqueue(2);
        i.enqueue(8);
        i.enqueue(5);
        i.enqueue(8);
        i.enqueue(3);
        i.enqueue(1);
        i.enqueue(6);
        i.enqueue(7);
        Queue a = QuickSort.quickSort(i);
        System.out.println(a.size());
    }

    @Test
    public void testMergeSort() {

    }

    /**
     * Returns whether a Queue is sorted or not.
     *
     * @param items  A Queue of items
     * @return       true/false - whether "items" is sorted
     */
    private <Item extends Comparable> boolean isSorted(Queue<Item> items) {
        if (items.size() <= 1) {
            return true;
        }
        Item curr = items.dequeue();
        Item prev = curr;
        while (!items.isEmpty()) {
            prev = curr;
            curr = items.dequeue();
            if (curr.compareTo(prev) < 0) {
                return false;
            }
        }
        return true;
    }
}
