import java.io.*;
import java.util.*;

public class Main {
	static int n, m;
	static int[] dist;
	static int[][] g;
	
	static final int INF = 1_000_000_000;
	
	static int dijkstra(int s, int d) {
		Arrays.fill(dist, INF);
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
		boolean[] v = new boolean[n];
		
		dist[s] = 0;
		pq.offer(new int[] {s, 0});
		
		while(!pq.isEmpty()) {
			int[] cur = pq.poll();
			
			if(v[cur[0]]) continue;
			v[cur[0]] = true;
			
			for(int i = 0; i < n; i++) {
				if (!v[i] && dist[i] > cur[1] + g[cur[0]][i]) {
					dist[i] = cur[1] + g[cur[0]][i];
					pq.offer(new int[] {i, dist[i]});
				}
			}
		}
		
		return dist[d] == INF ? -1 : dist[d];
	}
	
	static void dfs(int cur) {
		for(int i = 0; i < n; i++) {
			if(dist[cur] == dist[i] + g[i][cur]) {
				g[i][cur] = INF;
				dfs(i);
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		while(true) {
			st = new StringTokenizer(br.readLine(), " ");
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			if(n == 0 && m == 0) break;
			
			g = new int[n][n];
			dist = new int[n];
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					g[i][j] = INF;
				}
			}
			
			st = new StringTokenizer(br.readLine(), " ");
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			for(int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				int p = Integer.parseInt(st.nextToken());
				g[u][v] = p;
			}
			
			int exist = dijkstra(s, d);
			if(exist == -1) {
				sb.append(-1).append('\n');
			} else {				
				dfs(d);
				sb.append(dijkstra(s, d)).append('\n');
			}
		}
		
		System.out.print(sb);
		br.close();
	}
}
