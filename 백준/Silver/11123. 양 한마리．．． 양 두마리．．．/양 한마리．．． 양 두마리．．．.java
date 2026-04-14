import java.io.*;
import java.util.*;

public class Main {
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, -1, 0, 1};

    static void bfs(int h, int w, char[][] mp, int r, int c, boolean[][] v, Queue<int[]> q) {
        v[r][c] = true;
        q.offer(new int[] {r, c});

        while(!q.isEmpty()) {
            int[] cur = q.poll();

            for(int d = 0; d < 4; ++d) {
                int nr = cur[0] + dr[d];
                int nc = cur[1] + dc[d];
                if(0 <= nr && nr < h && 0 <= nc && nc < w && mp[nr][nc] == '#' && !v[nr][nc]) {
                    v[nr][nc] = true;
                    q.offer(new int[] {nr, nc});
                }
            }
        }
    }

    static int solution(int h, int w, char[][] mp) {
        int res = 0;
        boolean[][] v = new boolean[h][w];
        Queue<int[]> q = new ArrayDeque<>();

        for(int i = 0; i < h; ++i) {
            for(int j = 0; j < w; ++j) {
                if(mp[i][j] == '#' && !v[i][j]) {
                    bfs(h, w, mp, i, j, v, q);
                    ++res;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while(t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            char[][] mp = new char[h][w];
            for(int i = 0; i < h; ++i) {
                String row = br.readLine();
                for(int j = 0; j < w; ++j) {
                    mp[i][j] = row.charAt(j);
                }
            }
            sb.append(solution(h, w, mp)).append('\n');
        }

        System.out.print(sb);
        br.close();
    }
}