package com.eniso.acm.Graphs;

import java.io.PrintWriter;

public class FloodFill {

    private static int R;
    private static int C;
    private static char[][] grid = new char[1000][1000];
    private static int dr[] = {1, 1, 0, -1, -1, -1, 0, 1};
    private static int dc[] = {0, 1, 1, 1, 0, -1, -1, -1};

    private static int floodFill(int r, int c, char c1, char c2) {
        if (r < 0 || r >= R || c < 0 || c >= C) {
            return 0;
        }
        if (grid[r][c] != c1) {
            return 0;
        }
        int s = 1;
        grid[r][c] = c2;
        for (int i = 0; i < 8; i++) {
            s += floodFill(r + dr[i], c + dc[i], c1, c2);
        }
        return s;
    }

    public static void main(String[] args) {
        PrintWriter out = new PrintWriter(System.out);
        out.close();
    }
}
