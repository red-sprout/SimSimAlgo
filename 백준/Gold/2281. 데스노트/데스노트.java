import java.io.*;
import java.util.*;

public class Main {
	static int n, m;
	static int[] name;
	static int[][] dp;
	static int dfs(int cur, int col) {
		if(cur == n) return 0;
		if(dp[cur][col] != Integer.MAX_VALUE) return dp[cur][col];
		if(col + name[cur] <= m) 
			dp[cur][col] = Math.min(dp[cur][col], dfs(cur + 1, col + name[cur] + 1));
		dp[cur][col] = Math.min(dp[cur][col], dfs(cur + 1, name[cur] + 1) + (m - col + 1) * (m - col + 1));
		return dp[cur][col];
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		name = new int[n];
		for(int i = 0; i < n; i++) {
			name[i] = Integer.parseInt(br.readLine());
		}
		dp = new int[n][m + 2];
		for(int i = 0; i < n; i++) {
			Arrays.fill(dp[i], Integer.MAX_VALUE);
		}
		System.out.println(dfs(0, 0));
		br.close();
	}
}