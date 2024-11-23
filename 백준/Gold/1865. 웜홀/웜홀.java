import java.io.*;
import java.util.*;

public class Main {
    static int N, M, W;
    static List<int[]> edges;
    static boolean bellmanFord() {
        int[] dist = new int[N + 1];
        Arrays.fill(dist, 1_000_000_000);
        dist[1] = 0;
        for(int i = 1; i < N; i++) {
            for(int[] e : edges) {
                dist[e[1]] = Math.min(dist[e[1]], dist[e[0]] + e[2]);
            }
        }
        for (int[] e : edges) {
            if (dist[e[1]] > dist[e[0]] + e[2]) return true;
        }
        return false;
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;
        int TC = Integer.parseInt(br.readLine());
        for(int test = 0; test < TC; test++) {
            st = new StringTokenizer(br.readLine(), " ");
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            edges = new ArrayList<>();
            for(int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int t = Integer.parseInt(st.nextToken());
                edges.add(new int[] {s, e, t});
                edges.add(new int[] {e, s, t});
            }
            for(int i = 0; i < W; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int t = Integer.parseInt(st.nextToken());
                edges.add(new int[] {s, e, -t});
            }
            sb.append(bellmanFord() ? "YES" : "NO").append('\n');
        }
        System.out.print(sb);
        br.close();
    }
}