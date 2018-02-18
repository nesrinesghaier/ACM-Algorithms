package com.eniso.acm.Graphs.mst;


import java.io.PrintWriter;
import java.util.*;

public class Prim {

    private static boolean[] taken = new boolean[1000000];
    private static ArrayList<Pair<Integer, Integer>>[] AdjList = new ArrayList[1000000];
    private static PriorityQueue<Pair<Integer, Integer>> pq =
            new PriorityQueue<>();

    public static void process(int vtx) {
        taken[vtx] = true;
        for (int i = 0; i < AdjList[vtx].size(); i++) {
            Pair<Integer, Integer> v = AdjList[vtx].get(i);
            if (!taken[v.getFirst()]) {
                pq.add(new Pair<>(v.getSecond(), v.getFirst()));
            }
        }
    }

    public static void main(String[] args) {
        PrintWriter out = new PrintWriter(System.out);
        Arrays.fill(taken, false);
        int n = 5;
        for (int i = 0; i < n; i++) {
            AdjList[i] = new ArrayList<>();
        }
        AdjList[0].add(new Pair<>(1, 4));
        AdjList[0].add(new Pair<>(2, 4));
        AdjList[0].add(new Pair<>(3, 6));
        AdjList[0].add(new Pair<>(4, 6));
        AdjList[1].add(new Pair<>(2, 2));
        AdjList[2].add(new Pair<>(3, 8));
        AdjList[3].add(new Pair<>(4, 9));
        AdjList[4].add(new Pair<>(3, 9));
        process(0);
        int mst_cost = 0;
        while (!pq.isEmpty()) {
            Pair<Integer, Integer> f = pq.poll();
            int u = f.getSecond();
            int w = f.getFirst();
            if (!taken[u]) {
                mst_cost += w;
                process(u);
            }
        }
        out.printf("MST cost = %d (Prim's)\n", mst_cost);
        out.close();
    }

    static class Pair<A, B> implements Comparable<Pair<A, B>> {
        private A first;
        private B second;

        Pair(A first, B second) {
            super();
            this.first = first;
            this.second = second;
        }

        public int hashCode() {
            int hashFirst = first != null ? first.hashCode() : 0;
            int hashSecond = second != null ? second.hashCode() : 0;

            return (hashFirst + hashSecond) * hashSecond + hashFirst;
        }

        public boolean equals(Object other) {
            if (other instanceof Pair) {
                Pair otherPair = (Pair) other;
                return
                        ((this.first == otherPair.first ||
                                (this.first != null && otherPair.first != null &&
                                        this.first.equals(otherPair.first))) &&
                                (this.second == otherPair.second ||
                                        (this.second != null && otherPair.second != null &&
                                                this.second.equals(otherPair.second))));
            }

            return false;
        }

        public String toString() {
            return "(" + first + ", " + second + ")";
        }

        A getFirst() {
            return first;
        }

        public void setFirst(A first) {
            this.first = first;
        }

        B getSecond() {
            return second;
        }

        public void setSecond(B second) {
            this.second = second;
        }

        @Override
        public int compareTo(Pair<A, B> o) {
            return (Integer) this.getFirst() - (Integer) o.getFirst();
        }
    }
}
