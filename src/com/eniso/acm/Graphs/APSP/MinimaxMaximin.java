package com.eniso.acm.Graphs.APSP;

import static java.lang.Math.*;
import java.util.Arrays;

public class MinimaxMaximin {

    static int n;
    static long[][] AdjMat;

    public static void main(String[] args) {
        n = 5;
        AdjMat = new long[n][n];
        for (int i = 0; i < n * n; i++) {
            AdjMat[i / n][i % n]
                    = (i / n == i % n) ? 0 : Integer.MAX_VALUE;
        }

        AdjMat[0][1] = 2;
        AdjMat[0][2] = 1;
        AdjMat[0][4] = 3;

        AdjMat[1][3] = 4;

        AdjMat[2][1] = 1;
        AdjMat[2][4] = 1;

        AdjMat[3][0] = 1;
        AdjMat[3][2] = 3;
        AdjMat[3][4] = 5;

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    //Minimax
                    AdjMat[i][j] = min(AdjMat[i][j], max(AdjMat[i][k], AdjMat[k][j]));
                    //Maximin
                    //AdjMat[i][j] = max(AdjMat[i][j], min(AdjMat[i][k], AdjMat[k][j]));
                }
            }
        }

    }

}
