import java.io.*;
import java.util.*;

public class Main {
	static int m;
	static char[][] map;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};
	
	static int bfs(int r1, int r2) {
		if(map[r1][0] == '#' || map[r2][m - 1] == '#')
			return Integer.MAX_VALUE;
		
		Queue<int[]> q = new ArrayDeque<>();
		boolean[][] v = new boolean[2][m];
		q.offer(new int[] {r1, 0, 1});
		v[r1][0] = true;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			
			if(cur[0] == r2 && cur[1] == m - 1)
				return cur[2];
			
			for(int i = 0; i < 4; i++) {
				int nr = cur[0] + dr[i];
				int nc = cur[1] + dc[i];
				if(0 <= nr && nr < 2 && 0 <= nc && nc < m && !v[nr][nc] && map[nr][nc] == '.') {
					v[nr][nc] = true;
					q.offer(new int[] {nr, nc, cur[2] + 1});
				}
			}
		}
		
		return Integer.MAX_VALUE;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		m = Integer.parseInt(br.readLine());
		map = new char[2][m];
		int black = 0;
		for(int i = 0; i < 2; i++) {
			String row = br.readLine();
			for(int j = 0; j < m; j++) {
				map[i][j] = row.charAt(j);
				if(map[i][j] == '#') black++;
			}
		}
		
		int path = 2 * m - black;
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 2; j++) {
				path = Math.min(path, bfs(i, j));
			}
		}
		
		System.out.println(2 * m - black - path);
		br.close();
	}
}