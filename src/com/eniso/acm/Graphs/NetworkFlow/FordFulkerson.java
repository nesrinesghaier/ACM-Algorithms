package com.eniso.acm.Graphs.NetworkFlow;

import java.util.LinkedList;
import java.util.Queue;

public class FordFulkerson {

    static int n;

    static boolean bfs(int[][] rGraph, int s, int t, int[] parent) {
        boolean[] visited = new boolean[n];
        Queue<Integer> q = new LinkedList<>();
        q.add(s);
        visited[s] = true;
        parent[s] = -1;

        while (!q.isEmpty()) {
            int u = q.poll();
            for (int v = 0; v < n; v++) {
                if (!visited[v] && rGraph[u][v] > 0) {
                    q.add(v);
                    visited[v] = true;
                    parent[v] = u;
                }
            }
        }

        return visited[t];
    }

    static int getFlow(int[][] graph, int s, int t) {
        int[][] rGraph = graph.clone();

        int[] parent = new int[n];

        int mf = 0;

        while (bfs(rGraph, s, t, parent)) {
            int pf = Integer.MAX_VALUE;
            for (int v = t; v != s; v = parent[v]) {
                int u = parent[v];
                pf = Integer.min(pf, rGraph[u][v]);
            }

            for (int v = t; v != s; v = parent[v]) {
                int u = parent[v];
                rGraph[u][v] -= pf;
                rGraph[v][u] += pf;
            }

            mf += pf;
        }
        return mf;
    }

    public static void main(String[] args) {
        n = 5;
        int[][] graph = new int[][]{{0, 0, 100, 50, 0},
                                    {0, 0, 50, 0, 125},
                                    {100, 50, 0, 50, 50},
                                    {50, 0, 50, 0, 100},
                                    {0, 125, 50, 100, 0}};

        System.out.println("The maximum possible flow is "
                + getFlow(graph, 0, 1));
    }
}
