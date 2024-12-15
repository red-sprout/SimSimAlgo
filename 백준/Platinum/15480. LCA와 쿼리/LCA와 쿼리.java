import java.io.*;
import java.util.*;

public class Main {
	static int N, M, size;
	static int[] depth;
	static int[][] dp;
	static List<Integer>[] tree;
	
	static void dfs(int cur, int d) {
		depth[cur] = d;
		for(int nxt : tree[cur]) {
			if(depth[nxt] == 0) {
				dfs(nxt, d + 1);
				dp[0][nxt] = cur;
			}
		}
	}
	
	static int lca(int n1, int n2) {
		int d, node1, node2;
		if(depth[n1] > depth[n2]) {
			d = depth[n1] - depth[n2]; node1 = n1; node2 = n2;
		} else {
			d = depth[n2] - depth[n1]; node1 = n2; node2 = n1;
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
	
	static int solution(int r, int u, int v) {
		int ru = lca(r, u), rv = lca(r, v), uv = lca(u, v);
		int ans = ru;
		ans = depth[ans] >= depth[rv] ? ans : rv;
		ans = depth[ans] >= depth[uv] ? ans : uv;
		return ans;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		size = (int) Math.ceil(Math.log(N) / Math.log(2));
		depth = new int[N + 1];
		dp = new int[size + 1][N + 1];
		tree = new List[N + 1];
		for(int i = 1; i <= N; i++) {
			tree[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			tree[u].add(v);
			tree[v].add(u);
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
			st = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(st.nextToken());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			sb.append(solution(r, u, v)).append('\n');
		}
		
		System.out.print(sb);
		br.close();
	}
}