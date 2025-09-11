import java.io.*;
import java.util.*;

public class Main {
    static int n, m, h;
    static List<Integer>[] info;
    static int[][][] dp;

    static int dfs(int cur, int idx, int sum) {
        if(sum == h) return 1;
        if(cur == n || sum > h) return 0;
        if(idx >= info[cur].size()) return dfs(cur + 1, 0, sum);
        if(dp[cur][idx][sum] != -1) return dp[cur][idx][sum];
        int res = 0;
        res = (res + dfs(cur, idx + 1, sum)) % 10007;
        res = (res + dfs(cur + 1, 0, sum + info[cur].get(idx))) % 10007;
        return dp[cur][idx][sum] = res;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        info = new List[n];
        for(int i = 0; i < n; ++i) {
            info[i] = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            while(st.hasMoreTokens()) {
                int x = Integer.parseInt(st.nextToken());
                info[i].add(x);
            }
        }

        dp = new int[n][m][h + 1];
        for(int i = 0; i < n; ++i) {
            for(int j = 0; j < m; ++j) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        System.out.println(dfs(0, 0, 0));
        br.close();
    }
}
