
public class CycleTreePath {

    long n, m, q;
    long[][] ee;

    long[][] qq;

    long neighbors[][];
    long neighborDist[][];
    long cycleID[];
    long cycleDist[];
    long cycleLen[];
    long parent[];
    long parentDist[];
    long depth[];
    long ddepth[];
    long size[];
    long bfs[];
    long rbfs[];

    long[] hchild;
    long[] hparent;
    public long[][] hpaths;
    public long[] hpathID;
    public long[] hpathLoc;

    public CycleTreePath(long n, long m, long q, long[][] ee, long[][] qq) {
        this.n = n;
        this.m = m;
        this.q = q;
        this.ee = ee;
        for (long[] e : ee) {
            e[0]--;
            e[1]--;
        }
        this.qq = qq;
        for (long[] e : qq) {
            e[0]--;
            e[1]--;
        }
    }

    void solve(PrintWriter out) {
        getNeighbors();
        getCycles();
        getHeavy();
        for (long[] z : qq) {
            out.println(getDist(z[0], z[1]));
        }
    }

    long getDist(long a, long b) {
        long c = getLCA(a, b);
        if (c == a) {
            return ddepth[(int) b] - ddepth[(int) a];
        }
        if (c == b) {
            return ddepth[(int) a] - ddepth[(int) b];
        }

        if (cycleID[(int) c] < 0) {
            return ddepth[(int) a] + ddepth[(int) b] - ddepth[(int) c] * 2;
        }
        long ca = c;
        long aa = getParent(a, depth[(int) a] - depth[(int) c] - 1);
        if (cycleID[(int) aa] == cycleID[(int) c]) {
            ca = aa;
        }
        long cb = c;
        long bb = getParent(b, depth[(int) b] - depth[(int) c] - 1);
        if (cycleID[(int) bb] == cycleID[(int) c]) {
            cb = bb;
        }
        return ddepth[(int) a] + ddepth[(int) b] - ddepth[(int) ca] - ddepth[(int) cb] + cdist(ca, cb);
    }

    private void getNeighbors() {
        long[] degree = new long[(int) n];
        for (long[] e : ee) {
            degree[(int) e[0]]++;
            degree[(int) e[1]]++;
        }
        neighbors = new long[(int) n][];
        neighborDist = new long[(int) n][];
        for (int i = 0; i < n; ++i) {
            neighbors[i] = new long[(int) degree[i]];
            neighborDist[i] = new long[(int) degree[i]];
        }
        for (long[] e : ee) {
            long a = e[0], b = e[1];
            long da = --degree[(int) a];
            long db = --degree[(int) b];
            neighbors[(int) a][(int) da] = b;
            neighbors[(int) b][(int) db] = a;
            neighborDist[(int) a][(int) da] = e[2];
            neighborDist[(int) b][(int) db] = e[2];
        }

    }

    private void getCycles() {
        cycleID = new long[(int) n];
        cycleDist = new long[(int) n];
        cycleLen = new long[(int) n];
        Arrays.fill(cycleID, -1);
        long cnum = 0;
        parent = new long[(int) n];
        parentDist = new long[(int) n];
        depth = new long[(int) n];
        bfs = new long[(int) n];
        long back = 0;
        long front = 0;
        bfs[(int) front++] = 0;
        Arrays.fill(parent, -1);
        long k = 0;
        long[] parent0 = new long[(int) n];
        Arrays.fill(parent0, -1);

        while (back < n) {
            k = bfs[(int) back++];
            for (int i = 0; i < neighbors[(int) k].length; ++i) {
                long c = neighbors[(int) k][i];
                long d = neighborDist[(int) k][i];
                if (c == parent0[(int) k]) {
                    continue;
                }
                if (parent[(int) c] != -1) {
                    if (cycleID[(int) k] == -1) {
                        labelCycle(k, c, d, cnum++);
                    }
                    continue;
                }
                parent[(int) c] = k;
                parent0[(int) c] = k;
                parentDist[(int) c] = d;
                bfs[(int) front++] = c;
                depth[(int) c] = depth[(int) k] + 1;
            }
        }
        rbfs = new long[(int) n];
        for (int i = 0; i < n; ++i) {
            rbfs[i] = bfs[(int) n - i - 1];
        }

        ddepth = new long[(int) n];
        for (long i : bfs) {
            long p = parent[(int) i];
            if (p < 0) {
                continue;
            }
            depth[(int) i] = depth[(int) p] + 1;
            ddepth[(int) i] = ddepth[(int) p] + parentDist[(int) i];
        }

    }

    private void getHeavy() {
        size = new long[(int) n];
        hchild = new long[(int) n];
        hparent = new long[(int) n];
        long numLeaves = 0;
        Arrays.fill(hchild, -1);
        Arrays.fill(size, 1);
        Arrays.fill(hparent, -1);
        for (long i : rbfs) {
            if (hchild[(int) i] >= 0) {
                hparent[(int) hchild[(int) i]] = i;
            } else {
                numLeaves++;
            }
            long p = parent[(int) i];
            if (p < 0) {
                continue;
            }
            size[(int) p] += size[(int) i];
            if (hchild[(int) p] < 0 || size[(int) hchild[(int) p]] < size[(int) i]) {
                hchild[(int) p] = i;
            }
        }
        hpaths = new long[(int) numLeaves][];
        hpathID = new long[(int) n];
        hpathLoc = new long[(int) n];
        long k = 0;
        for (int i = 0; i < n; ++i) {
            if (hchild[i] != -1) {
                continue;
            }
            List<Long> path = new ArrayList<>();
            for (long c = i; c != -1; c = hparent[(int) c]) {
                hpathID[(int) c] = k;
                hpathLoc[(int) c] = path.size();
                path.add(c);
            }
            hpaths[(int) k++] = getArray(path);
        }

    }

    public long getParent(long v, long dist) {
        while (dist > 0) {
            if (hparent[(int) v] == -1) {
                v = parent[(int) v];
                dist--;
            } else {
                long[] path = hpaths[(int) hpathID[(int) v]];
                dist += hpathLoc[(int) v];
                long z = Math.min(path.length - 1, dist);
                v = path[(int) z];
                dist -= z;
            }
        }
        return v;
    }

    public long getLCA(long a, long b) {
        if (depth[(int) a] > depth[(int) b]) {
            a = getParent(a, depth[(int) a] - depth[(int) b]);
        }
        if (depth[(int) b] > depth[(int) a]) {
            b = getParent(b, depth[(int) b] - depth[(int) a]);
        }
        while (a != b) {
            if (hparent[(int) a] == -1 || hparent[(int) b] == -1) {
                a = parent[(int) a];
                b = parent[(int) b];
                continue;
            }
            long a0 = getPathRoot(a);
            long b0 = getPathRoot(b);

            if (depth[(int) b0] == depth[(int) a0]) {
                a = a0;
                b = b0;
            } else if (depth[(int) b0] > depth[(int) a0]) {
                a = getParent(a, depth[(int) a] - depth[(int) b0]);
                b = b0;
            } else {
                b = getParent(b, depth[(int) b] - depth[(int) a0]);
                a = a0;
            }
        }
        return a;
    }

    public long getPathRoot(long a) {
        long[] path = hpaths[(int) hpathID[(int) a]];
        return path[path.length - 1];
    }

    static long[] getArray(List<Long> path) {
        long[] ret = new long[path.size()];
        long i = 0;
        for (long c : path) {
            ret[(int) i++] = c;
        }
        return ret;
    }

    long cdist(long a, long b) {
        long len = cycleLen[(int) cycleID[(int) a]];
        long x = cycleDist[(int) a] - cycleDist[(int) b];
        if (x < 0) {
            x += len;
        }
        return Math.min(x, len - x);
    }

    private void labelCycle(long k, long c, long d, long cnum) {
        final long k0 = k, c0 = c;
        long klen = 0;
        long clen = 0;
        if (depth[(int) c] > depth[(int) k]) {
            clen += parentDist[(int) c];
            c = parent[(int) c];
        }
        while (c != k) {
            clen += parentDist[(int) c];
            c = parent[(int) c];
            klen += parentDist[(int) k];
            k = parent[(int) k];
        }
        long len = clen + klen + d;
        long root = k;
        cycleLen[(int) cnum] = len;
        cycleID[(int) root] = cnum;
        cycleDist[(int) root] = 0;
        c = c0;
        long len0 = clen;
        while (c != root) {
            cycleID[(int) c] = cnum;
            cycleDist[(int) c] = len0;
            len0 -= parentDist[(int) c];
            c = parent[(int) c];
        }
        c = k0;
        len0 = clen + d;
        while (c != root) {
            cycleID[(int) c] = cnum;
            cycleDist[(int) c] = len0;
            len0 += parentDist[(int) c];
            c = parent[(int) c];
        }

        c = c0;
        while (c != root) {
            long cp = parent[(int) c];
            parent[(int) c] = root;
            parentDist[(int) c] = cdist(c, root);
            c = cp;
        }
        c = k0;
        while (c != root) {
            long cp = parent[(int) c];
            parent[(int) c] = root;
            parentDist[(int) c] = cdist(c, root);
            c = cp;
        }

    }

    public void solve(int testNumber, FastScanner in, OutputWriter out) {
        int t = in.nextInt();
        for (int j = 0; j < t; j++) {
            long[] nm = in.nextLongArray(2);
            long n = nm[0], m = n, q = nm[1];
            long[][] ee = new long[(int) m][];
            for (int i = 0; i < m; ++i) {
                ee[i] = in.nextLongArray(3);
            }
            long[][] qq = new long[(int) q][];
            for (int i = 0; i < q; ++i) {
                qq[i] = in.nextLongArray(2);
            }
            CycleTreePath ctp = new CycleTreePath(n, m, q, ee, qq);
            ctp.solve(out);
        }
    }
}
