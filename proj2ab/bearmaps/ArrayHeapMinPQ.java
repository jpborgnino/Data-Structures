package bearmaps;

import java.util.TreeMap;
import java.util.NoSuchElementException;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {
    private T[] items = (T[]) new Object[4];
    private double[] priorities = new double[4];
    private TreeMap indexT = new TreeMap();
    private int size = 0;


    @Override
    public void add(T item, double priority) {
        if (indexT.containsKey(item)) {
            throw new IllegalArgumentException("Item Already Added");
        }
        if (size + 1 >= items.length) {
            expand();
        }
        size += 1;
        items[size] = item;
        priorities[size] = priority;
        indexT.put(item, size);
        swim(size);


    }

    /* Returns true if the PQ contains the given item. */

    private void expand() {
        int newSize = (items.length * 2);
        T[] itemsTemp = (T[]) new Object[newSize];
        double[] prioritiesTemp = new double[newSize];
        System.arraycopy(items, 0, itemsTemp, 0, size + 1);
        System.arraycopy(priorities, 0, prioritiesTemp, 0, size + 1);
        items = itemsTemp;
        priorities = prioritiesTemp;
        resize();

    }


    private void resize() {
        double usage = (double) size / items.length;
        if (usage < 0.25) {
            int newSize = (int) (size / 0.5);
            T[] itemsTemp = (T[]) new Object[newSize];
            double[] prioritiesTemp = new double[newSize];
            System.arraycopy(items, 0, itemsTemp, 0, size + 1);
            System.arraycopy(priorities, 0, prioritiesTemp, 0, size + 1);
            items = itemsTemp;
            priorities = prioritiesTemp;

        }
    }


    @Override
    public boolean contains(T item) {
        return indexT.containsKey(item);
    }

    /* Returns the minimum item. Throws NoSuchElementException if the PQ is empty. */
    @Override
    public T getSmallest() {
        if (size() == 0) {
            throw new NoSuchElementException("dsads");
        }
        return items[1];
    }

    /* Removes and returns the minimum item. Throws NoSuchElementException if the PQ is empty. */
    public T removeSmallest() {
        if (size == 0) {
            throw new NoSuchElementException("dsads");
        } else if (size == 1) {
            T temp = items[size];
            size -= 1;
            indexT.remove(temp);
            return temp;
        } else {
            swap(1, size);
            T temp = items[size];
            items[size] = null;
            size -= 1;
            indexT.remove(temp);
            resize();
            swim(1);
            return temp;

        }
    }

    /* Returns the number of items in the PQ. */
    @Override
    public int size() {
        return size;
    }

    /* Changes the priority of the given item. Throws NoSuchElementException if the item
     * doesn't exist. */
    @Override
    public void changePriority(T item, double priority) {
        if (!contains(item)) {
            throw new NoSuchElementException("Item Does Not Exist");
        }
        int index = (int) indexT.get(item);
        priorities[index] = priority;
        swim(index);

    }

    private int parent(int i) {
        return i / 2;
    }

    private int leftchild(int i) {
        return i * 2;
    }

    private int rightchild(int i) {
        return i * 2 + 1;
    }

    private void swap(int i, int j) {
        T tempItemI = items[i];
        T tempItemJ = items[j];
        double tempPriorityI = priorities[i];
        double tempPriorityJ = (double) priorities[j];
        indexT.replace(tempItemI, i, j);
        indexT.replace(tempItemJ, j, i);
        items[i] = tempItemJ;
        items[j] = tempItemI;
        priorities[i] = tempPriorityJ;
        priorities[j] = tempPriorityI;
    }

    private void swim(int i) {
        double currentPriority = priorities[i];
        int indexParent = parent(i);
        if (i != 1) {
            double parentsPriority = priorities[indexParent];
            if (currentPriority < parentsPriority && i != 1) {
                swap(i, indexParent);
                swim(indexParent);
                return;
            }
        }
        int indexLeftchild = leftchild(i);
        if (indexLeftchild <= size) {
            int indexRightchild = rightchild(i);
            double lefthildPriority = priorities[indexLeftchild];
            if (indexRightchild <= size) {
                double rightchildPriority = priorities[indexRightchild];
                if (lefthildPriority < rightchildPriority) {
                    if (lefthildPriority < currentPriority) {
                        swap(i, indexLeftchild);
                        swim(indexLeftchild);
                        return;
                    }
                } else if (rightchildPriority < currentPriority) {
                    swap(i, indexRightchild);
                    swim(indexRightchild);
                    return;
                }
            } else {
                if (lefthildPriority < currentPriority) {
                    swap(i, indexLeftchild);
                    swim(indexLeftchild);
                    return;
                }


            }

        }
    }
}









