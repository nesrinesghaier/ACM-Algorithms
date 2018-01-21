
import java.util.LinkedList;
import java.util.List;

public class GoodMethods {

    public static List<int[]> list = new LinkedList<>();

    /**
     * Permute donne tous les permutations d'un ensemble des entiers,
     * charactères, etc ...
     *
     * @param input
     * @param startindex
     */
    public static void Permute(int[] input, int startindex) {
        int size = input.length;
        if (size == startindex + 1) {
            int[] tab = new int[size];
            for (int i = 0; i < tab.length; i++) {
                tab[i] = input[i];
            }
            list.add(tab);
        } else {
            for (int i = startindex; i < size; i++) {
                int temp = input[i];
                input[i] = input[startindex];
                input[startindex] = temp;
                Permute(input, startindex + 1);
                int temp2 = input[i];
                input[i] = input[startindex];
                input[startindex] = temp2;
            }
        }
    }

    /**
     * Méthode de radix sort d'un tableau des entiers
     *
     * @param f
     * @return
     */
    public static int[] radixSort(int[] f) {
        return radixSort(f, f.length);
    }

    public static int[] radixSort(int[] f, int n) {
        int[] to = new int[n];
        {
            int[] b = new int[65537];
            for (int i = 0; i < n; i++) {
                b[1 + (f[i] & 0xffff)]++;
            }
            for (int i = 1; i <= 65536; i++) {
                b[i] += b[i - 1];
            }
            for (int i = 0; i < n; i++) {
                to[b[f[i] & 0xffff]++] = f[i];
            }
            int[] d = f;
            f = to;
            to = d;
        }
        {
            int[] b = new int[65537];
            for (int i = 0; i < n; i++) {
                b[1 + (f[i] >>> 16)]++;
            }
            for (int i = 1; i <= 65536; i++) {
                b[i] += b[i - 1];
            }
            for (int i = 0; i < n; i++) {
                to[b[f[i] >>> 16]++] = f[i];
            }
            int[] d = f;
            f = to;
            to = d;
        }
        return f;
    }

    public static double bisection(double i, double v, int m) {
        double a = 0.01;
        double b = (1 + i) * v;
        double d = (a + b) / 2;
        double fa = f(a, m, v, i);
        double fb = f(b, m, v, i);
        double fd = f(d, m, v, i);
        int j = 0;
        while (Math.abs(fd) > 1e-10) {
            if (fa * fd < 0) {
                b = d;
                d = (a + b) / 2;
                fb = fd;
                fd = f(d, m, v, i);
            } else {
                a = d;
                d = (a + b) / 2;
                fa = fd;
                fd = f(d, m, v, i);
            }
        }
        return d;
    }

    public static double f(double d, int m, double v, double i) {
        if (m == 1) {
            return v * (1.0 + i) - d;
        }
        return f(d, m - 1, v * (1.0 + i) - d, i);
    }
}
