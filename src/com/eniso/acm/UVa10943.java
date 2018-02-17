package com.eniso.acm;

import java.io.PrintWriter;
import java.util.Arrays;

public class UVa10943 {

    public static long[][] memo = new long[101][101];

    public static long nbrWays(int n, int k) {
        if (k == 1) {
            return 1L;
        }
        if (memo[n][k] != -1) {
            return memo[n][k];
        }
        long s = 0;
        for (int i = 0; i < n + 1; i++) {
            s += nbrWays(n - i, k - 1);
        }
        return memo[n][k] = s;
    }

    public static void main(String[] args) {
        PrintWriter out = new PrintWriter(System.out);
        for (int i = 0; i < 101; i++) {
            Arrays.fill(memo[i], -1);
        }
        out.println(nbrWays(50, 3));
        out.close();
    }
}
