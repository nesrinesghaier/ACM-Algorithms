package com.eniso.acm.Graphs.SpecialGraphs.EulerianGraph;

import static java.lang.System.out;
import java.util.ArrayList;

public class PrintEulerTour {

    static int n;
    static ArrayList<Pair>[] adj;
    static ArrayList<Integer> cyc;

    static void EulerTour(int u) {
        for (Pair v : adj[u]) {
            if (v.second) {
                v.second = false;
                for (Pair uu : adj[v.first]) {
                    if (uu.first==u && uu.second){
                        uu.second = false;
                        break;
                    }
                }
                cyc.add(u);
                EulerTour(v.first);
            }
        }
    }
    
    public static void main(String[] args) {
        n = 5;
        cyc = new ArrayList<>();
        adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        adj[0].add(new Pair(1));
        adj[1].add(new Pair(0));
        
        adj[0].add(new Pair(2));
        adj[2].add(new Pair(0));
        
        adj[2].add(new Pair(1));
        adj[1].add(new Pair(2));
        
        adj[3].add(new Pair(1));
        adj[1].add(new Pair(3));
        
        adj[4].add(new Pair(1));
        adj[1].add(new Pair(4));
        
        adj[2].add(new Pair(3));
        adj[3].add(new Pair(2));
        
        adj[2].add(new Pair(4));
        adj[4].add(new Pair(2));
        
        adj[4].add(new Pair(3));
        adj[3].add(new Pair(4));
        
        EulerTour(0);
        
        for (Integer ii : cyc) {
            out.print(ii+" ");
        }
        out.println();
    }

    static class Pair {

        int first;
        boolean second;

        public Pair(int first) {
            this.first = first;
            this.second = true;
        }

        @Override
        public String toString() {
            return "Pair{" + "first=" + first + ", second=" + second + '}';
        }

    }
}
