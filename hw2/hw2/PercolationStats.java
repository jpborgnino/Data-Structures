package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    private int T;
    private int N;
    private PercolationFactory pf;
    private double[] stats;

    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new java.lang.IllegalArgumentException("Bounds Need to be greater than 0");
        }
        this.T = T;
        this.N = N;
        this.pf = pf;
        stats = new double[T];
        int row;
        int col;
        double size = N * N;
        for (int i = 0; i < T; i++) {
            Percolation test = pf.make(N);
            while (!test.percolates()) {
                row = StdRandom.uniform(0, N);
                col = StdRandom.uniform(0, N);
                test.open(row, col);
            }
            stats[i] = test.numberOfOpenSites() / (size);
        }
    }

    public double mean() {
        return StdStats.mean(stats);
    }

    public double stddev() {
        return StdStats.stddev(stats);
    }

    public double confidenceLow() {
        return mean() - ((1.96 * stddev()) / Math.sqrt(T));
    }

    public double confidenceHigh() {
        return mean() + ((1.96 * stddev()) / Math.sqrt(T));
    }


}
