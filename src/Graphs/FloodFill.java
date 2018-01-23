package Graphs;

import java.io.PrintWriter;

public class FloodFill {

    public static int R;
    public static int C;
    public static char[][] grid = new char[1000][1000];
    public static int dr[] = {1, 1, 0, -1, -1, -1, 0, 1};
    public static int dc[] = {0, 1, 1, 1, 0, -1, -1, -1};

    public static int floodFill(int r, int c, char c1, char c2) {
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
