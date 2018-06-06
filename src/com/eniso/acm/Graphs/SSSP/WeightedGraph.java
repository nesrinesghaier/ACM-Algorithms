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
public class WeightedGraph {

    static int n;
    static ArrayList<Pair>[] adj;
    static int[] dist;
    static PriorityQueue<Pair> q;

    static void work(int s) {
        q = new PriorityQueue<>((p1, p2) -> p1.second - p2.second);
        q.add(new Pair(s, 0));
        dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[s] = 0;
        while (!q.isEmpty()) {
            Pair front = q.poll();
            int u = front.first;
            int d = front.second;
            if (d > dist[u]) {
                continue;
            }
            for (Pair v : adj[u]) {
                if (dist[u] + v.second < dist[v.first]) {
                    dist[v.first] = dist[u] + v.second;
                    q.add(new Pair(v.first, dist[v.first]));
                }
            }

        }
    }
    
    public static void main(String[] args) {
        n = 5;
        adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        adj[0].add(new Pair(2, 6));
        adj[2].add(new Pair(0, 6));
        
        adj[0].add(new Pair(4, 1));
        adj[4].add(new Pair(0, 1));
        
        adj[1].add(new Pair(2, 2));
        adj[2].add(new Pair(1, 2));
        
        adj[1].add(new Pair(3, 3));
        adj[3].add(new Pair(1, 3));
        
        adj[1].add(new Pair(4, 6));
        adj[4].add(new Pair(1, 6));
        
        adj[2].add(new Pair(3, 7));
        adj[3].add(new Pair(2, 7));
        
        adj[4].add(new Pair(3, 5));
        adj[3].add(new Pair(4, 5));
        
        work(2);
        out.println(Arrays.toString(dist));
    }

    static class Pair {

        int first;
        int second;

        public Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public String toString() {
            return "Pair{" + "first=" + first + ", second=" + second + '}';
        }

    }

}
