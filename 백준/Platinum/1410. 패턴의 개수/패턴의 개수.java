import java.io.*;
import java.util.*;

public class Main {
    static int n, k, l;
    static final int MOD = 1_000_003;
    static char[][] pattern;
    static int[][] dp;

    static int dfs(int cur, int v) {
        if (Integer.bitCount(v) < k) return 0;
        if (cur == l) return Integer.bitCount(v) == k ? 1 : 0;
        if (dp[cur][v] != -1) return dp[cur][v];

        dp[cur][v] = 0;
        for (int i = 0; i < 26; i++) {
            int nv = 0;
            char c = (char) ('a' + i);
            for (int j = 0; j < n; j++) {
                if (pattern[j][cur] == c || pattern[j][cur] == '?') {
                    nv |= (1 << j);
                }
            }
            dp[cur][v] = (dp[cur][v] + dfs(cur + 1, v & nv)) % MOD;
        }
        return dp[cur][v];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        pattern = new char[n][];

        for (int i = 0; i < n; i++) {
            pattern[i] = br.readLine().toCharArray();
        }

        l = pattern[0].length;
        dp = new int[l][1 << n];
        for (int i = 0; i < l; i++) {
            Arrays.fill(dp[i], -1);
        }

        System.out.println(dfs(0, (1 << n) - 1));
        br.close();
    }
}
