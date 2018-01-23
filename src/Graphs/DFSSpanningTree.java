package Graphs;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class DFSSpanningTree {

    public static int n;
    public static int[] visited = new int[100000];
    public static int[] parent = new int[100000];
    public static ArrayList<Integer>[] AdjList = new ArrayList[100000];

    public static void graphCheck(int u, PrintWriter out) {
        visited[u] = 1;
        for (int i = 0; i < AdjList[u].size(); i++) {
            int v = AdjList[u].get(i);
            switch (visited[v]) {
                case -1:
                    parent[v] = u;
                    graphCheck(v, out);
                    break;
                case 1:
                    if (v == parent[u]) {
                        out.printf("Tow ways (%d, %d)-(%d, %d)\n", u, v, v, u);
                    } else {
                        out.printf("Back Edge (%d, %d) (Cycle)\n", u, v);
                    }
                    break;
                default:
                    out.printf("Forward/Cross Edge (%d, %d)\n", u, v);
                    break;
            }
        }
        visited[u] = 2;
    }

    public static void main(String[] args) {
        PrintWriter out = new PrintWriter(System.out);
        n = 9;
        for (int i = 0; i < n; i++) {
            AdjList[i] = new ArrayList<>();
        }
        AdjList[0].add(1);
        AdjList[1].add(0);
        AdjList[1].add(2);
        AdjList[1].add(3);
        AdjList[2].add(1);
        AdjList[2].add(3);
        AdjList[3].add(1);
        AdjList[3].add(2);
        AdjList[3].add(4);
        AdjList[4].add(3);
        AdjList[6].add(7);
        AdjList[6].add(8);
        AdjList[7].add(6);
        AdjList[8].add(6);
        Arrays.fill(visited, -1);
        int numComp = 0;
        for (int i = 0; i < n; i++) {
            if (visited[i] == -1) {
                out.println("Component " + ++numComp + ":");
                graphCheck(i, out);
            }
        }
        out.close();
    }
}
