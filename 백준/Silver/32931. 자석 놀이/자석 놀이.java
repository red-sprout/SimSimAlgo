import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static long[][] arr, dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        arr = new long[n][2];
        dp = new long[n][2];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; ++i) {
            arr[i][0] = Long.parseLong(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; ++i) {
            arr[i][1] = Long.parseLong(st.nextToken());
        }

        dp[0][0] = Math.max(arr[0][0] + arr[0][1], arr[0][0]);
        dp[0][1] = arr[0][0] + arr[0][1];
        for(int i = 1; i < n; ++i) {
            dp[i][0] = Math.max(dp[i - 1][0] + arr[i][0], Math.max(dp[i - 1][0], dp[i - 1][1]) + arr[i][0] + arr[i][1]);
            dp[i][1] = Math.max(dp[i - 1][1] + arr[i][1], Math.max(dp[i - 1][0], dp[i - 1][1]) + arr[i][0] + arr[i][1]);
        }

        System.out.println(dp[n - 1][1]);
        br.close();
    }
}
