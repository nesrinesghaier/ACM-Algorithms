package com.eniso.acm.SegmentTree;


public class SegmentTreePGCD {

    private int n;
    private int[] st;
    private int[] A;

    public SegmentTreePGCD(int[] A) {
        this.A = A;
        n = A.length;
        st = new int[5 * n];
        build(1, 0, n - 1);
    }

    public void update(int p, int L, int R, int i, int val) {
        if (L == R) {
            st[p] = A[i] = val;
            return;
        }
        if (i >= L && i <= (L + R) / 2) {
            update(left(p), L, (L + R) / 2, i, val);
        } else if (i > (L + R) / 2 && i <= R) {
            update(right(p), (L + R) / 2 + 1, R, i, val);
        }
        int p1 = st[left(p)];
        int p2 = st[right(p)];
        st[p] = pgcd(p1, p2);
    }

    public int rmq(int i, int j) {
        return rmq(1, 0, n - 1, i, j);
    }

    private int left(int p) {
        return p << 1;
    }

    private int right(int p) {
        return (p << 1) + 1;
    }

    private int pgcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return pgcd(b, a % b);
    }

    private void build(int p, int L, int R) {
        if (L == R) {
            st[p] = A[L];
        } else {
            build(left(p), L, (L + R) / 2);
            build(right(p), (L + R) / 2 + 1, R);
            int p1 = st[left(p)];
            int p2 = st[right(p)];
            st[p] = pgcd(p1, p2);
        }
    }

    private int rmq(int p, int L, int R, int i, int j) {
        if (i > R || j < L) {
            return -1;
        }
        if (L >= i && R <= j) {
            return st[p];
        }
        int p1 = rmq(left(p), L, (L + R) / 2, i, j);
        int p2 = rmq(right(p), (L + R) / 2 + 1, R, i, j);
        if (p1 == -1) {
            return p2;
        }
        if (p2 == -1) {
            return p1;
        }
        return pgcd(p1, p2);
    }

    public static int s = 0;

    public void verif(int p, int L, int R, int i, int j, int x) {
        if (s > 1) {
            return;
        }
        if (i > R || j < L) {
            return;
        }
        if (L == R) {
            if (st[p] % x != 0) {
                s++;
            }
            return;
        }
        if (st[left(p)] % x != 0) {
            verif(left(p), L, (L + R) / 2, i, j, x);
        }
        if (st[right(p)] % x != 0) {
            verif(right(p), (L + R) / 2 + 1, R, i, j, x);
        }
    }

}
