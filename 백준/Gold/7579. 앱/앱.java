import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int ans = Integer.MAX_VALUE;
    static int[] m, c;
    static int[][] dp;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        m = new int[N + 1];
        c = new int[N + 1];
        dp = new int[N + 1][10001];

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            m[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            c[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 1; i <= N; i++) {
            int memory = m[i];
            int cost = c[i];

            for(int j = 0; j <= 10000; j++) {
                if(i == 1) {
                    if(j >= cost) dp[i][j] = memory;
                } else {
                    if(j < cost) dp[i][j] = dp[i - 1][j];
                    else dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - cost] + memory);
                }

                if(dp[i][j] >= M) ans = Math.min(ans, j);
            }
        }

        System.out.println(ans);
        br.close();
    }
}