import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static char[][] map;
	static int[] dr = {-1, 1, -1, 1}; // 좌상, 좌하, 우상, 우하
	static int[] dc = {-1, -1, 1, 1};
	static int bfs() {
		Deque<int[]> deque = new ArrayDeque<>();
		boolean[][][] visited = new boolean[N + 1][M + 1][2];
		deque.offer(new int[] {0, 0, 0});
		visited[0][0][0] = true;
		while(!deque.isEmpty()) {
			int[] cur = deque.pollFirst();
			if(cur[0] == N && cur[1] == M) return cur[2];
			for(int i = 0; i < 4; i++) {
				int nr = cur[0] + dr[i];
				int nc = cur[1] + dc[i];
				if(nr < 0 || nr > N || nc < 0 || nc > M) continue;
				boolean connected = isConnected(cur[0], cur[1], i);
				if(!visited[nr][nc][0] && connected) {
					visited[nr][nc][0] = true;
					deque.offerFirst(new int[] {nr, nc, cur[2]});
				} else if(!visited[nr][nc][1] && !connected) {
					visited[nr][nc][1] = true;
					deque.offerLast(new int[] {nr, nc, cur[2] + 1});
				}
			}
		}
		return -1;
	}
	static boolean isConnected(int r, int c, int d) {
		switch(d) {
		case 0: return map[r - 1][c - 1] == '\\';
		case 1: return map[r][c - 1] == '/';
		case 2: return map[r - 1][c] == '/';
		default: return map[r][c] == '\\';
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][];
		for(int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		int d = bfs();
		System.out.println(d == -1 ? "NO SOLUTION" : String.valueOf(d));
		br.close();
	}
}