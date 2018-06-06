package com.eniso.acm.Graphs.Traversal;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class FindStronglyConnectedComponents {
    public static int n;
    private static int dfsRoot;
    private static int rootChildren;
    private static int dfsNumberCounter;
    private static boolean[] visited = new boolean[100000];
    private static int[] num = new int[100000];
    private static int[] low = new int[100000];
    private static int numSCC = 0;
    private static boolean[] articulation_vertex = new boolean[100000];
    private static int[] parent = new int[100000];
    private static ArrayList<Integer>[] AdjList = new ArrayList[100000];
    private static Stack<Integer> S = new Stack<>();

    private static void tarjanSCC(int u, PrintWriter out) {
        low[u] = num[u] = dfsNumberCounter++;
        S.push(u);
        visited[u] = true;
        for (int i = 0; i < AdjList[u].size(); i++) {
            int v = AdjList[u].get(i);
            if (num[v] == -1) {
                tarjanSCC(v, out);
            }
            if (visited[v]) {
                low[u] = Math.min(low[u], low[v]);
            }
        }
        if (low[u] == num[u]) {
            out.printf("SCC %d:", ++numSCC);
            while (true) {
                int v = S.pop();
                visited[v] = false;
                out.printf(" %d", v);
                if (u == v) break;
            }
            out.println();
        }
    }

    public static void main(String[] args) {
        PrintWriter out = new PrintWriter(System.out);
        n = 8;
        for (int i = 0; i < n; i++) {
            AdjList[i] = new ArrayList<>();
        }
        AdjList[0].add(1);
        AdjList[1].add(3);
        AdjList[2].add(1);
        AdjList[3].add(4);
        AdjList[3].add(2);
        AdjList[4].add(5);
        AdjList[5].add(7);
        AdjList[7].add(6);
        AdjList[6].add(4);
        Arrays.fill(num, -1);
        Arrays.fill(low, 0);
        Arrays.fill(visited, false);
        dfsNumberCounter = numSCC = 0;
        for (int i = 0; i < n; i++) {
            if (num[i] == -1) {
                tarjanSCC(i, out);
            }
        }
        out.close();
    }
}
