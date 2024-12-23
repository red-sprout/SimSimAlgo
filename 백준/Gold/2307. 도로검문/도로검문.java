import java.io.*;
import java.util.*;

public class Main {
	static class Edge implements Comparable<Edge> {
		int u, t;

		public Edge(int u, int t) {
			this.u = u;
			this.t = t;
		}

		@Override
		public String toString() {
			return "Edge [u=" + u + ", t=" + t + "]";
		}

		@Override
		public int compareTo(Edge e) {
			return this.t - e.t;
		}
	}
	
	static int N, M, first, max;
	static List<Edge>[] graph;
	static int[] edges;
	
	static boolean check(Edge cur, Edge nxt, int u1, int u2) {
		if(cur.u == u1 && nxt.u == u2) return false;
		if(cur.u == u2 && nxt.u == u1) return false;
		return true;
	}
	
	static int dijkstra(boolean init, int u, int v) {
		int[] dist = new int[N + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		boolean[] visited = new boolean[N + 1];
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		dist[1] = 0;
		pq.offer(new Edge(1, 0));
		while(!pq.isEmpty()) {
			Edge cur = pq.poll();
			if(cur.u == N) return dist[N];
			if(visited[cur.u]) continue;
			visited[cur.u] = true;
			for(Edge nxt : graph[cur.u]) {
				if(!visited[nxt.u] && check(cur, nxt, u, v) && dist[nxt.u] > dist[cur.u] + nxt.t) {
					dist[nxt.u] = dist[cur.u] + nxt.t;
					pq.offer(new Edge(nxt.u, dist[nxt.u]));
					if(init) edges[nxt.u] = cur.u;
				}
			}
		}
		return Integer.MAX_VALUE;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new List[N + 1];
		for(int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		edges = new int[N + 1];
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			graph[a].add(new Edge(b, t));
			graph[b].add(new Edge(a, t));
		}
		first = dijkstra(true, 0, 0);
		max = first;
		
		int v = N;
		while(v != 1) {
			int u = edges[v];
			max = Math.max(max, dijkstra(false, u, v));
			v = u;
		}
		
		System.out.println(max == Integer.MAX_VALUE ? -1 : max - first);
		br.close();
	}
}