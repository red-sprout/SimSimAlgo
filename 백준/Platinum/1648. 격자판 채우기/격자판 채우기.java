import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[][] dp;
    static final int MOD = 9901;

    static int dfs(int cur, int v) {
        if(cur == n * m && v == 0) return 1;
        if(cur >= n * m) return 0;
        if(dp[cur][v] != -1) return dp[cur][v];

        int res = 0;
        if((v & (1 << 0)) != 0) {
            res = (res + dfs(cur + 1, v >> 1)) % MOD;
        } else {
            res = (res + dfs(cur + 1, (v | 1 << m) >> 1)) % MOD;
            if(cur % m != m - 1 && (v & (1 << 1)) == 0) {
                res = (res + dfs(cur + 2, v >> 2)) % MOD;
            }
        }

        return dp[cur][v] = res;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        dp = new int[n * m][1 << m];
        for(int i = 0; i < n * m; i++) {
            Arrays.fill(dp[i], -1);
        }

        System.out.println(dfs(0, 0));
        br.close();
    }
}