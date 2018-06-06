package com.eniso.acm.Graphs.SpecialGraphs.ConvertGraphToDAG;

import static java.lang.System.out;
import java.util.Arrays;

public class SPOJ0101Fishmonger {

    static int n;
    static Pair[][] memo;
    static int[][] travelTime;
    static int[][] toll;
    static Pair mince = new Pair(-1, -1);

    static Pair go(int cur, int timeLeft) {
        if (timeLeft < 0) {
            return new Pair(Integer.MAX_VALUE, Integer.MAX_VALUE);
        }
        if (cur == n - 1) {
            return new Pair(0, 0);
        }
        if (memo[cur][timeLeft]!=null) {
            return memo[cur][timeLeft];
        }
        Pair ans = new Pair(Integer.MAX_VALUE, Integer.MAX_VALUE);
        for (int u = 0; u < n; u++) {
            if (cur != u) {
                Pair nextCity = go(u, timeLeft - travelTime[cur][u]);
                if (nextCity.time + toll[cur][u] < ans.time) {
                    ans.time = nextCity.time + toll[cur][u];
                    ans.toll = nextCity.toll + travelTime[cur][u];
                }
            }
        }
        memo[cur][timeLeft] = ans;
        return memo[cur][timeLeft];
    }

    public static void main(String[] args) {
        n = 4;
        int time = 7;
        memo = new Pair[n][time + 1];
        travelTime = new int[][]{
            {0, 5, 2, 3},
            {5, 0, 2, 3},
            {3, 1, 0, 2},
            {3, 3, 2, 0}
        };
        toll =  new int[][]{
            {0, 2, 2, 7},
            {2, 0, 1, 2},
            {2, 2, 0, 5},
            {7, 2, 5, 0}
        };
        out.println(go(0, time));
    }

    static class Pair {

        long time;
        long toll;

        public Pair(long first, long second) {
            this.time = first;
            this.toll = second;
        }

        @Override
        public int hashCode() {
            int hash = 5;
            hash = 13 * hash + (int) (this.time ^ (this.time >>> 32));
            hash = 13 * hash + (int) (this.toll ^ (this.toll >>> 32));
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final Pair other = (Pair) obj;
            if (this.time != other.time) {
                return false;
            }
            if (this.toll != other.toll) {
                return false;
            }
            return true;
        }

        @Override
        public String toString() {
            return "Pair{" + "first=" + time + ", second=" + toll + '}';
        }

        

    }
}
