import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] arr;
    static int[][] dp;
    static int dfs(int cur, int start) {
        if(cur == N) return 0;
        if(dp[cur][start] != -1) return dp[cur][start];
        int min = 10000, max = 0;
        dp[cur][start] = Math.max(dp[cur][start], dfs(cur + 1, start));
        for(int i = start; i <= cur; i++) {
            min = Math.min(min, arr[i]);
            max = Math.max(max, arr[i]);
        }
        dp[cur][start] = Math.max(dp[cur][start], dfs(cur + 1, cur + 1) + max - min);
        return dp[cur][start];
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        dp = new int[N][N];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                dp[i][j] = -1;
            }
        }
        System.out.println(dfs(0, 0));
        br.close();
    }
}