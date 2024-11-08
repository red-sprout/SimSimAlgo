package year2024.month11.second;

import java.io.*;
import java.util.*;

public class Main_bj_13901_로봇 {
	static int R, C, k;
	static boolean[][] map;
	static int[] s, order;
	static int[] dr = {0, -1, 1, 0, 0};
	static int[] dc = {0, 0, 0, -1, 1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(br.readLine());
		map = new boolean[R][C];
		for(int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			map[r][c] = true;
		}
		s = new int[2];
		st = new StringTokenizer(br.readLine(), " ");
		s[0] = Integer.parseInt(st.nextToken());
		s[1] = Integer.parseInt(st.nextToken());
		order = new int[4];
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < 4; i++) {
			order[i] = Integer.parseInt(st.nextToken());
		}
		int idx = 0;
		boolean isMovable = true;
		map[s[0]][s[1]] = true;
		while(isMovable) {
			isMovable = false;
			for(int d = 0; d < 4; d++) {
				int cur = order[(idx + d) % 4];
				int nr = s[0] + dr[cur];
				int nc = s[1] + dc[cur];
				if(nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
				if(!map[nr][nc]) {
					map[nr][nc] = true;
					s[0] = nr;
					s[1] = nc;
					idx = (idx + d) % 4;
					isMovable = true;
					break;
				}
			}
		}
		System.out.println(s[0] + " " + s[1]);
		br.close();
	}
}
