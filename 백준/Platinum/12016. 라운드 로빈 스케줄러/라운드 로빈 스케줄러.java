import java.io.*;
import java.util.*;

public class Main {
    static class Seg {
        int n;
        long[] tree;

        Seg(int n) {
            this.n = n;
            this.tree = new long[4 * n];
        }

        long get(int node, int s, int e, int ts, int te) {
            if(te < s || e < ts) return 0;
            if(ts <= s && e <= te) return tree[node];
            int m = (s + e) >> 1;
            return get(node << 1, s, m, ts, te) + get(node << 1 | 1, m + 1, e, ts, te);
        }

        long get(int a, int b) {
            return get(1, 0, n - 1, a, b);
        }

        void update(int node, int s, int e, int idx, long val) {
            if(idx < s || e < idx) return;
            if(s == e) {
                tree[node] += val;
                return;
            }
            int m = (s + e) >> 1;
            update(node << 1, s, m, idx, val);
            update(node << 1 | 1, m + 1, e, idx, val);
            tree[node] = tree[node << 1] + tree[node << 1 | 1];
        }

        void update(int idx, long val) {
            update(1, 0, n - 1, idx, val);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        long[] arr = new long[n];
        long[] res = new long[n];
        Seg seg1 = new Seg(n);
        Seg seg2 = new Seg(n);

        st = new StringTokenizer(br.readLine());
        List<long[]> list = new ArrayList<>();
        for(int i = 0; i < n; ++i) {
            arr[i] = Integer.parseInt(st.nextToken());
            list.add(new long[] {arr[i], i});
        }

        Collections.sort(list, (o1, o2) -> o1[0] == o2[0] ? Long.compare(o1[1], o2[1]) : Long.compare(o1[0], o2[0]));

        for(long[] p : list) {
            long val = p[0];
            int idx = (int) p[1];
            seg1.update(idx, 1);
            seg2.update(idx, val);
            long l = seg1.get(0, idx - 1) * val - seg2.get(0, idx - 1);
            long r = seg1.get(idx + 1, n - 1) * (val - 1) - seg2.get(idx + 1, n - 1);
            res[idx] = (1 + idx + n * (arr[idx] - 1)) - (l + r);
        }

        StringBuilder sb = new StringBuilder();
        for(long e : res) {
            sb.append(e).append("\n");
        }

        System.out.print(sb);
        br.close();
    }
}
