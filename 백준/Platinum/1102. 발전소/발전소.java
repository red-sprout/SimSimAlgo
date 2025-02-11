import java.io.*;
import java.util.*;

public class Main {
    static int n, p;
    static int[][] cost;
    static int[][] dp;

    static final int MAX = 1_000_000_000;

    static int dfs(int cur, int v) {
        if(cur >= p) return 0;
        if(dp[cur][v] != -1) return dp[cur][v];
        int res = MAX;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(i != j && (v & (1 << i)) != 0 && (v & (1 << j)) == 0) {
                    res = Math.min(res, dfs(cur + 1, v | (1 << j)) + cost[i][j]);
                }
            }
        }
        return dp[cur][v] = res;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        n = Integer.parseInt(br.readLine());
        cost = new int[n][n];
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < n; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new int[n][1 << n];
        for(int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }

        int v = 0;
        int cnt = 0;
        String status = br.readLine();
        for(int i = 0; i < n; i++) {
            if(status.charAt(i) == 'Y') {
                v |= 1 << i;
                cnt++;
            }
        }

        p = Integer.parseInt(br.readLine());
        int res = dfs(cnt, v);

        System.out.println(res < MAX ? res : -1);
        br.close();
    }
}
