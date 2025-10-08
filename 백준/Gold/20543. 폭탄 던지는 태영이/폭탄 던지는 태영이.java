import java.io.*;
import java.util.*;

public class Main {
    static void print(int n, long[][] mp) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; ++i) {
            for(int j = 0; j < n; ++j) {
                sb.append(mp[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    static void solution(int n, int m, long[][] h) {
        long[][] rvImos = new long[n + 1][n + 1];
        long[][] mp = new long[n][n];

        rvImos[0][0] = h[0][0];
        for(int j = 1; j < n; ++j) {
            rvImos[0][j] = h[0][j] - h[0][j - 1];
        }
        rvImos[0][n] = - h[0][n - 1];
        for(int i = 1; i < n; ++i) {
            rvImos[i][0] = h[i][0] - h[i - 1][0];
            for(int j = 1; j < n; ++j) {
                rvImos[i][j] = h[i][j] - h[i][j - 1] - h[i - 1][j] + h[i - 1][j - 1];
            }
            rvImos[i][n] = - h[i][n - 1] + h[i - 1][n - 1];
        }
        rvImos[n][0] = - h[n - 1][0];
        for(int j = 1; j < n; ++j) {
            rvImos[n][j] = - h[n - 1][j] + h[n - 1][j - 1];
        }
        rvImos[n][n] = h[n - 1][n - 1];

        int d = m / 2;
        for(int i = 0; i < n - m + 1; ++i) {
            for(int j = 0; j < n - m + 1; ++j) {
                if(rvImos[i][j] < 0) {
                    long cnt = -rvImos[i][j];
                    mp[i + d][j + d] += cnt;
                    rvImos[i][j] += cnt;
                    rvImos[i + m][j] -= cnt;
                    rvImos[i][j + m] -= cnt;
                    rvImos[i + m][j + m] += cnt;
                }
            }
        }

        print(n, mp);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        long[][] h = new long[n][n];

        for(int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; ++j) {
                h[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solution(n, m, h);
        br.close();
    }
}
