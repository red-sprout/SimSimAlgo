import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static long[] mTree, dTree, dist, ordered;
    static final long MOD = 1_000_000_007L;

    static void update(long[] tree, int node, int s, int e, int idx, long val) {
        if (idx < s || e < idx) return;
        if (s == e) {
            tree[node] = (tree[node] + val) % MOD;
            return;
        }
        int m = (s + e) >> 1;
        update(tree, node << 1, s, m, idx, val);
        update(tree, node << 1 | 1, m + 1, e, idx, val);
        tree[node] = (tree[node << 1] + tree[node << 1 | 1]) % MOD;
    }

    static long get(long[] tree, int node, int s, int e, int ts, int te) {
        if (ts > te) return 0;
        if (te < s || e < ts) return 0;
        if (ts <= s && e <= te) return tree[node];
        int m = (s + e) >> 1;
        long l = get(tree, node << 1, s, m, ts, te);
        long r = get(tree, node << 1 | 1, m + 1, e, ts, te);
        return (l + r) % MOD;
    }

    static int getIdx(long value) {
        return Arrays.binarySearch(ordered, value);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        mTree = new long[4 * n];
        dTree = new long[4 * n];
        dist = new long[n];
        ordered = new long[n];

        for (int i = 0; i < n; ++i) {
            dist[i] = Long.parseLong(br.readLine());
            ordered[i] = dist[i];
        }

        Arrays.sort(ordered);

        long ans = 1;

        for (int i = 0; i < n; ++i) {
            long d = dist[i];
            int idx = getIdx(d);

            if (i > 0) {
                long leftCnt = get(mTree, 1, 0, n - 1, 0, idx - 1);
                long leftSum = get(dTree, 1, 0, n - 1, 0, idx - 1);
                long rightCnt = get(mTree, 1, 0, n - 1, idx + 1, n - 1);
                long rightSum = get(dTree, 1, 0, n - 1, idx + 1, n - 1);

                long l = (d % MOD * leftCnt % MOD - leftSum + MOD) % MOD;
                long r = (rightSum - d % MOD * rightCnt % MOD + MOD) % MOD;
                long cost = (l + r) % MOD;

                ans = ans * cost % MOD;
            }

            update(mTree, 1, 0, n - 1, idx, 1);
            update(dTree, 1, 0, n - 1, idx, d % MOD);
        }

        System.out.println(ans);
    }
}