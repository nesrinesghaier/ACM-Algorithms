package Graphs;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BipartiteGraph {

    public static int n;
    public static ArrayList<Integer>[] AdjList = new ArrayList[1000];

    public static boolean isBipartite(int s) {
        int[] color = new int[1000];
        Arrays.fill(color, -1);
        color[s] = 0;
        Queue<Integer> q = new LinkedList<>();
        q.add(s);
        while (!q.isEmpty()) {
            int u = q.poll();
            for (int i = 0; i < AdjList[u].size(); i++) {
                int v = AdjList[u].get(i);
                if (color[v] == -1) {
                    color[v] = 1 - color[u];
                    q.add(v);
                } else if (color[v] == color[u]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        PrintWriter out = new PrintWriter(System.out);
        int n = 13;
        for (int i = 0; i < n; i++) {
            AdjList[i] = new ArrayList<>();
        }
        AdjList[0].add(1);
        AdjList[0].add(4);
        AdjList[1].add(0);
        AdjList[1].add(2);
        AdjList[1].add(5);
        AdjList[2].add(1);
        AdjList[2].add(3);
        AdjList[2].add(6);
        AdjList[3].add(2);
        AdjList[3].add(7);
        AdjList[4].add(0);
        AdjList[4].add(8);
        AdjList[5].add(1);
        AdjList[5].add(6);
        AdjList[5].add(10);
        AdjList[6].add(2);
        AdjList[6].add(5);
        AdjList[6].add(11);
        AdjList[7].add(3);
        AdjList[7].add(12);
        AdjList[8].add(4);
        AdjList[8].add(9);
        AdjList[9].add(8);
        AdjList[9].add(10);
        AdjList[10].add(5);
        AdjList[10].add(9);
        AdjList[10].add(11);
        AdjList[11].add(6);
        AdjList[11].add(10);
        AdjList[11].add(12);
        AdjList[12].add(7);
        AdjList[12].add(11);
        out.println(isBipartite(0));
        out.close();
    }
}
