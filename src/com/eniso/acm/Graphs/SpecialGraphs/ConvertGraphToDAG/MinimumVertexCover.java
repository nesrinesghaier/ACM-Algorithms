package com.eniso.acm.Graphs.SpecialGraphs.ConvertGraphToDAG;

import static java.lang.System.out;
import java.util.ArrayList;
import java.util.Arrays;

public class MinimumVertexCover {

    static int n;
    static ArrayList<Integer>[] adj;
    static int[][] memo;
    static boolean[] leaf;

    static int MVC(int u, int flag) {
        int ans = 0;
        if (memo[u][flag] != -1) {
            return memo[u][flag];
        }
        if (leaf[u]) {
            ans = flag;
        } else if (flag == 0) {
            for (Integer v : adj[u]) {
                ans += MVC(v, 1);
            }
        } else if (flag == 1) {
            ans++;
            for (Integer v : adj[u]) {
                ans += Math.min(MVC(v, 1), MVC(v, 0));
            }
        }
        memo[u][flag] = ans;
        return memo[u][flag];
    }

    public static void main(String[] args) {
        n = 5;
        adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        memo = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(memo[i], -1);
        }
        leaf = new boolean[n];
        adj[0].add(1);
        adj[0].add(2);
        adj[0].add(3);
        adj[1].add(4);
        leaf[4] = leaf[2] = leaf[3] = true;
        out.println(Math.min(MVC(0,0),MVC(0,1)));
    }
}
