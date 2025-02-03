import java.io.*;
import java.util.*;

public class Main {
    static int r, c;
    static char[][] map;
    static boolean[][] visited;

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, -1, 0, 1};

    static int dfs(int row, int col, int v) {
        visited[row][col] = true;
        int result = 1;
        for(int i = 0; i < 4; i++) {
            int nr = row + dr[i];
            int nc = col + dc[i];
            if(nr < 0 || nr >= r || nc < 0 || nc >= c || visited[nr][nc]) continue;
            int nxt = 1 << (map[nr][nc] - 'A');
            if((v & nxt) != 0) continue;
            result = Math.max(result, dfs(nr, nc, (v | nxt  )) + 1);
        }

        visited[row][col] = false;
        return result;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine(), " ");
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        map = new char[r][];
        visited = new boolean[r][c];

        for(int i = 0; i < r; i++) {
            map[i] = br.readLine().toCharArray();
        }

        System.out.println(dfs(0, 0, 1 << (map[0][0] - 'A')));
        br.close();
    }
}