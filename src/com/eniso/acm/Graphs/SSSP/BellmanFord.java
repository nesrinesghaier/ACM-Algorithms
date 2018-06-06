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
public class BellmanFord {

    static int n;
    static ArrayList<Pair>[] adj;
    static int[] dist;

    static boolean work(int s) {
        dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[s] = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int u = 0; u < n; u++) {
                for (Pair v : adj[u]) {
                    dist[v.first] = Math.min(dist[v.first], dist[u] + v.second);
                }
            }
        }
        for (int u = 0; u < n; u++) {
            for (Pair v : adj[u]) {
                if (dist[v.first] > dist[u] + v.second) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        n = 5;
        adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        adj[0].add(new Pair(1, 1000));

        adj[1].add(new Pair(2, 15));
        adj[2].add(new Pair(1, -42));

        if (work(0)) {
            out.println(Arrays.toString(dist));
        } else {
            out.println("Their is negative cycles !!");
        }
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
