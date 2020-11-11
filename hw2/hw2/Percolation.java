package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
    private int openSpaces; /* Instance variable to keep track of OpenSpaces constant time */
    private boolean[] openSpacesGrid;
    private WeightedQuickUnionUF grid; /* Instance Variable that uses WeightedUnion*/
    private int bottom; /* location of bottom int in Weighted Union Array; */
    private int top; /* location of top int in Weighted Union Array; */
    private int N; /* Size N by N size of grid */
    private WeightedQuickUnionUF checkFull;

    public Percolation(int N) {
        if (N <= 0) {
            throw new java.lang.IllegalArgumentException("Not Valid");
        }

        grid = new WeightedQuickUnionUF(N * N + 2);
        checkFull = new WeightedQuickUnionUF(N * N + 1);
        openSpaces = 0;
        top = N * N;
        bottom = N * N + 1;
        this.N = N;
        openSpacesGrid = new boolean[N * N];
    }

    private int cordinateTrans(int i, int j) {
        if (i > (N - 1) || (i < 0) || (j > N) || (j < 0)) {
            throw new java.lang.IndexOutOfBoundsException("Out Of Bounds");
        }
        return ((i) * N + j);
    }

    public void open(int row, int col) {
        int index = cordinateTrans(row, col);
        int index1 = 1;
        int index2 = 1;
        int index3 = 1;
        int index4 = 1;
        if (openSpacesGrid[index]) {
            return;
        }
        openSpacesGrid[index] = true;
        openSpaces += 1;
        if (row == 0) {
            grid.union(index, top);
            checkFull.union(index, top);
        }
        if (row == N - 1) {
            grid.union(index, bottom);
        }
        if ((row + 1) <= (N - 1)) {
            index1 = cordinateTrans(row + 1, col);
            if (openSpacesGrid[index1]) {
                grid.union(index, index1);
                checkFull.union(index, index1);

            }
        }
        if ((row - 1) >= 0) {
            index2 = cordinateTrans(row - 1, col);
            if (openSpacesGrid[index2]) {
                grid.union(index, index2);
                checkFull.union(index, index2);
            }
        }
        if ((col - 1) >= 0) {
            index3 = cordinateTrans(row, col - 1);
            if (openSpacesGrid[index3]) {
                grid.union(index, index3);
                checkFull.union(index, index3);
            }
        }
        if ((col + 1) <= (N - 1)) {
            index4 = cordinateTrans(row, col + 1);
            if (openSpacesGrid[index4]) {
                grid.union(index, index4);
                checkFull.union(index, index4);
            }
        }
    }


    public boolean isOpen(int row, int col) {
        int index = cordinateTrans(row, col);
        return openSpacesGrid[index];
    }

    public boolean isFull(int row, int col) {
        int index = cordinateTrans(row, col);
        return checkFull.connected(top, index);
    }

    public int numberOfOpenSites() {
        return openSpaces;
    }

    public boolean percolates() {
        return grid.connected(top, bottom);
    }

    public static void main(String[] args) {
        Percolation a = new Percolation(1);
        a.open(0, 0);
        System.out.println(a.percolates());
    }
}


