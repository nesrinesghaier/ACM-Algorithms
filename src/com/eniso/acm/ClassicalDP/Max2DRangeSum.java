package com.eniso.acm.ClassicalDP;

import java.io.PrintWriter;
import java.util.Scanner;

public class Max2DRangeSum {

    public static void main(String[] args) {
        PrintWriter out = new PrintWriter(System.out);
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[][] tab = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                tab[i][j] = in.nextInt();
                if (i > 0) {
                    tab[i][j] += tab[i - 1][j];
                }
                if (j > 0) {
                    tab[i][j] += tab[i][j - 1];
                }
                if (j > 0 && i > 0) {
                    tab[i][j] -= tab[i - 1][j - 1];
                }
            }
        }
        int max = -127 * n * n;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = i; k < n; k++) {
                    for (int l = j; l < n; l++) {
                        int s = tab[k][l];
                        if (i > 0) {
                            s -= tab[i - 1][l];
                        }
                        if (j > 0) {
                            s -= tab[k][j - 1];
                        }
                        if (j > 0 && i > 0) {
                            s += tab[i - 1][j - 1];
                        }
                        max = Integer.max(max, s);
                    }
                }
            }
        }
        out.println(max);
        out.close();
    }
}
