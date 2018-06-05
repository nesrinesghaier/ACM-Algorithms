package com.eniso.acm.OtherCodes;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FastScanner {

    final private int BUFFER_SIZE = 1 << 16;
    private DataInputStream din;
    private byte[] buffer;
    private int bufferPointer, bytesRead;

    public FastScanner(InputStream inputStream) {
        din = new DataInputStream(inputStream);
        buffer = new byte[BUFFER_SIZE];
        bufferPointer = bytesRead = 0;
    }

    public String nextLine() {
        byte[] buf = new byte[64];
        int cnt = 0, c;
        while ((c = read()) != -1) {
            if (c == '\n') {
                break;
            }
            buf[cnt++] = (byte) c;
        }
        return new String(buf, 0, cnt);
    }

    public int nextInt() {
        int ret = 0;
        byte c = read();
        while (c <= ' ') {
            c = read();
        }
        boolean neg = (c == '-');
        if (neg) {
            c = read();
        }
        do {
            ret = ret * 10 + c - '0';
        } while ((c = read()) >= '0' && c <= '9');

        if (neg) {
            return -ret;
        }
        return ret;
    }

    public long nextLong() {
        long ret = 0;
        byte c = read();
        while (c <= ' ') {
            c = read();
        }
        boolean neg = (c == '-');
        if (neg) {
            c = read();
        }
        do {
            ret = ret * 10 + c - '0';
        } while ((c = read()) >= '0' && c <= '9');
        if (neg) {
            return -ret;
        }
        return ret;
    }

    public double nextDouble() {
        double ret = 0, div = 1;
        byte c = read();
        while (c <= ' ') {
            c = read();
        }
        boolean neg = (c == '-');
        if (neg) {
            c = read();
        }

        do {
            ret = ret * 10 + c - '0';
        } while ((c = read()) >= '0' && c <= '9');

        if (c == '.') {
            while ((c = read()) >= '0' && c <= '9') {
                ret += (c - '0') / (div *= 10);
            }
        }

        if (neg) {
            return -ret;
        }
        return ret;
    }

    private void fillBuffer() {
        try {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (bytesRead == -1) {
            buffer[0] = -1;
        }
    }

    private byte read() {
        if (bufferPointer == bytesRead) {
            fillBuffer();
        }
        return buffer[bufferPointer++];
    }

    public void close() throws IOException {
        if (din == null) {
            return;
        }
        din.close();
    }

    public int[] nextIntArray(int n) {
        int[] tab = new int[n];
        for (int i = 0; i < n; i++) {
            tab[i] = nextInt();
        }
        return tab;        
    }

    public long[] nextLongArray(int n) {
        long[] tab = new long[n];
        for (int i = 0; i < n; i++) {
            tab[i] = nextLong();
        }
        return tab;        
    }
}
