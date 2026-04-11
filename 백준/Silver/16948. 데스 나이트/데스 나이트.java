import java.io.*;
import java.util.*;

public class Main {
    static int bfs(int n, int r1, int c1, int r2, int c2) {
        int[] dr = {-2, -2, 0, 0, 2, 2};
        int[] dc = {-1, 1, -2, 2, -1, 1};
        boolean[][] v = new boolean[n][n];
        Queue<int[]> q = new ArrayDeque<>();
        v[r1][c1] = true;
        q.offer(new int[] {r1, c1, 0});

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            if(cur[0] == r2 && cur[1] == c2) {
                return cur[2];
            }

            for(int i = 0; i < 6; ++i) {
                int nr = cur[0] + dr[i];
                int nc = cur[1] + dc[i];
                if(0 <= nr && nr < n && 0 <= nc && nc < n && !v[nr][nc]) {
                    v[nr][nc] = true;
                    q.offer(new int[] {nr, nc, cur[2] + 1});
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int r1 = Integer.parseInt(st.nextToken());
        int c1 = Integer.parseInt(st.nextToken());
        int r2 = Integer.parseInt(st.nextToken());
        int c2 = Integer.parseInt(st.nextToken());

        System.out.println(bfs(n, r1, c1, r2, c2));
        br.close();
    }
}