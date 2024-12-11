import java.io.*;
import java.util.*;

public class Main {
	static int n, m;
	static int[][] dp, path;
	static StringBuilder sb;
	static final int MAX = 10_000_001;
	
	static void init() {
		for(int k = 1; k <= n; k++) {
			for(int i = 1; i <= n; i++) {
				for(int j = 1; j <= n; j++) {
					if(i != j && dp[i][j] > dp[i][k] + dp[k][j]) {
						dp[i][j] = dp[i][k] + dp[k][j];
						path[i][j] = path[k][j];
					}
				}
			}
		}
	}
	
	static void solution() {
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= n; j++) {
				sb.append(dp[i][j] == MAX ? 0 : dp[i][j]).append(' ');
			}
			sb.append('\n');
		}
		Deque<Integer> stack = new ArrayDeque<>();
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= n; j++) {
				if(dp[i][j] == MAX) {
					sb.append(0).append('\n');
					continue;
				}
				int end = j;
				stack.push(end);
				while(i != path[i][end]) {
					end = path[i][end];
					stack.push(end);
				}
				stack.push(i);
				sb.append(stack.size());
				while(!stack.isEmpty()) {
					sb.append(' ').append(stack.pop());
				}
				sb.append('\n');
			}
		}
		System.out.print(sb);
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		dp = new int[n + 1][n + 1];
		path = new int[n + 1][n + 1];
		sb = new StringBuilder();
		for(int i = 0; i <= n; i++) {
			for(int j = 0; j <= n; j++) {
				dp[i][j] = MAX;
			}
		}
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			dp[a][b] = Math.min(dp[a][b], c);
			path[a][b] = a;
		}
		init();
		solution();
		br.close();
	}	
}