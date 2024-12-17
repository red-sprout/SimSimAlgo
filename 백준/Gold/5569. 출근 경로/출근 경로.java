import java.io.*;
import java.util.*;

public class Main {
	static int w, h;
	static int[][][][] dp;
	
	static final int[] dr = {1, 0};
	static final int[] dc = {0, 1};
	static final int CONST = 100_000;
	
	static int dfs(int r, int c, int d, int t) {
		if(r == w - 1 && c == h - 1) return 1;
		if(r < 0 || r >= w || c < 0 || c >= h) return 0;
		if(dp[r][c][d][t] != -1) return dp[r][c][d][t];
		dp[r][c][d][t] = 0;
		int nr = r, nc = c;
		nr = r + dr[d];
		nc = c + dc[d];
		dp[r][c][d][t] = (dp[r][c][d][t] + dfs(nr, nc, d, 0)) % CONST;
		if(t == 0) {
			nr = r + dr[1 - d];
			nc = c + dc[1 - d];
			dp[r][c][d][t] = (dp[r][c][d][t] + dfs(nr, nc, 1 - d, 1)) % CONST;
		}
		return dp[r][c][d][t];
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		st = new StringTokenizer(br.readLine(), " ");
		w = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		dp = new int[w][h][2][2];
		for(int i = 0; i < w; i++) {
			for(int j = 0; j < h; j++) {
				for(int k = 0; k < 2; k++) {
					for(int l = 0; l < 2; l++) {
						dp[i][j][k][l] = -1;
					}
				}
			}
		}
		System.out.println((dfs(0, 0, 0, 1) + dfs(0, 0, 1, 1)) % CONST);
		br.close();
	}
}