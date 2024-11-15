package year2024.month11.first;

import java.io.*;

public class Main_bj_15722_빙글빙글스네일 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int x = 0;
		int y = 0;
		int[] dx = {0, 1, 0, -1};
		int[] dy = {1, 0, -1, 0};
		int time = Integer.parseInt(br.readLine());
		int t = 0;
		int dist = 0;
		while(true) {
			int d = (t / 2) + 1;
			if(time - dist < d) break;
			dist += d;
			x += dx[t % 4] * d;
			y += dy[t % 4] * d;
			t++;
		}
		int d = time - dist;
		x += dx[t % 4] * d;
		y += dy[t % 4] * d;
		System.out.println(x + " " + y);
		br.close();
	}
}
