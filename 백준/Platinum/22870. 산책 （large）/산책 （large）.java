import java.io.*;
import java.util.*;

public class Main {
	static List<int[]>[] g;
	
	static class Edge implements Comparable<Edge>{
		int u; 
		long d;
		
		Edge(int u, long d) {
			this.u = u;
			this.d = d;
		}

		@Override
		public int compareTo(Edge o) {
			return Long.compare(this.d, o.d);
		}
	}
	
	static long dijkstra(int n, int[] v, int s, int e) {
		long[] dist = new long[n + 1];
		Arrays.fill(dist, Long.MAX_VALUE);
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		dist[s] = 0;
		pq.offer(new Edge(s, 0));
		while(!pq.isEmpty()) {
			Edge cur = pq.poll();
			if(v[cur.u] == -1) continue;
			for(int[] nxt : g[cur.u]) {
				if(v[nxt[0]] == -1) continue;
				if(dist[nxt[0]] > nxt[1] + cur.d) {
					dist[nxt[0]] = nxt[1] + cur.d;
					v[nxt[0]] = cur.u;
					if(nxt[0] != e) pq.offer(new Edge(nxt[0], dist[nxt[0]]));
				} else if(dist[nxt[0]] == nxt[1] + cur.d) {
					v[nxt[0]] = Math.min(v[nxt[0]], cur.u);
				}
			}
		}
		return dist[e];
	}
	
	static void updatePath(int[] v1, int[] v2, int s, int e) {
		int cur = v1[e];
		while(cur != s) {
			v2[cur] = -1;
			cur = v1[cur];
		}
	}
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        
        st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        g = new List[n + 1];
        for(int i = 1; i <= n; i++) {
        	g[i] = new ArrayList<>();
        }
        
        for(int i = 0; i < m; i++) {
        	st = new StringTokenizer(br.readLine(), " ");
        	int a = Integer.parseInt(st.nextToken());
        	int b = Integer.parseInt(st.nextToken());
        	int c = Integer.parseInt(st.nextToken());
        	g[a].add(new int[] {b, c});
        	g[b].add(new int[] {a, c});
        }
        
        st = new StringTokenizer(br.readLine(), " ");
        int s = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        
        int[] v1 = new int[n + 1];
        int[] v2 = new int[n + 1];
        
        long res = 0l;
        res += dijkstra(n, v1, e, s);
        updatePath(v1, v2, e, s);
        res += dijkstra(n, v2, e, s);
        
        System.out.println(res);
        br.close();
    }
}
