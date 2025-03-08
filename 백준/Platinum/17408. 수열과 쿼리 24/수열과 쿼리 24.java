import java.io.*;
import java.util.*;

public class Main {
    static class Node {
        int val, idx;

        Node(int val, int idx) {
            this.val = val;
            this.idx = idx;
        }
    }

    static int n, sz;
    static int[] arr;
    static Node[] tree;

    static Node merge(Node n1, Node n2) {
        return n1.val > n2.val ? n1 : n2;
    }

    static Node init(int node, int s, int e) {
        if(s == e) {
            return tree[node] = new Node(arr[s], s);
        }
        int m  = (s + e) >> 1;
        Node left = init(node << 1, s, m);
        Node right = init(node << 1 | 1, m + 1, e);
        return tree[node] = merge(left, right);
    }

    static Node update(int node, int s, int e, int i, int v) {
        if(e < i || i < s) return tree[node];
        if(s == e) {
            tree[node].val = v;
            tree[node].idx = i;
            return tree[node];
        }
        int m = (s + e) >> 1;
        Node left = update(node << 1, s, m, i, v);
        Node right = update(node << 1 | 1, m + 1, e, i, v);
        return tree[node] = merge(left, right);
    }

    static void cmd1(int i, int v) {
        update(1, 1, n, i, v);
    }

    static Node get(int node, int s, int e, int ts, int te) {
        if(te < s || e < ts) return new Node(-1, -1);
        if(ts <= s && e <= te) return tree[node];
        int m = (s + e) >> 1;
        return merge(get(node << 1, s, m, ts, te), get(node << 1 | 1, m + 1, e, ts, te));
    }

    static int cmd2(int l, int r) {
        Node first = get(1, 1, n, l, r);
        Node left = get(1, 1, n, l, first.idx - 1);
        Node right = get(1, 1, n, first.idx + 1, r);
        return first.val + Math.max(left.val, right.val);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        n = Integer.parseInt(br.readLine());
        sz = 1 << (int) (Math.ceil(Math.log(n) / Math.log(2)) + 1);
        tree = new Node[sz];
        arr = new int[n + 1];
        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        init(1, 1, n);
        int cmd, i, v, l, r;
        int m = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while(m-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            cmd = Integer.parseInt(st.nextToken());
            if(cmd == 1) {
                i = Integer.parseInt(st.nextToken());
                v = Integer.parseInt(st.nextToken());
                cmd1(i, v);
            } else {
                l = Integer.parseInt(st.nextToken());
                r = Integer.parseInt(st.nextToken());
                sb.append(cmd2(l, r)).append('\n');
            }
        }

        System.out.print(sb);
        br.close();
    }
}