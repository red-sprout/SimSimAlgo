import java.io.*;
import java.util.*;

public class Main {
    static int[] dr = {-1, 0, 1, 0, -1, -1, 1, 1};
    static int[] dc = {0, -1, 0, 1, -1, 1, -1, 1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] map = new int[n][m];
        int[][] dist = new int[n][m];
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] v = new boolean[n][m];

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1) {
                    q.offer(new int[] {i, j, 0});
                    v[i][j] = true;
                }
            }
        }

        int res = 0;
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            for(int i = 0; i < 8; i++) {
                int nr = cur[0] + dr[i];
                int nc = cur[1] + dc[i];
                if(nr < 0 || nr >= n || nc < 0 || nc >= m || v[nr][nc]) continue;
                dist[nr][nc] = cur[2] + 1;
                res = Math.max(res, dist[nr][nc]);
                v[nr][nc] = true;
                q.offer(new int[] {nr, nc, dist[nr][nc]});
            }
        }

        System.out.println(res);
        br.close();
    }
}
