import java.io.*;
import java.util.*;

public class Main {
	static int n, m, k;
	static Queue<Long>[] resQ;
	static List<Pair>[] g;
	
	static class Pair implements Comparable<Pair> {
		int f; long s;

		public Pair(int f, long s) {
			this.f = f;
			this.s = s;
		}

		@Override
		public String toString() {
			return "[" + f + ", " + s + "]";
		}

		@Override
		public int compareTo(Pair p) {
			return Long.compare(this.s, p.s);
		}
	}
	
	static void dijkstra() {
		PriorityQueue<Pair> pq = new PriorityQueue<>();
		resQ[1].offer(0l);
		pq.offer(new Pair(1, 0));
		
		while(!pq.isEmpty()) {
			Pair p = pq.poll();
			int cur = p.f;
			long cost = p.s;
			
			for(Pair edge : g[cur]) {
				int nxt = edge.f;
				long d = edge.s;
				
				if(resQ[nxt].size() == k) {
					if(resQ[nxt].peek() > cost + d) {
						resQ[nxt].poll();
						resQ[nxt].offer(cost + d);
						pq.offer(new Pair(nxt, cost + d));
					}
				} else {
					resQ[nxt].offer(cost + d);
					pq.offer(new Pair(nxt, cost + d));
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
		resQ = new Queue[n + 1];
		g = new List[n + 1];
		
		for(int i = 1; i <= n; i++) {
			resQ[i] = new PriorityQueue<>(Collections.reverseOrder());
			g[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			g[a].add(new Pair(b, c));
		}
		
		dijkstra();
		
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i <= n; i++) {
			if(resQ[i].size() < k) {
				sb.append(-1).append('\n');
			} else {				
				sb.append(resQ[i].peek()).append('\n');
			}
		}
		
		System.out.print(sb);
		br.close();
	}
}