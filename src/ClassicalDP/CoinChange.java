package ClassicalDP;

import java.io.PrintWriter;

public class CoinChange {

    public static int n;
    public static int[] coinValue;

    public static int solve(int value) {
        if (value == 0) {
            return 0;
        }
        if (value < 0) {
            return Integer.MAX_VALUE;
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < coinValue.length; i++) {
            min = Integer.min(min, solve(value - coinValue[i]));
        }
        return 1 + min;
    }

    public static int nbrWays(int i, int value) {
        if (value == 0) {
            return 1;
        }
        if (value < 0 || i == n) {
            return 0;
        }
        return nbrWays(i + 1, value) + nbrWays(i, value - coinValue[i]);
    }

    public static void main(String[] args) {
        PrintWriter out = new PrintWriter(System.out);
        n = 4;
        coinValue = new int[n];
        coinValue[0] = 1;
        coinValue[1] = 3;
        coinValue[2] = 4;
        coinValue[3] = 5;
        out.println(solve(3));
        out.println(nbrWays(0, 5));
        out.close();
    }

}
