package year2024.month10.fifth;

import java.io.*;
import java.util.*;

public class Main_bj_18809_Gaaaaaaaaaarden {
	static int N, M, G, R, answer;
	static int[][] map, greenDist, redDist;
	static List<int[]> list;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};
	static Queue<int[]> greenQ, redQ, lazy;
	static void solution(int cur, int gIdx, int rIdx, int[] green, int[] red) {
		if(cur == list.size()) {
			if(gIdx == G && rIdx == R) {
				initVisited();
				answer = Math.max(answer, countFlower(green, red));
			}
			return;
		}
		solution(cur + 1, gIdx, rIdx, green, red);
		if(gIdx < G) {
			green[gIdx] = cur;
			solution(cur + 1, gIdx + 1, rIdx, green, red);
		}
		if(rIdx < R) {
			red[rIdx] = cur;
			solution(cur + 1, gIdx, rIdx + 1, green, red);
		}
	}
	static void initVisited() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				greenDist[i][j] = -1;
				redDist[i][j] = -1;
			}
		}
	}
	static int countFlower(int[] green, int[] red) {
		int cnt = 0;
		for(int idx : green) {
			int[] pos = list.get(idx);
			greenQ.offer(pos);
			greenDist[pos[0]][pos[1]] = 0;
		}
		for(int idx : red) {
			int[] pos = list.get(idx);
			redQ.offer(pos);
			redDist[pos[0]][pos[1]] = 0;
		}
		while(!greenQ.isEmpty() || !redQ.isEmpty()) {
			int gSize = greenQ.size();
			for(int i = 0; i < gSize; i++) {
				int[] cur = greenQ.poll();
				for(int d = 0; d < 4; d++) {
					int nr = cur[0] + dr[d];
					int nc = cur[1] + dc[d];
					if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
					if(map[nr][nc] == 0 || greenDist[nr][nc] != -1) continue;
					greenDist[nr][nc] = greenDist[cur[0]][cur[1]] + 1;
					if(redDist[nr][nc] == -1) greenQ.offer(new int[] {nr, nc});
				}
			}
			int rSize = redQ.size();
			for(int i = 0; i < rSize; i++) {
				int[] cur = redQ.poll();
				for(int d = 0; d < 4; d++) {
					int nr = cur[0] + dr[d];
					int nc = cur[1] + dc[d];
					if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
					if(map[nr][nc] == 0 || redDist[nr][nc] != -1) continue;
					redDist[nr][nc] = redDist[cur[0]][cur[1]] + 1;
					if(greenDist[nr][nc] == -1) redQ.offer(new int[] {nr, nc});
				}
			}
		}
		System.out.println("G");
		for(int[] a : greenDist) System.out.println(Arrays.toString(a));
		System.out.println("R");
		for(int[] a : redDist) System.out.println(Arrays.toString(a));
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(greenDist[i][j] == -1 || redDist[i][j] == -1) continue;
				if(greenDist[i][j] == redDist[i][j]) cnt++;
			}
		}
		System.out.println(cnt);
		return cnt;
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		greenDist = new int[N][M];
		redDist = new int[N][M];
		list = new ArrayList<>();
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 2) list.add(new int[] {i, j});
			}
		}
		answer = 0;
		greenQ = new ArrayDeque<>();
		redQ = new ArrayDeque<>();
		lazy = new ArrayDeque<>();
		solution(0, 0, 0, new int[G], new int[R]);
		System.out.println(answer);
		br.close();
	}
}
