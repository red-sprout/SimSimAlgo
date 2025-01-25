import java.io.*;
import java.util.*;

public class Main {

    static class Edge implements Comparable<Edge> {
        int loc;
        long dist;
        int cnt;

        public Edge(int loc, long dist, int cnt) {
            this.loc = loc;
            this.dist = dist;
            this.cnt = cnt;
        }

        public int compareTo(Edge o) {
            return Long.compare(this.dist, o.dist);
        }
    }
	
    static int n, m, k;
    static int[] cost;
    static long[][] dp;
    static List<Edge>[] g;

    static void dijkstra(int s) {
        for (int i = 1; i <= n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        dp[s][0] = 0;
        pq.offer(new Edge(s, 0, 0));

        while (!pq.isEmpty()) {
            Edge now = pq.poll();
            int node = now.loc, cnt = now.cnt;
            long dist = now.dist;

            if (dp[node][cnt] < dist) continue;

            for (Edge e : g[node]) {
                int nxt = e.loc;
                long ndist = e.dist;

                if (cnt + 1 <= n && dp[nxt][cnt + 1] > dist + ndist) {
                    dp[nxt][cnt + 1] = dist + ndist;
                    pq.offer(new Edge(nxt, dp[nxt][cnt + 1], cnt + 1));
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        cost = new int[k + 1];
        dp = new long[n + 1][n + 1];
        g = new List[n + 1];

        for (int i = 1; i <= n; i++) {
            g[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            g[a].add(new Edge(b, w, 1));
            g[b].add(new Edge(a, w, 1));
        }

        for (int i = 1; i <= k; i++) {
            cost[i] = cost[i - 1] + Integer.parseInt(br.readLine());
        }

        dijkstra(s);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= k; i++) {
            long res = Long.MAX_VALUE;
            for (int j = 0; j <= n; j++) {
                if (dp[d][j] != Integer.MAX_VALUE) {
                    res = Math.min(res, dp[d][j] + (long) j * cost[i]);
                }
            }
            sb.append(res).append('\n');
        }

        System.out.print(sb);
    }
}
