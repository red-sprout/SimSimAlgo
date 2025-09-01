import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[][] dp;
    static int[][] dist;
    static final int INF = -1_000_000_000;

    static int dfs(int cur, int vis) {
        if(vis == (1 << n) - 1) {
            if(dist[cur][0] == -1) return INF;
            return dist[cur][0];
        }

        if(dp[cur][vis] != -1) return dp[cur][vis];

        int res = INF;
        for(int i = 1; i <= n; ++i) {
            if(dist[cur][i] == -1 || (vis & (1 << (i - 1))) != 0) continue;
            res = Math.max(res, dfs(i, vis | (1 << (i - 1))) + dist[cur][i]);
        }
        return dp[cur][vis] = res;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        dp = new int[n + 1][1 << n];
        dist = new int[n + 1][n + 1];
        for(int i = 0; i <= n; ++i) {
            Arrays.fill(dp[i], -1);
            Arrays.fill(dist[i], -1);
        }

        for(int i = 0; i < m; ++i) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            dist[u][v] = Math.max(dist[u][v], d);
        }

        int res = dfs(0, 0);
        System.out.println(res < 0 ? -1 : res);
        br.close();
    }
}
