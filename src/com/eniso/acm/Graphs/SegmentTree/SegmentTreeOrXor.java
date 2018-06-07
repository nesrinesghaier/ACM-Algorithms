package com.eniso.acm.Graphs.SegmentTree;


public class SegmentTreeOrXor {

    private int n;
    private int[] st;
    private int[] A;

    public SegmentTreeOrXor(int[] A, int op) {
        this.A = A;
        n = A.length;
        st = new int[4 * n];
        build(1, 0, n - 1, op);
    }

    public void update(int p, int L, int R, int i, int val, int op) {
        if (L == R) {
            st[p] = A[i] = val;
            return;
        }
        if (i >= L && i <= (L + R) / 2) {
            update(left(p), L, (L + R) / 2, i, val, op ^ 1);
        } else if (i > (L + R) / 2 && i <= R) {
            update(right(p), (L + R) / 2 + 1, R, i, val, op ^ 1);
        }
        int p1 = st[left(p)];
        int p2 = st[right(p)];
        st[p] = merge(p1, op, p2);
    }

    public int getRoot() {
        return st[1];
    }

    private int left(int p) {
        return p << 1;
    }

    private int right(int p) {
        return (p << 1) + 1;
    }

    private int merge(int x1, int op, int x2) {
        if (op == 1) {
            return x1 ^ x2;
        }
        return x1 | x2;
    }

    private void build(int p, int L, int R, int op) {
        if (L == R) {
            st[p] = A[L];
        } else {
            build(left(p), L, (L + R) / 2, op ^ 1);
            build(right(p), (L + R) / 2 + 1, R, op ^ 1);
            int p1 = st[left(p)];
            int p2 = st[right(p)];
            st[p] = merge(p1, op, p2);
        }
    }

}
