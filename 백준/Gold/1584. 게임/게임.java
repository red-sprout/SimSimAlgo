import java.io.*;
import java.util.*;

public class Main {
    static char[][] mp;
    static final int[] dx = {-1, 0, 1, 0};
    static final int[] dy = {0, -1, 0, 1};

    static void fill(char c, int x1, int y1, int x2, int y2) {
        int minX = Math.min(x1, x2);
        int maxX = Math.max(x1, x2);
        int minY = Math.min(y1, y2);
        int maxY = Math.max(y1, y2);
        for(int i = minX; i <= maxX; ++i) {
            for(int j = minY; j <= maxY; ++j) {
                mp[i][j] = c;
            }
        }
    }

    static int bfs() {
        boolean[][] vis = new boolean[501][501];
        int[][] dist = new int[501][501];
        Deque<int[]> deque = new ArrayDeque<>();
        deque.addLast(new int[] {0, 0, 0});
        for(int i = 0; i < 501; ++i) {
            for(int j = 0; j < 501; ++j) {
                dist[i][j] = 1_000_000_000;
            }
        }
        dist[0][0] = 0;
        while(!deque.isEmpty()) {
            int[] cur = deque.pollFirst();

            if(cur[0] == 500 && cur[1] == 500) return cur[2];

            if(vis[cur[0]][cur[1]]) continue;
            vis[cur[0]][cur[1]] = true;

            for(int i = 0; i < 4; ++i) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                if(0 <= nx && nx <= 500 && 0 <= ny && ny <= 500 && mp[nx][ny] != 'D') {
                    int d = mp[nx][ny] == 'd' ? 1 : 0;
                    if(!vis[nx][ny] && dist[nx][ny] > dist[cur[0]][cur[1]] + d) {
                        dist[nx][ny] = dist[cur[0]][cur[1]] + d;
                        if(d == 0) {
                            deque.addFirst(new int[] {nx, ny, dist[nx][ny]});
                        } else {
                            deque.addLast(new int[] {nx, ny, dist[nx][ny]});
                        }
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        mp = new char[501][501];

        int n = Integer.parseInt(br.readLine());
        int x1, y1, x2, y2;
        for(int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            x1 = Integer.parseInt(st.nextToken());
            y1 = Integer.parseInt(st.nextToken());
            x2 = Integer.parseInt(st.nextToken());
            y2 = Integer.parseInt(st.nextToken());
            fill('d', x1, y1, x2, y2);
        }

        int m = Integer.parseInt(br.readLine());
        for(int i = 0; i < m; ++i) {
            st = new StringTokenizer(br.readLine());
            x1 = Integer.parseInt(st.nextToken());
            y1 = Integer.parseInt(st.nextToken());
            x2 = Integer.parseInt(st.nextToken());
            y2 = Integer.parseInt(st.nextToken());
            fill('D', x1, y1, x2, y2);
        }

        System.out.println(bfs());
        br.close();
    }
}
