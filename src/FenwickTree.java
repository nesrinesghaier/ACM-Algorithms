
import java.io.PrintWriter;


public class FenwickTree {

    private int[] ft;

    public FenwickTree(int n) {
        ft = new int[n + 1];
    }

    private int LSOne(int s) {
        return s & (-s);
    }

    public int rsq(int b) {
        int s = 0;
        for (; b > 0; b -= LSOne(b)) {
            s += ft[b];
        }
        return s;
    }

    public int rsq(int a, int b) {
        return rsq(b) - (a == 1 ? 0 : rsq(a - 1));
    }

    public void adjust(int k, int v) {
        for (; k < ft.length; k += LSOne(k)) {
            ft[k] += v;
        }
    }

    public static void main(String[] args) {
        PrintWriter out = new PrintWriter(System.out);
        int f[] = {2, 4, 5, 5, 6, 6, 6, 7, 7, 8, 9};
        FenwickTree ft = new FenwickTree(1000000);
        for (int i = 0; i < f.length; i++) {
            ft.adjust(f[i], 1);            
        }
        out.println(ft.rsq(1,1));
        out.println(ft.rsq(1,2));
        out.println(ft.rsq(1,6));
        out.println(ft.rsq(1,10));
        out.println(ft.rsq(3,6));
        ft.adjust(5, 2);
        out.println(ft.rsq(1,10));
        out.close();
    }

}
