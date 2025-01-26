import java.io.*;
import java.util.*;

public class Main {
	
	static class Edge implements Comparable<Edge>{
		int v;
		long d;
		
		public Edge(int v, long d) {
			this.v = v;
			this.d = d;
		}
		
		@Override
		public String toString() {
			return "[" + v + ", " + d + "]";
		}

		@Override
		public int compareTo(Edge e) {
			return Long.compare(this.d, e.d);
		}
	}
	
	static int n, m, k;
	static long[] dist;
	static List<Edge>[] g;
	static PriorityQueue<Edge> pq;
	
	static void dijkstra() {
		boolean[] visited = new boolean[n + 1];
		while(!pq.isEmpty()) {
			Edge cur = pq.poll();
			
			if(visited[cur.v]) continue;
			visited[cur.v] = true;
			
			for(Edge nxt : g[cur.v]) {
				if(!visited[nxt.v] && dist[nxt.v] > cur.d + nxt.d) {
					dist[nxt.v] = cur.d + nxt.d;
					pq.offer(new Edge(nxt.v, dist[nxt.v]));
				}
			}
		}
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
        	int c = Integer.parseInt(st.nextToken());
        	g[v].add(new Edge(u, c));
        }
        
        dist = new long[n + 1];
        pq = new PriorityQueue<>();
        Arrays.fill(dist, Long.MAX_VALUE);
        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < k; i++) {
        	int dest = Integer.parseInt(st.nextToken());
        	dist[dest] = 0;
        	pq.offer(new Edge(dest, 0));
        }
        
        dijkstra();
        int city = 0;
        long d = 0;
        for(int i = 1; i <= n; i++) {
        	if(dist[i] > d) {
        		city = i;
        		d = dist[i];
        	}
        }
        
        System.out.println(city);
        System.out.println(d);
        br.close();
    }
}
