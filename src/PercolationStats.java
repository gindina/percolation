import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by К on 29.02.2016.
 */
public class PercolationStats {
    private int N;
    private int T;
    private int[] x;
    private boolean haveStats = false;

    public PercolationStats(int N, int T) throws IllegalArgumentException { // perform T independent experiments on an N-by-N grid
        if (N <= 0 || T <= 0) throw new IllegalArgumentException();
        this.N = N;
        this.T = T;
        this.x = new int[T];
        for (int i = 0;i < T;  i++) {
            x[i] = percMonteCarloSimulation(N);
        }
        haveStats = true;
    }

    public double mean() {             // sample mean of percolation threshold
        if (haveStats = true) {
//            double sumXi = 0;
//            for (int i = 0; i < T; i++) {
//                sumXi += x[i];
//            }
//            return sumXi / T;
            return StdStats.mean(x);
        }
        return 0;
    }
    public double stddev() { // sample standard deviation of percolation threshold
        if (haveStats = true) {
//            double sumXi = 0;
//            double m = mean();
//            for (int i = 0; i < T; i++) {
//                sumXi += (x[i]-m)*(x[i]-m);
//            }
//            return  sumXi / (T-1);
            return StdStats.stddev(x);
        }
        return 0;
    }
    public double confidenceLo() {        // low  endpoint of 95% confidence interval
        if (haveStats){
            double lo = (1.96*stddev()/Math.sqrt(T) - mean())*(-1);
            return lo;
        }
        return 0;
    }
    public double confidenceHi() {         // high endpoint of 95% confidence interval
        if (haveStats){
            double hi = 1.96*stddev()/Math.sqrt(T) + mean();
            return hi;
        }
        return 0;
    }
//
//    public static void main(String[] args)    // test client (described below)

    public int percMonteCarloSimulation (int N){
        if (N<=0) throw new IllegalArgumentException();
        Percolation percolation = new Percolation(N);
        while (!percolation.percolates()) {
            percolation.open(StdRandom.uniform(1, N + 1), StdRandom.uniform(1, N + 1));
        }
        return percolation.openSitesCount();
    }

    public static void main(String[] args) {
        String userInput = ""; // Line read from standard in

        InputStreamReader converter = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(converter);
        String username = "";

        try {
            while (!(userInput.equals("0"))) {
                System.out.println("1. Создать пользователя");
                System.out.println("2. Найти пользователя");
                System.out.println("3. Удалить пользователя");
                System.out.println("0. Выход");
            }
        } catch (Exception e) {
        }
    }
}
