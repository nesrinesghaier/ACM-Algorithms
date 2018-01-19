
public class SegmentTreeSum {

    private int n;
    private int[] st;
    private int[] A;

    public SegmentTreeSum(int[] A) {
        this.A = A;
        n = A.length;
        st = new int[4 * n];
        build(1, 0, n - 1);
    }

    public int rsq(int i, int j) {
        return rsq(1, 0, n - 1, i, j);
    }

    public void updateValue(int i, int newVal) {
        if (i < 0 || i > n - 1) {
            System.out.println("Invalid Input!");
            return;
        }
        int val = newVal - A[i];
        A[i] = newVal;
        updateValue(1, 0, n-1, i, val);
    }

    private int left(int p) {
        return p << 1;
    }

    private int right(int p) {
        return (p << 1) + 1;
    }

    private int build(int p, int L, int R) {
        if (L == R) {
            st[p] = A[L];
        } else {
            st[p] = build(left(p), L, (L + R) / 2)
                    + build(right(p), (L + R) / 2 + 1, R);
        }
        return st[p];
    }

    private int rsq(int p, int L, int R, int i, int j) {
        if (i > R || j < L) {
            return 0;
        }
        if (L >= i && R <= j) {
            return st[p];
        }
        return rsq(left(p), L, (L + R) / 2, i, j)
                + rsq(right(p), (L + R) / 2 + 1, R, i, j);
    }

    private void updateValue(int p, int L, int R, int i, int val) {
        if (i < L || i > R) {
            return;
        }
        st[p] = st[p] + val;
        if (L != R) {
            updateValue(left(p), L, (L + R) / 2, i, val);
            updateValue(right(p), (L + R) / 2 + 1, R, i, val);
        }
    }
}
