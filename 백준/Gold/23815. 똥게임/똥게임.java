import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[][] dp;
    static String[][] order;

    static int dfs(int cur, int pass) {
        if(cur < 0) return 1;
        if(dp[cur][pass] != -1) return dp[cur][pass];
        int res = -100;

        String s1 = order[cur][0];
        char op1 = s1.charAt(0);
        int b1 = Integer.parseInt(s1.substring(1));
        int amt1 = calc(dfs(cur - 1, pass), op1, b1);
        if(amt1 > 0) res = Math.max(amt1, res);

        String s2 = order[cur][1];
        char op2 = s2.charAt(0);
        int b2 = Integer.parseInt(s2.substring(1));
        int amt2 = calc(dfs(cur - 1, pass), op2, b2);
        if(amt2 > 0) res = Math.max(amt2, res);

        if(pass == 0) {
            int amt3 = dfs(cur - 1, 1);
            if(amt3 > 0) res = Math.max(amt3, res);
        }

        return dp[cur][pass] = res;
    }

    static int calc(int a, char oper, int b) {
        switch(oper) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            default:
                return a / b;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        dp = new int[n][2];
        order = new String[n][2];

        for(int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            order[i][0] = st.nextToken();
            order[i][1] = st.nextToken();
        }

        for(int i = 0; i < n; ++i) {
            dp[i][0] = -1;
            dp[i][1] = -1;
        }

        int res = dfs(n - 1,0);
        System.out.println(res <= 0 ? "ddong game" : res);
        br.close();
    }
}
