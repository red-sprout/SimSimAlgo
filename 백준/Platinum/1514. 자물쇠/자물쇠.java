import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] a, b;
    static int[][][][] dp;

    static final int INF = 1_000_000_007;

    static int dfs(int cur, int x, int y, int z) {
        if(cur >= n) return 0;
        int res = dp[cur][x][y][z];
        if(res != -1) return res;

        res = INF;
        int l = (b[cur] - x + 10) % 10;

        int nx, ny, nz, d;
        nz = cur + 3 < n ? a[cur + 3] : 0;
        for(int i = 0; i <= l; ++i) {
            for(int j = 0; j <= i; ++j) {
                nx = (y + i + 10) % 10;
                ny = (z + j + 10) % 10;
                d = (l - i + 2) / 3 + (i - j + 2) / 3 + (j + 2) / 3;
                res = Math.min(res, d + dfs(cur + 1, nx, ny, nz));
            }
        }

        l = 10 - l;
        for(int i = 0; i <= l; ++i) {
            for(int j = 0; j <= i; ++j) {
                nx = (y - i + 10) % 10;
                ny = (z - j + 10) % 10;
                d = (l - i + 2) / 3 + (i - j + 2) / 3 + (j + 2) / 3;
                res = Math.min(res, d + dfs(cur + 1, nx, ny, nz));
            }
        }

        return dp[cur][x][y][z] = res;
    }

    static int solution() {
        dp = new int[n][10][10][10];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < 10; j++) {
                for(int k = 0; k < 10; k++) {
                    for(int l = 0; l < 10; l++) {
                        dp[i][j][k][l] = -1;
                    }
                }
            }
        }
        return dfs(0, a[0], a[1], a[2]);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        a = new int[n];
        b =  new int[n];

        String pwd = br.readLine();
        for(int i = 0; i < n; i++) {
            a[i] = pwd.charAt(i) - '0';
        }

        pwd = br.readLine();
        for(int i = 0; i < n; i++) {
            b[i] = pwd.charAt(i) - '0';
        }

        System.out.println(solution());
        br.close();
    }
}