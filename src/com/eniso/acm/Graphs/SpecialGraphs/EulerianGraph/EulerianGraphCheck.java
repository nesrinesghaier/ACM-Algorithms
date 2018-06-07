package com.eniso.acm.Graphs.SpecialGraphs.EulerianGraph;

import java.io.*;
import java.util.*;

public class EulerianGraphCheck {

    static int n;
    static ArrayList<Integer> adj[];

    static void DFS(int u, boolean visited[]) {
        visited[u] = true;
        for (Integer v : adj[u]) {
            if (!visited[v]) {
                DFS(v, visited);
            }
        }
    }

    static boolean isConnected() {
        boolean visited[] = new boolean[n];
        int i;
        for (i = 0; i < n; i++) {
            if (!adj[i].isEmpty()) {
                break;
            }
        }
        if (i == n) {
            return true;
        }
        DFS(i, visited);
        for (i = 0; i < n; i++) {
            if (visited[i] == false && adj[i].size() > 0) {
                return false;
            }
        }
        return true;
    }

    static int isEulerian() {
        if (isConnected() == false) {
            return 0;
        }
        int odd = 0;
        for (int i = 0; i < n; i++) {
            if (adj[i].size() % 2 != 0) {
                odd++;
            }
        }
        if (odd > 2) {
            return 0;
        }
        return (odd == 2) ? 1 : 2;
    }

    static void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
    }

    public static void main(String args[]) {
        n = 5;
        adj = new ArrayList[n];
        for (int i = 0; i < n; ++i) {
            adj[i] = new ArrayList<>();
        }
        addEdge(1, 0);
        addEdge(0, 2);
        addEdge(2, 1);
        addEdge(0, 3);
        addEdge(3, 4);
        int res = isEulerian();
        switch (res) {
            case 0:
                System.out.println("graph is not Eulerian");
                break;
            case 1:
                System.out.println("graph has a Euler path");
                break;
            default:
                System.out.println("graph has a Euler cycle");
                break;
        }
    }
}
