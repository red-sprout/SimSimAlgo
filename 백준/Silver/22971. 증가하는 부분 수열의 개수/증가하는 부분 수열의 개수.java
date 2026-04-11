import java.io.*;
import java.util.*;

public class Main {
    static final int CONST = 998_244_353;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        int[] dp = new int[n];

        for(int i = 0; i < n; ++i) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; ++i) {
            dp[i] = 1;
            for(int j = 0; j < i; ++j) {
                if(arr[j] < arr[i]) {
                    dp[i] = (dp[i] + dp[j]) % CONST;
                }
            }
            sb.append(dp[i]).append(' ');
        }

        System.out.println(sb.toString().trim());
        br.close();
    }
}