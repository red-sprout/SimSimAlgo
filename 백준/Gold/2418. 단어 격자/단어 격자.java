import java.io.*;
import java.util.*;

public class Main {
	static int h, w, l;
	static char[][] map;
	static long[][][] dp;
	static String str;
	static int[] dr = {-1, 0, 1, 0, -1, -1, 1, 1};
	static int[] dc = {0, -1, 0, 1, -1, 1, -1, 1};
	
	static long dfs(int r, int c, int idx) {
		if(str.charAt(idx) != map[r][c]) return 0;
		if(idx == l - 1) return 1;
		if(dp[r][c][idx] != -1) return dp[r][c][idx];
		dp[r][c][idx] = 0;
		for(int i = 0; i < 8; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if(nr < 0 || nr >= h || nc < 0 || nc >= w) continue;
			dp[r][c][idx] += dfs(nr, nc, idx + 1);
		}
		return dp[r][c][idx];
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		h = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());
		map = new char[h][];
		dp = new long[h][w][l];
		for(int i = 0; i < h; i++) {
			map[i] = br.readLine().toCharArray();
		}
		for(int i = 0; i < h; i++) {
			for(int j = 0; j < w; j++) {
				for(int k = 0; k < l; k++) {
					dp[i][j][k] = -1;
				}
			}
		}
		str = br.readLine();
		
		long answer = 0;
		for(int i = 0; i < h; i++) {
			for(int j = 0; j < w; j++) {
				answer += dfs(i, j, 0);
			}
		}
		
		System.out.println(answer);
		br.close();
	}
}