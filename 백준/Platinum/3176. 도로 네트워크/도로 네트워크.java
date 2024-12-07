import java.io.*;
import java.util.*;

public class Main {
	static int N, size;
	static int[] depth;
	static int[][] dp, min, max;
	static List<int[]>[] graph;
	
	static void dfs(int node, int d) {
		depth[node] = d;
		for(int[] edge : graph[node]) {
			if(depth[edge[0]] == 0) {
				dfs(edge[0], d + 1);
				dp[0][edge[0]] = node;
				min[0][edge[0]] = edge[1];
				max[0][edge[0]] = edge[1];
			}
		}
	}
	
	static int[] query(int n1, int n2) {
		int node1, node2, dist;
		int mn = Integer.MAX_VALUE;
		int mx = Integer.MIN_VALUE;
		
		if(depth[n1] > depth[n2]) {
			node1 = n1;
			node2 = n2;
			dist = depth[n1] - depth[n2];
		} else {
			node1 = n2;
			node2 = n1;
			dist = depth[n2] - depth[n1];
		}
		
		for(int i = size; i >= 0; i--) {
			if((dist & (1 << i)) != 0) {
				mn = Math.min(mn, min[i][node1]);
				mx = Math.max(mx, max[i][node1]);
				node1 = dp[i][node1];
			}
		}
		
		if(node1 == node2) return new int[] {mn, mx};
		
		for(int i = size; i >= 0; i--) {
			if(dp[i][node1] != dp[i][node2]) {
				mn = Math.min(mn, min[i][node1]);
				mx = Math.max(mx, max[i][node1]);
				mn = Math.min(mn, min[i][node2]);
				mx = Math.max(mx, max[i][node2]);
				node1 = dp[i][node1];
				node2 = dp[i][node2];
			}
		}

		mn = Math.min(mn, Math.min(min[0][node1], min[0][node2]));
		mx = Math.max(mx, Math.max(max[0][node1], max[0][node2]));
		return new int[] {mn, mx};
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		depth = new int[N + 1];
		size = (int)(Math.ceil(Math.log(N) / Math.log(2))) + 1;
		dp = new int[size + 1][N + 1];
		min = new int[size + 1][N + 1];
		max = new int[size + 1][N + 1];
		graph = new List[N + 1];
		
		for(int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		for(int i = 0; i <= size; i++) {
			for(int j = 0; j <= N; j++) {
				min[i][j] = Integer.MAX_VALUE;
				max[i][j] = Integer.MIN_VALUE;
			}
		}
		
		for(int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			graph[a].add(new int[] {b, c});
			graph[b].add(new int[] {a, c});
		}
		
		dfs(1, 1);
		for(int i = 1; i <= size; i++) {
			for(int j = 1; j <= N; j++) {
				dp[i][j] = dp[i - 1][dp[i - 1][j]];
				min[i][j] = Math.min(min[i - 1][j], min[i - 1][dp[i - 1][j]]);
				max[i][j] = Math.max(max[i - 1][j], max[i - 1][dp[i - 1][j]]);
			}
		}
		
		int K = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int d = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int[] result = query(d, e);
			sb.append(result[0]).append(' ').append(result[1]).append('\n');
		}
		
		System.out.print(sb);
		br.close();
	}
}