import java.io.*;
import java.util.*;

public class Main {
    static int N, M, originEmpty, answer;
    static int[][] map;
    static List<int[]> virusList;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, -1, 0, 1};
    static void selectVirus(int cnt, int start, int[] arr) {
        if(cnt == M) {
            bfs(arr);
            return;
        }
        for(int i = start; i < virusList.size(); i++) {
            arr[cnt] = i;
            selectVirus(cnt + 1, i + 1, arr);
        }
    }
    static void bfs(int[] arr) {
        boolean[][] visited = new boolean[N][N];
        Queue<int[]> q = new ArrayDeque<>();
        for(int p : arr) {
            int[] rc = virusList.get(p);
            int r = rc[0], c = rc[1];
            q.offer(new int[] {r, c, 0});
        }
        int empty = originEmpty;
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0], c = cur[1], t = cur[2];
            if(visited[r][c]) continue;
            visited[r][c] = true;
            if(map[r][c] == 0) empty--;
            if(empty == 0) {
                answer = Math.min(answer, t);
                return;
            }
            for(int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                int nt = t + 1;
                if(nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc] || map[nr][nc] == 1) continue;
                q.offer(new int[] {nr, nc, nt});
            }
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        originEmpty = 0;
        virusList = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 0) originEmpty++;
                if(map[i][j] == 2) virusList.add(new int[] {i, j});
            }
        }
        answer = Integer.MAX_VALUE;
        selectVirus(0, 0, new int[M]);
        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
        br.close();
    }
}