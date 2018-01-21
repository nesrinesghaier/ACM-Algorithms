package ChessQueensProblems;

import java.util.Arrays;
import java.util.Scanner;

public class EightQueensProblem {

    public static int[] row = new int[8];
    public static int TC;
    public static int a;
    public static int b;
    public static int lineCounter;

    public static boolean place(int r, int c) {
        for (int prev = 0; prev < c; prev++) {
            if (row[prev] == r || Math.abs(row[prev] - r) == Math.abs(prev - c)) {
                return false;
            }
        }
        return true;
    }

    public static void backtrack(int c) {
        if (c == 8 && row[b] == a) {
            System.out.print(" " + (++lineCounter) + "\t" + (row[0] + 1));
            for (int i = 1; i < 8; i++) {
                System.out.print(" " + (row[i] + 1));
            }
            System.out.println();
        }
        for (int r = 0; r < 8; r++) {
            if (place(r, c)) {
                row[c] = r;
                backtrack(c + 1);
            }
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int tc = in.nextInt();
        while (tc-- > 0) {
            a = in.nextInt() - 1;
            b = in.nextInt() - 1;
            Arrays.fill(row, 0);
            System.out.println("SOLN\t   COLUMN");
            System.out.println(" #\t1 2 3 4 5 6 7 8\n");
            backtrack(0);
            if (tc > 0) {
                System.out.println();
            }
        }
    }
}
