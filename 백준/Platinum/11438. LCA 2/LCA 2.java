import java.io.*;
import java.util.*;

public class Main {
	static int N, M, size;
	static int[] depth;
	static int[][] dp;
	static List<Integer>[] tree;
	static void dfs(int node, int d) {
		depth[node] = d;
		for(int next : tree[node]) {
			if(depth[next] == 0) {
				dfs(next, d + 1);
				dp[0][next] = node;
			}
		}
	}
	static int lca(int a, int b) {
		int d, node1, node2;
		if(depth[a] > depth[b]) {
			d = depth[a] - depth[b];
			node1 = a;
			node2 = b;
		} else {
			d = depth[b] - depth[a];
			node1 = b;
			node2 = a;
		}
		
		for(int i = size; i >= 0; i--) {
			if((d & (1 << i)) != 0) {
				node1 = dp[i][node1];
			}
		}
		
		if(node1 == node2) return node1;
		for(int i = size; i >= 0; i--) {
			if(dp[i][node1] != dp[i][node2]) {
				node1 = dp[i][node1];
				node2 = dp[i][node2];
			}
		}
		
		return dp[0][node1];
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
        
		N = Integer.parseInt(br.readLine());
		depth = new int[N + 1];
		size = (int)Math.ceil(Math.log(N) / Math.log(2));
		dp = new int[size + 1][N + 1];
		tree = new List[N + 1];
		for(int i = 1; i <= N; i++) {
			tree[i] = new ArrayList<>();
		}
        
		for(int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			tree[a].add(b);
			tree[b].add(a);
		}
        
		dfs(1, 1);
		for(int i = 1; i <= size; i++) {
			for(int j = 1; j <= N; j++) {
				dp[i][j] = dp[i - 1][dp[i - 1][j]];
			}
		}
        
		M = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			sb.append(lca(a, b)).append('\n');
		}
        
		System.out.print(sb);
		br.close();
	}
}