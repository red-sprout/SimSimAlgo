import java.io.*;
import java.util.*;

public class Main {
	static int N, M, K;
	static char[][] map;
	static String str;
	static int[][][] dp;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};
	
	static int dfs(int r, int c, int idx) {
		if(str.charAt(idx) != map[r][c]) return 0;
		if(idx == str.length() - 1) return 1;
		if(dp[r][c][idx] != -1) return dp[r][c][idx];
		
		dp[r][c][idx] = 0;
		for(int i = 0; i < 4; i++) {
			for(int j = 1; j <= K; j++) {
				int nr = r + j * dr[i];
				int nc = c + j * dc[i];
				if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
				dp[r][c][idx] += dfs(nr, nc, idx + 1);
			}
		}
		
		return dp[r][c][idx];
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new char[N][];
		for(int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		str = br.readLine();
		dp = new int[N][M][str.length()];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				for(int k = 0; k < str.length(); k++) {
					dp[i][j][k] = -1;
				}
			}
		}
		
		int answer = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				answer += dfs(i, j, 0);
			}
		}
		
		System.out.println(answer);
		br.close();
	}
}