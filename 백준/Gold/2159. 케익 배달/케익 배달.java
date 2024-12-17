import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[][] pos;
	static long[][] dp;
	static int[] dr = {0, -1, 0, 1, 0};
	static int[] dc = {0, 0, -1, 0, 1};
	static int MAX = 100_000;
	
	static long dfs(int cur, int d) {
		if(cur == N) return 0;
		if(dp[cur][d] != Long.MAX_VALUE) return dp[cur][d];
		int r = pos[cur][0] + dr[d], c = pos[cur][1] + dc[d];
		for(int i = 0; i < 5; i++) {
			int nr = pos[cur + 1][0] + dr[i];
			int nc = pos[cur + 1][1] + dc[i];
			if(nr < 0 || nr >= MAX || nc < 0 || nc >= MAX) continue;
			dp[cur][d] = Math.min(dp[cur][d], dfs(cur + 1, i) + Math.abs(r - nr) + Math.abs(c - nc));
		}
		return dp[cur][d];
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		pos = new int[N + 1][2];
		dp = new long[N + 1][5];
		for(int i = 0; i < N + 1; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			pos[i][0] = Integer.parseInt(st.nextToken()) - 1;
			pos[i][1] = Integer.parseInt(st.nextToken()) - 1;
			Arrays.fill(dp[i], Long.MAX_VALUE);
		}
		System.out.println(dfs(0, 0));
		br.close();
	}
}