import java.io.*;
import java.util.*;

public class Main {
    static int n, m, res;
    static int[][] arr;
    static final int INF = 1_000_000_007;

    static void paintRow(int v) {
        for(int i = 0; i < n; ++i) {
            if((v & (1 << i)) == 0) continue;
            for(int j = 0; j < m; ++j) {
                arr[i][j] *= -1;
            }
        }
    }

    static void paintCol(int v) {
        for(int j = 0; j < m; ++j) {
            if((v & (1 << j)) == 0) continue;
            for(int i = 0; i < n; ++i) {
                arr[i][j] *= -1;
            }
        }
    }

    static boolean isP() {
        int sum = 0;
        for(int i = 0; i < n; ++i) {
            sum = 0;
            for(int j = 0; j < m; ++j) {
                sum += arr[i][j];
            }
            if(sum <= 0) return false;
        }
        for(int j = 0; j < m; ++j) {
            sum = 0;
            for(int i = 0; i < n; ++i) {
                sum += arr[i][j];
            }
            if(sum <= 0) return false;
        }
        return true;
    }

    static int calc(int vRow) {
        paintRow(vRow);
        int cnt = Integer.bitCount(vRow);
        int vCol = 0;
        for(int j = 0; j < m; ++j) {
            int sum = 0;
            for(int i = 0; i < n; ++i) {
                sum += arr[i][j];
            }
            if(sum <= 0) {
                vCol |= (1 << j);
                ++cnt;
            }
        }
        paintCol(vCol);
        boolean flag = isP();
        paintCol(vCol);
        paintRow(vRow);
        return flag ? cnt : INF;
    }

    static void dfs(int cur, int v) {
        if(cur == n) {
            res = Math.min(res, calc(v));
            return;
        }
        dfs(cur + 1, v);
        dfs(cur + 1, v | (1 << cur));
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n][m];

        for(int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; ++j) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        res = INF;
        dfs(0, 0);
        System.out.println(res == INF ? -1 : res);
        br.close();
    }
}
