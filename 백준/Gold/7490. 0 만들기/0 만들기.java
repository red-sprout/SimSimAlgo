import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder sb;

    static int calc(int n, char[] op) {
        int sum = 0;
        int cur = 1;
        int sign = 1;

        for (int i = 0; i < n - 1; i++) {
            int nxt = i + 2;

            if(op[i] == ' ') {
                cur = cur * 10 + nxt;
            } else {
                sum += sign * cur;

                if(op[i] == '+') sign = 1;
                else sign = -1;

                cur = nxt;
            }
        }

        sum += sign * cur;
        return sum;
    }

    static void print(int n, char[] op) {
        for(int i = 1; i < n; ++i) {
            sb.append(i).append(op[i - 1]);
        }
        sb.append(n).append('\n');
    }

    static void dfs(int idx, int n, char[] op) {
        if(idx == n - 1) {
            if(calc(n, op) == 0) {
                print(n, op);
            }
            return;
        }

        op[idx] = ' ';
        dfs(idx + 1, n, op);

        op[idx] = '+';
        dfs(idx + 1, n, op);

        op[idx] = '-';
        dfs(idx + 1, n, op);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int t = Integer.parseInt(br.readLine());
        sb = new StringBuilder();
        while(t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            char[] op = new char[n - 1];
            dfs(0, n, op);
            sb.append('\n');
        }

        System.out.println(sb.toString().trim());
        br.close();
    }
}