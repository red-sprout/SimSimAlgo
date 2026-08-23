import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int t = Integer.parseInt(br.readLine());
		while (t-- > 0) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			List<Integer>[] g = new List[n + 1];
			for (int i = 0; i < n + 1; i++) {
				g[i] = new ArrayList<>();
			}
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				g[a].add(b);
				g[b].add(a);
			}
			
			int[] c = new int[n + 1];
			int[] p = new int[n + 1];
			
			Arrays.fill(c, -1);
			Arrays.fill(p, -1);
			
			Queue<Integer> q = new ArrayDeque<>();
			c[1] = 0;
			q.offer(1);
			
			int u = -1;
			int v = -1;
			while (!q.isEmpty() && u == -1) {
				int cur = q.poll();
				for (int nxt : g[cur]) {
					if (c[nxt] == -1) {
						c[nxt] = c[cur] ^ 1;
						p[nxt] = cur;
						q.offer(nxt);
					} else if (c[nxt] == c[cur]) {
						u = cur;
						v = nxt;
						break;
					}
				}
			}
			
			if (u == -1) {
				sb.append(-1).append("\n");
				continue;
			}
			
			boolean[] vis = new boolean[n + 1];
			
			int cur = u;
			while (cur != -1) {
				vis[cur] = true;
				cur = p[cur];
			}
			
			int lca = v;
			while (!vis[lca]) {
				lca = p[lca];
			}
			
			List<Integer> left = new ArrayList<>();
			List<Integer> right = new ArrayList<>();
			cur = u;
			while (cur != lca) {
				left.add(cur);
				cur = p[cur];
			}
			
			cur = v;
			while (cur != lca) {
				right.add(cur);
				cur = p[cur];
			}
			
			left.add(lca);
			
			Collections.reverse(right);
			left.addAll(right);
			
			sb.append(left.size()).append("\n");
			
			for (int node : left) {
				sb.append(node).append(" ");
			}
			
			sb.append("\n");
		}
		
		System.out.print(sb);
		br.close();
	}
}
