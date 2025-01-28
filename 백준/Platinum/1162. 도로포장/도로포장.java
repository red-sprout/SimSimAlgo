import java.io.*;
import java.util.*;

public class Main {
    static class Edge implements Comparable<Edge> {
        int u, c;
        long d;

        public Edge(int u, int c, long d) {
            this.u = u;
            this.c = c;
            this.d = d;
        }

        @Override
        public String toString() {
            return "[" + u + ", " + c + ", " + d + "]";
        }

        @Override
        public int compareTo(Edge e) {
            return Long.compare(this.d, e.d);
        }
    }

    static int n, m, k;
    static List<Edge>[] g;

    static long dijkstra() {
        long[][] dist = new long[n + 1][k + 1];
        boolean[][] visited = new boolean[n + 1][k + 1];
        PriorityQueue<Edge> pq = new PriorityQueue<>();

        for(int i = 1; i <= n; i++) {
            Arrays.fill(dist[i], Long.MAX_VALUE);
        }
        dist[1][0] = 0;
        pq.offer(new Edge(1, 0, dist[1][0]));

        while(!pq.isEmpty()) {
            Edge cur = pq.poll();

            if(cur.u == n) return cur.d;
            if(visited[cur.u][cur.c]) continue;
            visited[cur.u][cur.c] = true;

            for(Edge nxt : g[cur.u]) {
                if(!visited[nxt.u][cur.c] && dist[nxt.u][cur.c] > dist[cur.u][cur.c] + nxt.d) {
                    dist[nxt.u][cur.c] = dist[cur.u][cur.c] + nxt.d;
                    pq.offer(new Edge(nxt.u, cur.c, dist[nxt.u][cur.c]));
                }
                if(cur.c != k && !visited[nxt.u][cur.c + 1] && dist[nxt.u][cur.c + 1] > dist[cur.u][cur.c]) {
                    dist[nxt.u][cur.c + 1] = dist[cur.u][cur.c];
                    pq.offer(new Edge(nxt.u, cur.c + 1, dist[nxt.u][cur.c + 1]));
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        g = new List[n + 1];
        for(int i = 1; i <= n; i++) {
            g[i] = new ArrayList<>();
        }

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            long d = Integer.parseInt(st.nextToken());
            g[u].add(new Edge(v, 0, d));
            g[v].add(new Edge(u, 0, d));
        }

        System.out.println(dijkstra());
        br.close();
    }
}
