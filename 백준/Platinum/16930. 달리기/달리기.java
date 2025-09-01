import java.io.*;
import java.util.*;

public class Main {
    static int n, m, k;
    static char[][] mp;
    static final int[] dr = {-1, 0, 1, 0};
    static final int[] dc = {0, -1, 0, 1};

    static boolean isBlocked(int x, int y) {
        return (x < 0 || x >= n || y < 0 || y >= m || mp[x][y] == '#');
    }

    static int bfs(int x1, int y1, int x2, int y2) {
        int[][] dist = new int[n][m];
        for(int i = 0; i < n; ++i) {
            for(int j = 0; j < m; ++j) {
                dist[i][j] = -1;
            }
        }

        Queue<int[]> q = new ArrayDeque<>();
        dist[x1][y1] = 0;
        q.add(new int[] {x1, y1});

        while(!q.isEmpty()) {
            int[] sts = q.poll();
            int x = sts[0];
            int y = sts[1];

            if(x == x2 && y == y2) return dist[x][y];

            for(int i = 0; i < 4; ++i) {
                for(int j = 1; j <= k; ++j) {
                    int nx = x + j * dr[i];
                    int ny = y + j * dc[i];

                    if(isBlocked(nx, ny)) break;
                    if(dist[nx][ny] == -1) {
                        dist[nx][ny] = dist[x][y] + 1;
                        q.add(new int[] {nx, ny});
                    } else if(dist[nx][ny] <= dist[x][y]) {
                        break;
                    }
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        mp = new char[n][];

        for(int i = 0; i < n; ++i) {
            mp[i] = br.readLine().toCharArray();
        }

        int x1, y1, x2, y2;
        st = new StringTokenizer(br.readLine());
        x1 = Integer.parseInt(st.nextToken());
        y1 = Integer.parseInt(st.nextToken());
        x2 = Integer.parseInt(st.nextToken());
        y2 = Integer.parseInt(st.nextToken());

        System.out.println(bfs(x1 - 1, y1 - 1, x2 - 1, y2 - 1));
        br.close();
    }
}
