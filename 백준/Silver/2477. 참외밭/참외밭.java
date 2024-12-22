import java.io.*;
import java.util.*;

public class Main {
	static int[][] ccw = {
		{0, 0, 0, 0, 0},
		{0, 0, 0, -1, 1},
		{0, 0, 0, 1, -1},
		{0, 1, -1, 0, 0},
		{0, -1, 1, 0, 0},
	};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int K = Integer.parseInt(br.readLine());
		List<int[]> list = new ArrayList<>();
		for(int i = 0; i < 6; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int d = Integer.parseInt(st.nextToken());
			int l = Integer.parseInt(st.nextToken());
			list.add(new int[] {d, l});
		}
		list.add(list.get(0));
		int area = 0, minus = 0;
		for(int i = 0; i < 6; i++) {
			int[] p = list.get(i);
			int[] n = list.get(i + 1);
			if(ccw[p[0]][n[0]] < 0) {
				minus = p[1] * n[1];
			} else {
				area = Math.max(area, p[1] * n[1]);
			}
		}
		System.out.println(K * (area - minus));
		br.close();
	}
}