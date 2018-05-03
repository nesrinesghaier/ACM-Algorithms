package com.eniso.acm.OtherCodes;

import java.io.PrintWriter;
import java.util.Arrays;

public class UVa10003 {

    public static int[][] memo = new int[101][101];
    public static int[] A = new int[1001];

    public static int minCost(int left, int right) {
        if (right==left+1) {
            return 0;
        }
        if (memo[left][right] != -1) {
            return memo[left][right];
        }
        int min = Integer.MAX_VALUE;
        for (int i = left+1; i < right; i++) {
            min= Integer.min(min, minCost(left, i)
                    +minCost(i, right)+A[right]-A[left]);
        }
        return memo[left][right] = min;
    }

    public static void main(String[] args) {
        PrintWriter out = new PrintWriter(System.out);
        for (int i = 0; i < 101; i++) {
            Arrays.fill(memo[i], -1);
        }
        int n = 3;
        A = Arrays.copyOf(new int[]{0,25,50,75,100}, 5);
        out.println(minCost(0, n+1));
        out.close();
    }
}
