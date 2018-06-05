/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eniso.acm.Graphs.SSSP;

import static java.lang.System.out;
import java.util.*;

/**
 *
 * @author bacali
 */
public class UnweightedGraph {

    static int n;
    static ArrayList<Integer>[] adj;
    static int[] dist;
    static int[] parent;
    static Queue<Integer> q;

    static void work(int s) {
        q = new LinkedList<>();
        q.add(s);
        parent = new int[n];
        dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[s] = 0;
        while (!q.isEmpty()) {
            int u = q.poll();
            for (Integer v : adj[u]) {
                if (dist[v] == Integer.MAX_VALUE) {
                    dist[v] = dist[u] + 1;
                    parent[v] = u;
                    q.add(v);
                }
            }

        }
    }

}
