import java.io.*;
import java.util.*;

public class Main {
    static int n, t, q, sz;
    static int[] tree, lazy;

    static void init() {
        tree = new int[sz << 1];
        lazy = new int[sz << 1];
        Arrays.fill(tree, 1);
    }

    static void updateLazy(int node, int s, int e) {
        if(lazy[node] == 0) return;
        tree[node] = lazy[node];
        if(s != e) {
            lazy[node << 1] = lazy[node];
            lazy[node << 1 | 1] = lazy[node];
        }
        lazy[node] = 0;
    }

    static void updateTree(int node, int s, int e, int x, int y, int z) {
        updateLazy(node, s, e);
        if(y < s || e < x) return;
        if(x <= s && e <= y) {
            int bit = 1 << z;
            tree[node] = bit;
            if(s != e) {
                lazy[node << 1] = bit;
                lazy[node << 1 | 1] = bit;
            }
            return;
        }
        int mid = (s + e) >> 1;
        updateTree(node << 1, s, mid, x, y, z);
        updateTree(node << 1 | 1, mid + 1, e, x, y, z);
        tree[node] = tree[node << 1] | tree[node << 1 | 1];
    }

    static void update(int x, int y, int z) {
        updateTree(1, 1, n, Math.min(x, y), Math.max(x, y), z - 1);
    }

    static int get(int node, int s, int e, int x, int y) {
        updateLazy(node, s, e);
        if(y < s || e < x) return 0;
        if(x <= s && e <= y) return tree[node];
        int mid = (s + e) >> 1;
        return get(node << 1, s, mid, x, y) | get(node << 1 | 1, mid + 1, e, x, y);
    }

    static int query(int x, int y) {
        return Integer.bitCount(get(1, 1, n, Math.min(x, y), Math.max(x, y)));
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());
        sz = 1 << (int) Math.ceil(Math.log(n) / Math.log(2));

        init();
        String cmd;
        int x, y, z;
        StringBuilder sb = new StringBuilder();
        while(q-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            cmd = st.nextToken();
            switch(cmd) {
                case "C":
                    x = Integer.parseInt(st.nextToken());
                    y = Integer.parseInt(st.nextToken());
                    z = Integer.parseInt(st.nextToken());
                    update(x, y, z);
                    break;
                case "Q":
                    x = Integer.parseInt(st.nextToken());
                    y = Integer.parseInt(st.nextToken());
                    sb.append(query(x, y)).append('\n');
                    break;
            }
        }

        System.out.print(sb);
        br.close();
    }
}