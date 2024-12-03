import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static char[][] map;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int[][] mirror = {{3, 2, 1, 0}, {1, 0, 3, 2}};
    static int bfs(int[] door) {
        Deque<int[]> q = new ArrayDeque<>();
        boolean[][][] visited = new boolean[N][N][4];
        for(int i = 0; i < 4; i++) {
            q.offerFirst(new int[] {door[0], door[1], 0, i});
        }
        while(!q.isEmpty()) {
            int[] cur = q.pollFirst();
            int r = cur[0], c = cur[1], d = cur[2], t = cur[3];
            if(r == door[2] && c == door[3]) return d;
            int nr = r + dr[t], nc = c + dc[t];
            if(visited[r][c][t]) continue;
            visited[r][c][t] = true;
            if(0 <= nr && nr < N && 0 <= nc && nc < N && map[nr][nc] != '*' && !visited[nr][nc][t]) {
                q.offerFirst(new int[] {nr, nc, d, t});
                int nt = t;
                if(map[nr][nc] == '!') {
                    nt = mirror[0][t];
                    q.offerLast(new int[] { nr, nc, d + 1, nt });
                    nt = mirror[1][t];
                    q.offerLast(new int[] { nr, nc, d + 1, nt });
                }
            }
        }
        return -1;
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        int[] door = new int[4];
        int idx = 0;
        for(int i = 0; i < N; i++) {
            String row = br.readLine();
            for(int j = 0; j < N; j++) {
                map[i][j] = row.charAt(j);
                if(map[i][j] == '#') {
                    door[idx++] = i;
                    door[idx++] = j;
                    map[i][j] = '.';
                }
            }
        }
        System.out.println(bfs(door));
        br.close();
    }
}