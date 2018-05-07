package com.eniso.acm.OtherCodes;

import java.util.LinkedList;
import java.util.List;

public class GoodMethods {

    public static int upper_bound(int[] tab, int l, int h, long x) {
        while (l < h) {
            int mid = (l + h) / 2;
            if (tab[mid] <= x) {
                l = mid + 1;
            } else {
                h = mid;
            }
        }
        return l;
    }

    public static List<int[]> list = new LinkedList<>();

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

    public static int[] radixsort(int[] input) {

        // Largest place for a 32-bit int is the 1 billion's place
        for (int place = 1; place <= 1000000000; place *= 10) {
            // Use counting sort at each digit's place
            input = countingSort(input, place);
        }

        return input;
    }

    private static int[] countingSort(int[] input, int place) {
        int[] out = new int[input.length];

        int[] count = new int[10];

        for (int i = 0; i < input.length; i++) {
            int digit = getDigit(input[i], place);
            count[digit] += 1;
        }

        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }

        for (int i = input.length - 1; i >= 0; i--) {
            int digit = getDigit(input[i], place);

            out[count[digit] - 1] = input[i];
            count[digit]--;
        }

        return out;

    }

    private static int getDigit(int value, int digitPlace) {
        return ((value / digitPlace) % 10);
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
