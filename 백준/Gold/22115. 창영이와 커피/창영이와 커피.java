import java.io.*;
import java.util.*;

public class Main {
    static int n, k;
    static int[] c;
    static int[][] dp;

    static final int MAX = 101;

    static int dfs(int cur, int sum) {
        if(sum > k) return MAX;
        if(sum == k) return 0;
        if(cur == n) return MAX;
        if(dp[cur][sum] != -1) return dp[cur][sum];

        int result = MAX;
        result = Math.min(result, dfs(cur + 1, sum));
        result = Math.min(result, dfs(cur + 1, sum + c[cur]) + 1);
        return dp[cur][sum] = result;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        dp = new int[n][k + 1];
        for(int i = 0; i < n; i++)  {
            Arrays.fill(dp[i], -1);
        }

        st = new StringTokenizer(br.readLine(), " ");
        c = new int[n];
        for(int i = 0; i < n; i++) {
            c[i] = Integer.parseInt(st.nextToken());
        }

        int res = dfs(0, 0);
        System.out.println(res < MAX ? res : -1);
        br.close();
    }
}
