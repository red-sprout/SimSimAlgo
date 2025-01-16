import java.io.*;
import java.util.*;

public class Main {
    static int n, size;
    static long[] tree, lazy;

    static final int MAX = 1_000_000;

    static void updateLazy(int node, int s, int e) {
        if(lazy[node] == 0) return;
        tree[node] += (e - s + 1) * lazy[node];
        if(s != e) {
            lazy[node << 1] += lazy[node];
            lazy[node << 1 | 1] += lazy[node];
        }
        lazy[node] = 0;
    }

    static void updateTree(int node, int s, int e, int ts, int te, long val) {
        updateLazy(node, s, e);
        if(e < ts || te < s) return;
        if(ts <= s && e <= te) {
            tree[node] += (e - s + 1) * val;
            if(s != e) {
                lazy[node << 1] += val;
                lazy[node << 1 | 1] += val;
            }
            return;
        }
        int mid = (s + e) >> 1;
        updateTree(node << 1, s, mid, ts, te, val);
        updateTree(node << 1 | 1, mid + 1, e, ts, te, val);
        tree[node] = tree[node << 1] + tree[node << 1 | 1];
    }

    static long get(int node, int s, int e, int idx) {
        updateLazy(node, s, e);
        if(e < idx || idx < s) return 0;
        if(s == e) return tree[node];
        int mid = (s + e) >> 1;
        long left = get(node << 1, s, mid, idx);
        long right = get(node << 1 | 1, mid + 1, e, idx);
        return left + right;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        n = Integer.parseInt(br.readLine());
        size = 1 << ((int) Math.ceil(Math.log(MAX) / Math.log(2)) + 1);
        tree = new long[size];
        lazy = new long[size];

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            long left = get(1, 1, MAX, l);
            long right = get(1, 1, MAX, r);
            sb.append(left + right).append('\n');
            updateTree(1, 1, MAX, l, l, -left);
            updateTree(1, 1, MAX, r, r, -right);
            if(l + 1 < r) updateTree(1, 1, MAX, l + 1, r - 1, 1);
        }

        System.out.print(sb);
        br.close();
    }
}
