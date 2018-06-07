package com.eniso.acm.Graphs.SpecialGraphs.BipartiteGraph;

import static java.lang.System.out;
import java.util.Arrays;

public class MaxCardinalityBipartiteMatching {

    static int n;
    static int m;

    static boolean bpm(boolean[][] bpGraph, int u, boolean[] seen, int[] matchR) {
        for (int v = 0; v < n; v++) {
            if (bpGraph[u][v] && !seen[v]) {
                seen[v] = true;
                if (matchR[v] < 0 || bpm(bpGraph, matchR[v], seen, matchR)) {
                    matchR[v] = u;
                    return true;
                }
            }
        }
        return false;
    }

    static int maxBPM(boolean[][] bpGraph) {
        int[] matchR = new int[n];
        Arrays.fill(matchR, -1);

        int ans = 0;
        for (int u = 0; u < m; u++) {
            boolean[] seen = new boolean[n];
            if (bpm(bpGraph, u, seen, matchR)) {
                ans++;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        m = 2;
        n = 3;
        boolean bpGraph[][] = new boolean[][]{
            {true, true, true},
            {true, true, true}
        };
        out.println(maxBPM(bpGraph));
    }
}
