package com.eniso.acm.Graphs.MST;

import com.eniso.acm.OtherCodes.UnionFind;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;

public class Kruskal {

    static class Pair<T, R> {

        T key;
        R value;

        public Pair(T key, R value) {
            this.key = key;
            this.value = value;
        }

        public T getKey() {
            return key;
        }

        public void setKey(T key) {
            this.key = key;
        }

        public R getValue() {
            return value;
        }

        public void setValue(R value) {
            this.value = value;
        }

    }

    public static void main(String[] args) {
        PrintWriter out = new PrintWriter(System.out);
        ArrayList<Pair<Integer, Pair<Integer, Integer>>> EdgeList = new ArrayList<>();
        int e = 7;
        EdgeList.add(new Pair<>(4, new Pair<>(0, 1)));
        EdgeList.add(new Pair<>(4, new Pair<>(0, 2)));
        EdgeList.add(new Pair<>(6, new Pair<>(0, 3)));
        EdgeList.add(new Pair<>(6, new Pair<>(0, 4)));
        EdgeList.add(new Pair<>(2, new Pair<>(1, 2)));
        EdgeList.add(new Pair<>(8, new Pair<>(2, 3)));
        EdgeList.add(new Pair<>(9, new Pair<>(3, 4)));
        //Minimum Spanning Tree
        EdgeList.sort(Comparator.comparingInt(Pair::getKey));
        //Maximum Spanning Tree
        int mst_cost = 0;
        UnionFind unionFind = new UnionFind(5);
        for (int i = 0; i < e; i++) {
            Pair<Integer, Pair<Integer, Integer>> f = EdgeList.get(i);
            if (!unionFind.isSameSet(f.getValue().getKey(), f.getValue().getValue())) {
                mst_cost += f.getKey();
                unionFind.unionSet(f.getValue().getKey(), f.getValue().getValue());
            }
        }
        out.printf("MST cost = %d (Kruskal's)\n", mst_cost);
        out.close();
    }

}
