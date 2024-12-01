import java.io.*;
import java.util.*;

public class Main {
    static int N, M, size;
    static int[] tree;
    static boolean[] lazy;
    static void update(int s, int t) {
        updateTree(1, 1, N, s, t);
    }
    static void updateLazy(int node, int s, int e) {
        if(!lazy[node]) return;
        tree[node] = (e - s + 1) - tree[node];
        if(s != e) {
            lazy[2 * node] = !lazy[2 * node];
            lazy[2 * node + 1] = !lazy[2 * node + 1];
        }
        lazy[node] = false;
    }
    static void updateTree(int node, int s, int e, int ts, int te) {
        updateLazy(node, s, e);
        if(te < s || e < ts) return;
        if(ts <= s && e <= te) {
            tree[node] = (e - s + 1) - tree[node];
            if(s != e) {
                lazy[2 * node] = !lazy[2 * node];
                lazy[2 * node + 1] = !lazy[2 * node + 1];
            }
            return;
        }
        int mid = (s + e) / 2;
        updateTree(2 * node, s, mid, ts, te);
        updateTree(2 * node + 1, mid + 1, e, ts, te);
        tree[node] = tree[2 * node] + tree[2 * node + 1];
    }
    static int query(int s, int t) {
        return get(1, 1, N, s, t);
    }
    static int get(int node, int s, int e, int ts, int te) {
        updateLazy(node, s, e);
        if(te < s || e < ts) return 0;
        if(ts <= s && e <= te) return tree[node];
        int mid = (s + e) / 2;
        int left = get(2 * node, s, mid, ts, te);
        int right = get(2 * node + 1, mid + 1, e, ts, te);
        return left + right;
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        size = (1 << (((int)Math.ceil(Math.log(N) / Math.log(2))) + 1));
        tree = new int[size];
        lazy = new boolean[size];
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int o = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            if(o == 0) {
                update(s, t);
            } else {
                sb.append(query(s, t)).append('\n');
            }
        }
        System.out.print(sb);
        br.close();
    }
}