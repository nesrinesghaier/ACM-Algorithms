package com.eniso.acm.ChessQueensProblems;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class NQueensProblem {

    private static boolean[] rw = new boolean[30];
    private static boolean[] ld = new boolean[30];
    private static boolean[] rd = new boolean[30];
    public static int n;
    private static int ans = 0;
    private static char[][] board = new char[15][15];

    private static void backtrack(int c) {
        if (c == n) {
            ans++;
            return;
        }
        for (int r = 0; r < n; r++) {
            if (board[r][c] != '*' && !rw[r]
                    && !ld[r - c + n - 1] && !rd[r + c]) {
                rw[r] = ld[r - c + n - 1] = rd[r + c] = true;
                backtrack(c + 1);
                rw[r] = ld[r - c + n - 1] = rd[r + c] = false;
            }
        }
    }

    public static void main(String[] args) {
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        int j = 1;
        while (n != 0) {
            ans = 0;
            Arrays.fill(rw, false);
            Arrays.fill(ld, false);
            Arrays.fill(rd, false);
            for (int i = 0; i < n; i++) {
                board[i] = in.next().toCharArray();
            }
            backtrack(0);
            out.printf("Case %d: %d\n", j++, ans);
            n = in.nextInt();
        }
        out.close();
    }
}
