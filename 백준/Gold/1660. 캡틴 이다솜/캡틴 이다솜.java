import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        List<Integer> tri = new ArrayList<>();
        for (int i = 1; ; i++) {
            int t = i * (i + 1) * (i + 2) / 6;;
            if (t > n) break;
            tri.add(t);
        }

        int[] dp = new int[n + 1];
        Arrays.fill(dp, 1_000_000_000);
        dp[0] = 0;
        for (int t : tri) {
            for (int j = t; j <= n; j++) {
                dp[j] = Math.min(dp[j], dp[j - t] + 1);
            }
        }

        System.out.println(dp[n]);
        br.close();
    }
}