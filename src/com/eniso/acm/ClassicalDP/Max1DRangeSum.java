package com.eniso.acm.ClassicalDP;

import java.io.PrintWriter;

public class Max1DRangeSum {

    public static void main(String[] args) {
        PrintWriter out = new PrintWriter(System.out);
        int n = 9;
        int tab[] = {4, -5, 4, -3, 4, 4, -4, 4, -5};
        int s = 0;
        int ans = 0;
        for (int aTab : tab) {
            s += aTab;
            ans = Integer.max(ans, s);
            s = (s < 0) ? 0 : s;
        }
        out.println(ans);
        out.close();
    }
}
