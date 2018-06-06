package com.eniso.acm.Graphs.APSP;

import static java.lang.System.out;
import java.util.Arrays;

public class TransitiveClosure {

    static int n;
    static int[][] AdjMat;

    public static void main(String[] args) {
        n = 5;
        AdjMat = new int[n][n];
        for (int i = 0; i < n * n; i++) {
            AdjMat[i / n][i % n] = (i / n == i % n) ? 1 : 0;
        }

        AdjMat[0][1] = AdjMat[1][0] = 1;
        AdjMat[1][4] = AdjMat[4][1] = 1;

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    AdjMat[i][j] |= (AdjMat[i][k] & AdjMat[k][j]);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            out.println(Arrays.toString(AdjMat[i]));
        }
    }

}
