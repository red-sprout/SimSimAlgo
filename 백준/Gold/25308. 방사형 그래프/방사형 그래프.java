import java.io.*;
import java.util.*;

public class Main {
	static int cnt;
	static int[] a, order;
	static final double R = Math.sqrt(2);
	static double[][] e = {
		{1, 0}, {1 / R, 1 / R}, {0, 1}, {-1 / R, 1 / R}, {-1, 0}, 
		{-1 / R, -1 / R}, {0, -1}, {1 / R, -1 / R}, {1, 0}, {1 / R, 1 / R}
	};
	
	static int ccw() {
		for(int i = 0; i < 8; i++) {
			int a1 = order[i], a2 = order[i + 1], a3 = order[i + 2];
			double x1 = a2 * e[i + 1][0] - a1 * e[i][0];
			double y1 = a2 * e[i + 1][1] - a1 * e[i][1];
			double x2 = a3 * e[i + 2][0] - a2 * e[i + 1][0];
			double y2 = a3 * e[i + 2][1] - a2 * e[i + 1][1];
			if(x1 * y2 - x2 * y1 < 0) return 0;
		}
		return 1;
	}
	
	static void dfs(int idx, int v) {
		if(idx == 8) {
			order[8] = order[0];
			order[9] = order[1];
			cnt += ccw();
			return;
		}
		for(int i = 0; i < 8; i++) {
			if((v & (1 << i)) != 0) continue;
			order[idx] = a[i];
			dfs(idx + 1, v | (1 << i));
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		a = new int[8];
		order = new int[10];
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0 ; i < 8; i++) {
			a[i] = Integer.parseInt(st.nextToken());
		}
		cnt = 0;
		dfs(0, 0);
		System.out.println(cnt);
		br.close();
	}
}