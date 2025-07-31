import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] memory, cost;
    static int[][] dp;
    
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        memory = new int[N];
        cost = new int[N];
        
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < N; i++) {
            memory[i] = Integer.parseInt(st.nextToken());
        }
        
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < N; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
        }
        
        dp = new int[N + 1][10001];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        
        int ans = Integer.MAX_VALUE;
        for (int c = 0; c <= 10000; c++) {
            if (dfs(0, c) >= M) {
                ans = c;
                break;
            }
        }
        System.out.println(ans);
    }
    
    static int dfs(int idx, int maxCost) {
        if (idx == N) {
            return 0;
        }
        if (dp[idx][maxCost] != -1) {
            return dp[idx][maxCost];
        }
        
        int notTake = dfs(idx + 1, maxCost);
        int take = 0;
        if (cost[idx] <= maxCost) {
            take = dfs(idx + 1, maxCost - cost[idx]) + memory[idx];
        }
        
        return dp[idx][maxCost] = Math.max(notTake, take);
    }
}