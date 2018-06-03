public class LCAtoRMQ {

    static class Edge {
        int u;
        long a, b;

        Edge(int x, long z, long l) {
            u = x;
            a = z;
            b = l;
        }

    }

    int level = 18;
    int MAXN = 100003;
    ArrayList<Edge>[] tree = new ArrayList[MAXN];
    int[] depth = new int[MAXN];
    int[][] parent = new int[MAXN][level];
    long[] down = new long[MAXN];
    long[] up = new long[MAXN];
    long ans = 0;

    void dfs(int cur, int prev, long d1, long d2) {
        depth[cur] = depth[prev] + 1;
        down[cur] = d1;
        up[cur] = d2;
        parent[cur][0] = prev;
        for (Edge e : tree[cur]) {
            int v = e.u;
            if (v != prev) {
                dfs(v, cur, d1 + e.a, d2 + e.b);
            }
        }
    }

    void precomputeSparseMatrix(int n) {
        for (int i = 1; i < level; i++) {
            for (int node = 1; node <= n; node++) {
                if (parent[node][i - 1] != -1)
                    parent[node][i] =
                            parent[parent[node][i - 1]][i - 1];
            }
        }
    }

    int swap(int a, int b) {
        return a;
    }


    int LCA(int u, int v) {
        if (depth[v] < depth[u]) {
            v = swap(u, u = v);
        }

        int diff = depth[v] - depth[u];

        for (int i = 0; i < level; i++) {
            if (((diff >> i) & 1) == 1) {
                v = parent[v][i];
            }
        }

        if (u == v) {
            return u;
        }

        for (int i = level - 1; i >= 0; i--) {
            if (parent[u][i] != parent[v][i]) {
                u = parent[u][i];
                v = parent[v][i];
            }
        }

        return parent[u][0];
    }

    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        FastScanner in = new FastScanner(inputStream);
        OutputWriter out = new OutputWriter(outputStream);
        LCAtoRMQ solver = new LCAtoRMQ();
        solver.solve(1, in, out);
        out.close();
    }

    public void solve(int testNumber, FastScanner in, OutputWriter out) {
        int t = in.nextInt();
        for (int i = 0; i < t; i++) {
            int n = in.nextInt();
            depth = new int[MAXN];
            down = new long[MAXN];
            up = new long[MAXN];
            for (int j = 0; j <= n; j++) {
                tree[j] = new ArrayList<>();
            }
            for (int j = 0; j < 18; j++) {
                for (int k = 1; k <= n; k++) {
                    parent[k][j] = -1;
                }
            }
            ans = 0L;
            for (int j = 0; j < n - 1; j++) {
                int u = in.nextInt();
                int v = in.nextInt();
                long c1 = in.nextLong();
                long c2 = in.nextLong();
                tree[u].add(new Edge(v, c1, c2));
                tree[v].add(new Edge(u, c2, c1));
                ans += c1 + c2;
            }
            dfs(1, 0, 0, 0);
            precomputeSparseMatrix(n);
            int q = in.nextInt();
            for (int j = 0; j < q; j++) {
                int x = in.nextInt();
                int y = in.nextInt();
                int c = LCA(x, y);
                long a1 = down[x] - down[c];
                long a2 = up[y] - up[c];
                out.println(ans - a1 - a2);
            }
        }
    }

}