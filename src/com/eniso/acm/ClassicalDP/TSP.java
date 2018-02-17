package com.eniso.acm.ClassicalDP;

import java.io.PrintWriter;
import java.util.Arrays;

public class TSP {

    public static int n;
    private static int[][] dist;

    private static int tsp(int i, int mask) {
        if (mask == (1 << n) - 1) {
            return dist[i][0];
        }
        int min = Integer.MAX_VALUE;
        for (int j = 0; j < n; j++) {
            if (i != j && (mask & (1 << j)) == 0) {
                min = Integer.min(min, dist[i][j]
                        + tsp(j, mask | (1 << j)));
            }
        }
        return min;
    }

    public static void main(String[] args) {
        PrintWriter out = new PrintWriter(System.out);
        n = 4;
        dist = new int[n][n];
        dist[0] = Arrays.copyOf(new int[]{0, 20, 42, 35}, n);
        dist[1] = Arrays.copyOf(new int[]{20, 0, 30, 34}, n);
        dist[2] = Arrays.copyOf(new int[]{42, 30, 0, 12}, n);
        dist[3] = Arrays.copyOf(new int[]{35, 34, 12, 0}, n);
        out.println(tsp(0, 1));
        out.close();
    }
}
