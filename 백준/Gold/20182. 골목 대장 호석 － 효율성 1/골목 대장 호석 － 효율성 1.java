import java.io.*;
import java.util.*;

public class Main {
    static class Edge implements Comparable<Edge> {
        int v;
        long c;
        long d;

        public Edge(int v, long c, long d) {
            this.v = v;
            this.c = c;
            this.d = d;
        }

        @Override
        public int compareTo(Edge e) {
            return Long.compare(this.c, e.c);
        }
    }

    static int n, m, a, b;
    static long c;
    static List<Edge>[] g;

    static long dijkstra() {
        long[] cost = new long[n + 1];
        boolean[] visited = new boolean[n + 1];
        Arrays.fill(cost, Long.MAX_VALUE);
        PriorityQueue<Edge> pq = new PriorityQueue<>();

        cost[a] = 0;
        pq.add(new Edge(a, cost[a], 0));
        while(!pq.isEmpty()) {
            Edge cur = pq.poll();

            if(visited[cur.v]) continue;
            visited[cur.v] = true;

            for(Edge nxt : g[cur.v]) {
                if(!visited[nxt.v]&& cost[nxt.v] >= Math.max(cur.c, nxt.d) && c >= cur.d + nxt.d) {
                    cost[nxt.v] = Math.max(cur.c, nxt.d);
                    pq.offer(new Edge(nxt.v, Math.max(cur.c, nxt.d), cur.d + nxt.d));
                }
            }
        }

        return cost[b] == Long.MAX_VALUE ? -1 : cost[b];
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        c = Long.parseLong(st.nextToken());
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
