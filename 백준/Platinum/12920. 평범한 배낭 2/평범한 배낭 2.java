import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        dp = new int[M + 1];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int v = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            int bin = 0;
            while((1 << (bin + 1)) <= k) bin++;
            k -= (1 << bin) - 1;
            for(int idx = 0; idx < bin; idx++) {
                int cnt = 1 << idx;
                for(int j = M; j >= v * cnt; j--) {
                    dp[j] = Math.max(dp[j], dp[j - v * cnt] + c * cnt);
                }
            }
            for(int j = M; j >= v * k; j--) {
                dp[j] = Math.max(dp[j], dp[j - v * k] + c * k);
            }
        }
        System.out.println(dp[M]);
        br.close();
    }
}