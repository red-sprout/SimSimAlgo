import java.io.*;
import java.util.*;

public class Main {
    static int M, N;
    static int[][] graph;
    static StringTokenizer st;
    static int[] dr = {-1, 1, 0 ,0};
    static int[] dc = {0, 0, -1, 1};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st= new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        graph = new int[N][M];
        List<int[]> startPos = new ArrayList<>();
        boolean flag = true;

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                int value = Integer.parseInt(st.nextToken());
                graph[i][j] = value;
                if(value == 1) {
                    startPos.add(new int[] {i, j});
                } else if(value == 0) {
                    flag = false;
                }
            }
        }

        bfs(startPos);
        if(flag) {
            System.out.println(0);
        } else {
            System.out.println(check());
        }

        br.close();
    }

    private static void bfs(List<int[]> startPos) {
        Queue<int[]> q = new LinkedList<>();

        Iterator<int[]> iter = startPos.iterator();
        while(iter.hasNext()) {
            q.add(iter.next());
        }

        while(!q.isEmpty()) {
            int[] pos = q.poll();
            int pastRow = pos[0];
            int pastCol = pos[1];

            for(int i = 0; i < 4; i++) {
                int nowRow = pastRow + dr[i];
                int nowCol = pastCol + dc[i];

                if(nowRow < 0 || nowRow >= N) continue;
                if(nowCol < 0 || nowCol >= M) continue;
                if(graph[nowRow][nowCol] != 0) continue;

                q.add(new int[] {nowRow, nowCol});
                graph[nowRow][nowCol] = graph[pastRow][pastCol] + 1;
            }
        }
    }

    private static int check() {
        int ans = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                int value = graph[i][j];
                if(value == 0) {
                    return -1;
                }
                ans = Math.max(ans, value);
            }
        }
        return ans - 1;
    }
}