import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] dep;
    static int[][] dp;
    static boolean[] isEnd;
    static List<Integer>[] g;

    public static void dfs(int cur, int d) {
        dep[cur] = d;
        dp[cur][0] = 0;
        dp[cur][1] = 1;
        for(int nxt : g[cur]) {
            if(dep[nxt] == 0) {
                dfs(nxt, d + 1);
                dp[cur][0] += dp[nxt][1];
                dp[cur][1] += Math.min(dp[nxt][0], dp[nxt][1]);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        n = Integer.parseInt(br.readLine());
        dep = new int[n + 1];
        dp = new int[n + 1][2];
        isEnd = new boolean[n + 1];
        g = new List[n + 1];

        for (int i = 1; i <= n; ++i) {
            dep[i] = 0;
            dp[i][0] = dp[i][1] = -1;
            g[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; ++i) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            g[u].add(v);
            g[v].add(u);
        }

        dfs(1, 1);
        System.out.println(Math.min(dp[1][0], dp[1][1]));
        br.close();
    }
}