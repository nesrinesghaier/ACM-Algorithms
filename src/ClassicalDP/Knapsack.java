package ClassicalDP;

import java.io.PrintWriter;

public class Knapsack {

    public static int n;
    public static int S;
    public static int[] value;
    public static int[] weight;

    public static int solve(int i, int remW) {
        if (remW == 0 || i == n) {
            return 0;
        }
        if (weight[i] > remW) {
            return solve(i + 1, remW);
        } else {
            return Integer.max(solve(i + 1, remW),
                    value[i] + solve(i + 1, remW - weight[i]));
        }
    }

    public static void main(String[] args) {
        PrintWriter out = new PrintWriter(System.out);
        n = 4;
        S = 12;
        value = new int[n];
        value[0] = 100;
        value[1] = 70;
        value[2] = 50;
        value[3] = 10;
        weight = new int[n];
        weight[0] = 10;
        weight[1] = 4;
        weight[2] = 6;
        weight[3] = 12;
        out.println(solve(0, S));
        out.close();
    }

}
