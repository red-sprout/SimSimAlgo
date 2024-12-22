import java.io.*;
import java.util.*;

public class Main {
	static class Edge implements Comparable<Edge> {
		int u, v, d;

		public Edge(int u, int v, int d) {
			this.u = u;
			this.v = v;
			this.d = d;
		}

		@Override
		public int compareTo(Edge e) {
			return this.d - e.d;
		}
	}
	
	static int N, M, val;
	static int[] b, parent;
	static List<Edge> edge, result;
	
	static int find(int x) {
		if(x == parent[x]) return x;
		return parent[x] = find(parent[x]);
	}
	
	static boolean isValid(List<Edge> list) {
		for(int i = 1; i <= N; i++) {
			parent[i] = i;
		}
		for(Edge e : list) {
			int u = find(e.u);
			int v = find(e.v);
			if(u == v) return false;
			parent[v] = u;
		}
		return true;
	}
	
	static void dfs(int idx, int total, List<Edge> list) {
		if(list.size() == N - 1) {
			if(isValid(list) && val > total) {
				val = total;
				result.clear();
				for(Edge e : list) result.add(e);
			}
			return;
		}
		if(idx == M) return;
		
		dfs(idx + 1, total, list);
		
		Edge e = edge.get(idx);
		if(b[e.u] > 0 && b[e.v] > 0) {
			b[e.u]--; b[e.v]--;
			list.add(e);
			dfs(idx + 1, total + e.d, list);
			list.remove(list.size() - 1);
			b[e.u]++; b[e.v]++;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		val = Integer.MAX_VALUE;
		b = new int[N + 1];
		parent = new int[N + 1];
		edge = new ArrayList<>();
		result = new ArrayList<>();
		
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 1; i <= N; i++) {
			b[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			edge.add(new Edge(u, v, d));
		}
		Collections.sort(edge);
		
		dfs(0, 0, new ArrayList<>());
		
		StringBuilder sb = new StringBuilder();
		if(val == Integer.MAX_VALUE) {
			sb.append("NO").append('\n');
		} else {
			sb.append("YES").append('\n');
			for(Edge e : result) {
				sb.append(e.u).append(' ').append(e.v).append('\n');
			}
		}
		
		System.out.print(sb);
		br.close();
	}
}