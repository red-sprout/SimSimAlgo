import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static final long MOD = 1_000_000_007;
    static List<Integer>[] tree;
    static int[] par;
    static long[] f;
    static long[][] dp;

    static void init() {
        f = new long[n + 1];
        dp = new long[n + 1][2];
        f[0] = 1;
        for(int i = 1; i <= n; ++i) {
            f[i] = f[i - 1] * i % MOD;
        }
        for(int i = 0; i <= n; ++i) {
            dp[i][0] = 1;
            dp[i][1] = 1;
        }
    }

    static long p(long a, long b) {
        if(b == 0) return 1;
        long half = p(a, b >> 1);
        long result = half * half % MOD;

        if((b & 1) == 1) {
            result = result * a % MOD;
        }
        return result;
    }

    static void dfs(int cur) {
        if(tree[cur].isEmpty()) return;
        long a = 0, b = 1, c = 1;
        for(int nxt : tree[cur]) {
            dfs(nxt);
            a = a + dp[nxt][0];
            b = (b * f[(int) dp[nxt][0]]) % MOD;
            c = (c * dp[nxt][1]) % MOD;
        }
        dp[cur][0] = a + 1;
        dp[cur][1] = (f[(int) a] * p(b, MOD - 2) % MOD) * c % MOD;
    }

    static long solution() {
        init();
        for(int i = 1; i <= n; ++i) {
            if(par[i] == 0) tree[0].add(i);
        }
        dfs(0);
        return dp[0][1];
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        tree = new List[n + 1];
        par = new int[n + 1];
        for(int i = 0; i <= n; ++i) {
            tree[i] = new ArrayList<>();
        }

        while(m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            tree[a].add(b);
            par[b] = a;
        }

        System.out.println(solution());
        br.close();
    }
}