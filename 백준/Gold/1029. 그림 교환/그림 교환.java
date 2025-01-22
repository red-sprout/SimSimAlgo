import java.io.*;

public class Main {
    static int n;
    static int[][] cost;
    static int[][][] dp;

    static int dfs(int cur, int val, int visited) {
        if(dp[cur][val][visited] != -1) return dp[cur][val][visited];
        int result = 0;
        for(int i = 0; i < n; i++) {
            if(i != cur && (visited & (1 << i)) == 0 && cost[cur][i] >= val) {
                result = Math.max(result, dfs(i, cost[cur][i], visited | (1 << i)) + 1);
            }
        }
        return dp[cur][val][visited] = result;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        cost = new int[n][n];
        dp = new int[n][10][1 << n];
        for(int i = 0; i < n ; i++) {
            for(int j = 0; j < 10; j++) {
                for(int k = 0; k < (1 << n); k++) {
                    dp[i][j][k] = -1;
                }
            }
        }

        for(int i = 0; i < n; i++) {
            String line = br.readLine();
            for(int j = 0; j < n; j++) {
                cost[i][j] = line.charAt(j) - '0';
            }
        }

        System.out.println(dfs(0, 0 , 1) + 1);
        br.close();
    }
}
