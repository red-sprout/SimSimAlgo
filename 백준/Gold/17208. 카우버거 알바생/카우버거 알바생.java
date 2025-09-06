import java.io.*;
import java.util.*;

public class Main {
    static int n, m, k;
    static int[][][] dp;
    static List<int[]> orders;

    static int dfs(int cur, int cheese, int chips) {
        if(cheese > m || chips > k) return -1_000_000_000;
        if(cur == n) return 0;
        if(dp[cur][cheese][chips] != -1) return dp[cur][cheese][chips];

        int res = 0;
        int[] order = orders.get(cur);
        res = Math.max(res, dfs(cur + 1, cheese, chips));
        res = Math.max(res, dfs(cur + 1, cheese + order[0], chips + order[1]) + 1);
        return dp[cur][cheese][chips] = res;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        orders = new ArrayList<>();
        for(int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            orders.add(new int[] {x, y});
        }

        dp = new int[n][m + 1][k + 1];
        for(int i = 0; i < n; ++i) {
            for(int x = 0; x <= m; ++x) {
                for(int y = 0; y <= k; ++y) {
                    dp[i][x][y] = -1;
                }
            }
        }

        System.out.println(dfs(0, 0, 0));
        br.close();
    }
}
