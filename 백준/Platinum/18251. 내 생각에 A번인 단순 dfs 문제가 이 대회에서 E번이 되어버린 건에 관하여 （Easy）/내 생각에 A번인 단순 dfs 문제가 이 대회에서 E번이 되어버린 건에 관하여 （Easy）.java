import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static long res = -1_000_000_001;
    static long[] tree;
    static List<Integer> order;

    static int h(int idx) {
        return (int) (Math.log(idx) / Math.log(2));
    }

    static void dfs(int cur) {
        if(cur > n) return;
        dfs(cur << 1);
        order.add(cur);
        dfs(cur << 1 | 1);
    }

    static void solution() {
        if(res < 0) return;

        order = new ArrayList<>();
        dfs(1);
        
        int H = h(n);
        for(int i = 0; i <= H; ++i) {
            for(int j = i; j <= H; ++j) {
                long sum = 0;
                for(int idx : order) {
                    long val = tree[idx];
                    int y = h(idx);
                    if(y < i || y > j) continue;
                    sum = Math.max(0, sum + val);
                    res = Math.max(res, sum);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        tree = new long[n + 1];

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; ++i) {
            tree[i] = Integer.parseInt(st.nextToken());
            res = Math.max(res, tree[i]);
        }

        solution();
        System.out.println(res);
        br.close();
    }
}
