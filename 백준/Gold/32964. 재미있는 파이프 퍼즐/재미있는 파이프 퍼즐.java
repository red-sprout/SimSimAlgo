import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static char[][] map;
    static int[] dr = {-1, 1, 0};
    static int[] dc = {0, 0, 1};
    static boolean flag;

    static void dfs(int r, int c, int d, boolean[][] vis) {
        if(flag || r < 0 || r > 1 || c < 0 || c > n - 1 || vis[r][c]) return;
        if(r == 1 && c == n - 1) {
            flag = true;
            return;
        }

        vis[r][c] = true;
        char p = map[r][c];
        if(p == 'L') {
            if(d == 0 || d == 1) d = 2;
            else if(r == 0) d = 1;
            else d = 0;
            dfs(r + dr[d], c + dc[d], d, vis);
        } else if(p == 'I') {
            dfs(r + dr[d], c + dc[d], d, vis);
        } else {
            dfs(r + dr[1], c + dc[1], 1, vis);
            dfs(r + dr[2], c + dc[2], 2, vis);
        }
        vis[r][c] = false;
    }

    static boolean solution() {
        flag = false;
        boolean[][] vis = new boolean[2][n];
        dfs(0, 0, 0, vis);
        return flag;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        map = new char[2][];
        map[0] = br.readLine().toCharArray();
        map[1] = br.readLine().toCharArray();

        System.out.println(solution() ? "YES" : "NO");
        br.close();
    }
}
