package com.eniso.acm.OtherCodes;


import java.io.*;
import java.util.StringTokenizer;

public class FastScanner {

    private BufferedReader br;
    private StringTokenizer st;

    public FastScanner(InputStream stream) {
        br = new BufferedReader(new InputStreamReader(stream));
    }

    public String next() {
        while (st == null || !st.hasMoreElements()) {
            try {
                st = new StringTokenizer(br.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return st.nextToken();
    }

    public int nextInt() {
        return Integer.parseInt(next());
    }

    public long nextLong() {
        return Long.parseLong(next());
    }

    public double nextDouble() {
        return Double.parseDouble(next());
    }

    public String nextLine() {
        String str = "";
        try {
            str = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }

    public int[] nextIntArray(int n) {
        int[] tab = new int[n];
        for (int i = 0; i < tab.length; i++) {
            tab[i] = nextInt();
        }
        return tab;
    }

    public long[] nextLongArray(int n) {
        long[] tab = new long[n];
        for (int i = 0; i < tab.length; i++) {
            tab[i] = nextLong();
        }
        return tab;
    }

    public double[] nextDoubleArray(int n) {
        double[] tab = new double[n];
        for (int i = 0; i < tab.length; i++) {
            tab[i] = nextDouble();
        }
        return tab;
    }

}
