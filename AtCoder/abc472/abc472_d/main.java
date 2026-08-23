import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int h = Integer.parseInt(st.nextToken());
		int w = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		char[][] mp = new char[h][w];
		boolean[] vh = new boolean[h];
		boolean[] vw = new boolean[w];

		for(int i = 0; i < h; i++) {
			String line = br.readLine();
			for(int j = 0; j < w; j++) {
				mp[i][j] = line.charAt(j);
				vh[i] = (mp[i][j] == '#') || vh[i];
				vw[j] = (mp[i][j] == '#') || vw[j];
			}
		}

		boolean[][] v = new boolean[h][w];
		Queue<int[]> q = new ArrayDeque<>();
		for(int i = 0; i < h; i++) {
			for(int j = 0; j < w; j++) {
				if(!vh[i] && !vw[j]) {
					q.offer(new int[] {i, j, 0});
					v[i][j] = true;
				}
			}
		}

		int[] dr = {-1, 1, 0, 0};
		int[] dc = {0, 0, -1, 1};
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			if(cur[2] == k) continue;
			for(int i = 0; i < 4; i++) {
				int nr = cur[0] + dr[i];
				int nc = cur[1] + dc[i];
				if(0 <= nr && nr < h && 0 <= nc && nc < w && mp[nr][nc] != '#' && !v[nr][nc]) {
					v[nr][nc] = true;
					q.offer(new int[] {nr, nc, cur[2] + 1});
				}
			}
		}

		int ans = 0;
		for(int i = 0; i < h; i++) {
			for(int j = 0; j < w; j++) {
				if(v[i][j]) ++ans;
			}
		}

		System.out.println(ans);
		br.close();
	}
}
