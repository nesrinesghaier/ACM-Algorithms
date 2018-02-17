package com.eniso.acm.ClassicalDP;

import java.io.PrintWriter;

public class LIS {

    public static int n;
    private static int[] tab;

    private static int lis(int k) {
        if (k == 0) {
            return 1;
        }
        int max = 0;
        for (int i = 0; i < k; i++) {
            if (tab[i] < tab[k]) {
                max = Integer.max(max, lis(i) + 1);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        PrintWriter out = new PrintWriter(System.out);
        n = 8;
        tab = new int[n];
        tab[0] = -7;
        tab[1] = 10;
        tab[2] = 9;
        tab[3] = 2;
        tab[4] = 3;
        tab[5] = 8;
        tab[6] = 8;
        tab[7] = 1;
        int max = 0;
        for (int i = 0; i < n; i++) {
            max = Integer.max(max, lis(i));
        }
        out.println(max);
        out.close();
    }
}
