import java.io.*;
import java.util.*;

public class Main {
	static class Edge implements Comparable<Edge>{
		int now;
		long cost;
		
		public Edge(int now, long cost) {
			this.now = now;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge e) {
			return Long.compare(this.cost, e.cost);
		}
	}
	
	static int n, m;
	static List<Edge>[] graph;
	
	static long dijkstra() {
		long[] dist = new long[n + 1];
		Arrays.fill(dist, Long.MAX_VALUE);
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		dist[1] = 0;
		pq.offer(new Edge(1, 0));
		
		while(!pq.isEmpty()) {
			Edge cur = pq.poll();
			int now = cur.now;
			long cost = cur.cost;
			long cnt = cur.cost / m;
			long order = cur.cost % m;
			if(now == n) return dist[n];
			
			for(Edge nxt : graph[now]) {
				int next = nxt.now;
				long norder = nxt.cost % m;
				
				if(order + 1 > norder) {
					long ncost = (cnt + 1) * m + norder;
					if(dist[next] > ncost) {
						dist[next] = ncost;
						pq.offer(new Edge(next, ncost));
					}
				} else {
					long ncost = cnt * m + norder;
					if(dist[next] > ncost) {
						dist[next] = ncost;
						pq.offer(new Edge(next, ncost));
					}
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
		graph = new List[n + 1];
		for(int i = 1; i <= n; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i = 1; i <= m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			graph[u].add(new Edge(v, i));
			graph[v].add(new Edge(u, i));
		}
		
		System.out.println(dijkstra());
		br.close();
	}
}