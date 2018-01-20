package SegmentTree;


public class SegmentTreeString {

    private int n;
    private String[] st;
    private char[] A;

    public SegmentTreeString(char[] A) {
        this.A = A;
        n = A.length;
        st = new String[5 * n];
        build(1, 0, n - 1);
    }

    public void update(int p, int L, int R, int i, char val) {
        if (L == R) {
            A[i] = val;
            st[p] = val + "";
            return;
        }
        if (i >= L && i <= (L + R) / 2) {
            update(left(p), L, (L + R) / 2, i, val);
        } else if (i > (L + R) / 2 && i <= R) {
            update(right(p), (L + R) / 2 + 1, R, i, val);
        }
        String p1 = st[left(p)];
        String p2 = st[right(p)];
        st[p] = p1 + p2;
    }

    public String rmq(int i, int j) {
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
            st[p] = A[L] + "";
        } else {
            build(left(p), L, (L + R) / 2);
            build(right(p), (L + R) / 2 + 1, R);
            String p1 = st[left(p)];
            String p2 = st[right(p)];
            st[p] = p1 + p2;
        }
    }

    private String rmq(int p, int L, int R, int i, int j) {
        if (i > R || j < L) {
            return "";
        }
        if (L >= i && R <= j) {
            return st[p];
        }
        String p1 = rmq(left(p), L, (L + R) / 2, i, j);
        String p2 = rmq(right(p), (L + R) / 2 + 1, R, i, j);
        if (p1.equals("")) {
            return p2;
        }
        if (p2.equals("")) {
            return p1;
        }
        return p1 + p2;
    }

}
