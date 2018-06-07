package com.eniso.acm.Graphs.SpecialGraphs.BipartiteGraph;

import static java.lang.System.out;
import java.util.ArrayList;
import java.util.Arrays;

public class AugmentingPathAlgorithmforMaxCardinalityBipartiteMatching {

    static int n;
    static ArrayList<Integer> adj[];
    static int[] match;
    static boolean[] visited;

    static int Aug(int l) {
        if (visited[l]) {
            return 0;
        }
        visited[l] = true;

        for (Integer r : adj[l]) {
            if (match[r] == -1 || Aug(match[r]) > 0) {
                match[r] = l;
                return 1;
            }
        }
        return 0;
    }

    static void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
    }

    public static void main(String[] args) {
        n = 5;
        adj = new ArrayList[n];
        for (int i = 0; i < n; ++i) {
            adj[i] = new ArrayList<>();
        }

        addEdge(1, 4);
        addEdge(2, 3);

        visited = new boolean[n];
        match = new int[n];
        Arrays.fill(match, -1);
        int MCBM = 0;
        for (int l = 0; l < n; l++) {
            Arrays.fill(visited, false);
            MCBM += Aug(l);
        }
        out.printf("Found %d matchings\n", MCBM);
    }
}
