import java.io.*;
import java.util.*;

public class Main {
    static int n, m, a, b, c;
    static int[][] mp;

    static int getSum(int r1, int c1, int r2, int c2) {
        return mp[r2][c2] - mp[r1][c2] - mp[r2][c1] + mp[r1][c1];
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        mp = new int[n + 1][m + 1];

        st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        for(int i = 1; i <= n; ++i) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= m; ++j) {
                mp[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 1; i <= n; ++i) {
            for(int j = 1; j <= m; ++j) {
                mp[i][j] += mp[i - 1][j] + mp[i][j - 1] - mp[i - 1][j - 1];
            }
        }

        int res = 1_000_000_000;
        for(int i = 0; i <= n - a; ++i) {
            for(int j = 0; j <= m - b - c; ++j) {
                res = Math.min(res, getSum(i, j, i + a, j + b + c));
            }
        }

        for(int i = 0; i <= n - a - b; ++i) {
            for(int j = 0; j <= m - a - c; ++j) {
                res = Math.min(res, getSum(i, j, i + a, j + c) + getSum(i + a, j + c, i + a + b, j + a + c));
            }
        }

        for(int i = 0; i <= n - a - c; ++i) {
            for(int j = 0; j <= m - a - b; ++j) {
                res = Math.min(res, getSum(i, j, i + a, j + b) + getSum(i + a, j + b, i + a + c, j + a + b));
            }
        }

        System.out.println(res);
        br.close();
    }
}
