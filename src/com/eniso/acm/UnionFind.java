package com.eniso.acm;

public class UnionFind {

    private int[] p;
    private int[] rank;

    public UnionFind(int n) {
        p = new int[n];
        rank = new int[n];
        for (int i = 0; i < p.length; i++) {
            p[i] = i;
        }
    }

    public int findSet(int i) {
        return (p[i] == i) ? i : (p[i] = findSet(p[i]));
    }

    public boolean isSameSet(int i, int j) {
        return findSet(i) == findSet(j);
    }

    public void unionSet(int i, int j) {
        if (!isSameSet(i, j)) {
            int x = findSet(i);
            int y = findSet(j);
            if (rank[x] > rank[y]) {
                p[y] = x;
            } else {
                p[x] = y;
                if (rank[x] == rank[y]) {
                    rank[y]++;
                }
            }
        }
    }

}
