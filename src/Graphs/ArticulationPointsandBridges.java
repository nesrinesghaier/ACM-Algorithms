package Graphs;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class ArticulationPointsandBridges {

    public static int n;
    public static int dfsRoot;
    public static int rootChildren;
    public static int dfsNumberCounter;
    public static int[] visited = new int[100000];
    public static int[] low = new int[100000];
    public static boolean[] articulation_vertex = new boolean[100000];
    public static int[] parent = new int[100000];
    public static ArrayList<Integer>[] AdjList = new ArrayList[100000];

    public static void articulationPointAndBridge(int u, PrintWriter out) {
        low[u] = visited[u] = dfsNumberCounter++;
        for (int i = 0; i < AdjList[u].size(); i++) {
            int v = AdjList[u].get(i);
            if (visited[v] == -1) {
                parent[v] = u;
                if (u == dfsRoot) {
                    rootChildren++;
                }
                articulationPointAndBridge(v, out);
                if (low[v] >= visited[u]) {
                    articulation_vertex[v] = true;
                }
                if (low[v] > visited[u]) {
                    out.printf("Edge (%d, %d) is a bridge\n", u, v);
                }
                low[u] = Integer.min(low[u], low[v]);
            } else if (v != parent[u]) {
                low[u] = Integer.min(low[u], visited[v]);
            }
        }
    }

    public static void main(String[] args) {
        PrintWriter out = new PrintWriter(System.out);
        n = 6;
        for (int i = 0; i < n; i++) {
            AdjList[i] = new ArrayList<>();
        }
        AdjList[0].add(1);
        AdjList[1].add(0);
        AdjList[1].add(2);
        AdjList[1].add(4);
        AdjList[1].add(3);
        AdjList[1].add(5);
        AdjList[2].add(1);
        AdjList[3].add(1);
        AdjList[4].add(1);
        AdjList[4].add(5);
        AdjList[5].add(1);
        AdjList[5].add(4);
        Arrays.fill(visited, -1);
        out.println("Bridges :");
        for (int i = 0; i < n; i++) {
            if (visited[i] == -1) {
                dfsRoot = i;
                rootChildren = 0;
                articulationPointAndBridge(i, out);
                articulation_vertex[dfsRoot] = rootChildren > 1;
            }
        }
        out.println("Articulation Points:");
        for (int i = 0; i < n; i++) {
            if (articulation_vertex[i]) {
                out.println("Vertex "+i);
            }
        }
        out.close();
    }
}
