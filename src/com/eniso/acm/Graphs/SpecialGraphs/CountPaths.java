package com.eniso.acm.Graphs.SpecialGraphs;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class CountPaths {

    public static int n;
    private static boolean[] visited;
    private static int[] numPaths;
    private static ArrayList<Integer>[] adjList;

    private static void dfs(int u) {
        visited[u] = true;
        for (int i = 0; i < adjList[u].size(); i++) {
            int v = adjList[u].get(i);
            if (!visited[v]) {
                dfs(v);
            }
            numPaths[u] += numPaths[v];
        }
    }

    public static void main(String[] args) {
        PrintWriter out = new PrintWriter(System.out);
        n = 9;
        visited = new boolean[n];
        numPaths = new int[n];
        numPaths[8] = 1;
        adjList = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adjList[i] = new ArrayList<>();
        }

        adjList[0].add(1);
        adjList[1].add(2);
        adjList[1].add(4);
        adjList[2].add(3);
        adjList[2].add(4);
        adjList[3].add(4);
        adjList[4].add(5);
        adjList[4].add(6);
        adjList[5].add(7);
        adjList[6].add(8);
        adjList[7].add(8);
        
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(i);
            }
        }
        out.println("Numbers of paths is : "+numPaths[0]);
        out.close();
    }
}
