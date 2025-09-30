import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static boolean[][] map;
    static Queue<int[]> q;

    static final int[] dr = {-1, 0, 1, 0};
    static final int[] dc = {0, -1, 0, 1};

    static void bfs(int rr, int cc) {
        q.clear();
        q.add(new int[] {rr, cc});
        while(!q.isEmpty()) {
            int[] pos = q.poll();
            int r = pos[0], c = pos[1];

            if(map[r][c]) continue;
            map[r][c] = true;

            for(int d = 0; d < 4; ++d) {
                int nr = (r + dr[d] + n) % n;
                int nc = (c + dc[d] + m) % m;
                if(!map[nr][nc]) {
                    q.add(new int[] {nr, nc});
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new boolean[n][m];
        q = new ArrayDeque<>();

        for(int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; ++j) {
                map[i][j] = st.nextToken().charAt(0) == '1';
            }
        }

        int cnt = 0;
        for(int i = 0; i < n; ++i) {
            for(int j = 0; j < m; ++j) {
                if(!map[i][j]) {
                    ++cnt;
                    bfs(i, j);
                }
            }
        }

        System.out.println(cnt);
        br.close();
    }
}
