package com.eniso.acm.Graphs.APSP;

import java.util.Arrays;

public class FloydWarshall {

    static int n;
    static long[][] AdjMat;
    static int[][] parent;

    public static void main(String[] args) {
        n = 5;
        AdjMat = new long[n][n];
        parent = new int[n][n];
        for (int i = 0; i < n * n; i++) {
            AdjMat[i / n][i % n]
                    = (i / n == i % n) ? 0 : Integer.MAX_VALUE;
            parent[i / n][i % n] = i / n;
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
                    if (AdjMat[i][k] + AdjMat[k][j] < AdjMat[i][j]) {
                        AdjMat[i][j] = AdjMat[i][k] + AdjMat[k][j];
                        parent[i][j] = parent[k][j];
                    }
                }
            }
        }

        printPath(1, 4);
        System.out.println();
    }

    static void printPath(int i, int j) {
        if (i != j) {
            printPath(i, parent[i][j]);
        }
        System.out.printf(" %d", j);
    }

}
