public class LinkedListDeque<T>  {
    private class ListMaker {
        private T item;
        private ListMaker next;
        private ListMaker prev;


        private ListMaker(ListMaker e, T i, ListMaker j) {
            prev = e;
            item = i;
            next = j;


        }
    }

    private ListMaker sentinel;

    private int size;

    public LinkedListDeque() {
        sentinel = new ListMaker(null, null, null);
        size = 0;

    }

    public LinkedListDeque(LinkedListDeque other) {
        this.size = other.size();
        this.sentinel = new ListMaker(null, null, null);
        int i = other.size();
        LinkedListDeque a = new LinkedListDeque();

        ListMaker b = other.sentinel.next;
        while (i != 0) {
            a.addLast(b.item);
            b = b.next;
            i -= 1;
        }
        this.sentinel = a.sentinel;
        this.size = a.size;

    }


    public void addFirst(T x) {
        sentinel.next = new ListMaker(sentinel, x, sentinel.next);
        if (this.size == 0) {
            sentinel.next = new ListMaker(sentinel, x, sentinel);
            sentinel.prev = sentinel.next;
            size += 1;
        } else {
            sentinel.next.next.prev = sentinel.next;
            size += 1;
        }
    }

    public void addLast(T x) {
        if (this.size == 0) {
            addFirst(x);
        } else {

            sentinel.prev.next = new ListMaker(sentinel.prev, x, sentinel);
            sentinel.prev = sentinel.prev.next;
            size += 1;
        }
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        int i = this.size;
        ListMaker a = sentinel.next;
        while (i != 0) {
            System.out.println(a.item);
            size -= 1;
            a = a.next;
        }
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        } else {
            T a = sentinel.next.item;
            sentinel.next = sentinel.next.next;
            sentinel.next.prev = sentinel;
            size -= 1;
            return a;

        }
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        } else {
            T a = sentinel.prev.item;
            sentinel.prev = sentinel.prev.prev;
            sentinel.prev.next = sentinel;
            size -= 1;
            return a;
        }
    }

    public T get(int index) {
        if ((index > size - 1) || (index < 0)) {
            return null;
        } else {
            ListMaker b = sentinel.next;
            int i = index;
            while (i != 0) {
                b = b.next;
                i -= 1;
            }
            return b.item;

        }
    }


    public T getRecursive(int index) {

        if (this.size == 0) {
            return null;
        } else if (index == 0) {
            return this.sentinel.next.item;
        }
        LinkedListDeque a = this.duplicate(this).removeF();
        return (T) a.getRecursive(index - 1);
    }


    private LinkedListDeque duplicate(LinkedListDeque other) {
        int i = other.size();
        LinkedListDeque a = new LinkedListDeque();
        ListMaker b = other.sentinel.next;
        while (i != 0) {
            a.addLast(b.item);
            b = b.next;
            i -= 1;
        }
        return a;
    }

    private LinkedListDeque removeF() {
        if (sentinel.next == null) {
            return this;
        } else {
            T a = sentinel.next.item;
            sentinel.next = sentinel.next.next;
            sentinel.next.prev = sentinel;
            size -= 1;
            return this;

        }
    }
}

     /*public static void main(String[] args){
        LinkedListDeque a = new LinkedListDeque();
        a.addLast(2);
        a.addLast(3);
        a.addFirst(2);
        a.addLast(3);
        new LinkedListDeque(a);

    } */






