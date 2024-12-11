import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static int[][] map;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};
	static int solution(int max, int min) {
		int left = -1;
		int right = max - min + 1;
		while(left + 1 < right) {
			int mid = (left + right) / 2;
			boolean flag = false;
			for(int i = min; i + mid <= max; i++) {
				if(i <= map[0][0] && map[0][0] <= i + mid) {
					if(flag = bfs(i + mid, i)) break;
				}
			}
			if(flag) {
				right = mid;
			} else {
				left = mid;
			}
		}
		return right;
	}
	static boolean bfs(int max, int min) {
		Queue<int[]> q = new ArrayDeque<>();
		boolean[][] visited = new boolean[n][n];
		q.offer(new int[] {0, 0});
		visited[0][0] = true;
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			if(cur[0] == n - 1 && cur[1] == n - 1) return true;
			for(int i = 0; i < 4; i++) {
				int nr = cur[0] + dr[i];
				int nc = cur[1] + dc[i];
				if(nr < 0 || nr >= n || nc < 0 || nc >= n || visited[nr][nc]) continue;
				if(min <= map[nr][nc] && map[nr][nc] <= max) {
					q.offer(new int[] {nr, nc});
					visited[nr][nc] = true;
				}
			}
		}
		return false;
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		int max = 0, min = 200;
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				max = Math.max(max, map[i][j]);
				min = Math.min(min, map[i][j]);
			}
		}
		System.out.println(solution(max, min));
		br.close();
	}
}
