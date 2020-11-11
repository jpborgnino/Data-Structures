
public class ArrayDeque<T> {
    private T[] items;
    private int first;
    private int size;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        first = items.length / 2;
        size = 0;
    }

    public ArrayDeque(ArrayDeque other) {
        T[] array = (T[]) new Object[other.items.length];
        int index = 0;
        while (index != other.items.length) {
            array[index] = (T) other.items[index];
            index += 1;
        }
        this.items = array;
        this.first = other.first;
        this.size = other.size;

    }


    private void reshape() {
        if (size == items.length) {
            T[] a = (T[]) new Object[items.length * 3];
            System.arraycopy(items, first, a, a.length / 3, size);
            first = a.length / 3;
            items = a;
        } else {
            T[] a = (T[]) new Object[items.length * 2];
            System.arraycopy(items, first, a, a.length / 2, size);
            first = a.length / 2;
            items = a;
        }
    }

    private void reshapeWaste() {
        double b = items.length;
        double c = size;
        double p = c / b;
        if ((p <= 0.27) && (items.length >= 16)) {
            /* b = items.length;
            c = size;
            p = c / b; */
            double s = size / 0.27;
            int h = (int) Math.ceil((items.length) - s);
            if (size == 0) {
                T[] a = (T[]) new Object[14];
                System.arraycopy(items, first, a, a.length / 2, size);
                first = a.length / 2;
                items = a;
            } else {
                T[] a = (T[]) new Object[items.length - h];
                System.arraycopy(items, first, a, a.length / 2, size);
                first = a.length / 2;
                items = a;
            }
        }

    }


    public void addFirst(T item) {
        if (first == 0) {
            reshape();
        }
        if (items[first] == null) {
            items[first] = item;
            size += 1;
        } else {
            items[first - 1] = item;
            size += 1;
            first -= 1;
        }
        double b = items.length;
        double c = size;
        double a = c / b;
        if (((a <= 0.27) && (items.length >= 16))) {
            reshapeWaste();
        }
    }


    public void addLast(T item) {
        if (size + first == items.length) {
            reshape();
        }
        items[first + size] = item;
        size += 1;
        double b = items.length;
        double c = size;
        double a = c / b;
        if ((a <= 0.27) && (items.length >= 16)) {
            reshapeWaste();
        }

    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        int j = 0;
        while (j != size) {
            System.out.println(items[first + j]);
            j += 1;
        }
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        } else {
            T value = (T) items[first];
            items[first] = null;
            if (first == items.length - 1) {
                reshape();
            }
            first += 1;
            size -= 1;
            double b = items.length;
            double c = size;
            double a = c / b;
            if ((a <= 0.27) && (items.length >= 16)) {
                reshapeWaste();
            }
            return value;
        }


    }


    public T removeLast() {
        if (size == 0) {
            return null;
        } else {
            T value = items[first + size - 1];
            items[first + size - 1] = null;
            size -= 1;
            double b = items.length;
            double c = size;
            double a = c / b;
            if ((a <= 0.27) && (items.length >= 16)) {
                reshapeWaste();
            }
            return value;
        }
    }

    public T get(int index) {
        return items[first + index];

    }

}






