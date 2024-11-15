package year2024.month11.second;

import java.io.*;
import java.util.*;

public class Main_bj_5419_북서풍 {
	static int n, size;
	static int[] order, tree;
	static void makeOrder(List<int[]> listX) {
		Collections.sort(listX, (o1, o2) -> o1[0] == o2[0] ? o2[1] - o1[1] : o1[0] - o2[0]);
		order = new int[n];
		List<int[]> listY = new ArrayList<>(n);			
		for(int i = 0; i < n; i++) {
			int[] cur = listX.get(i);
			cur[2] = i;
			listY.add(cur);
		}
		Collections.sort(listY, (o1, o2) -> o1[1] == o2[1] ? o1[0] - o2[0] : o2[1] - o1[1]);
		for(int i = 0; i < n; i++) {
			int[] cur = listY.get(i);
			order[cur[2]] = i;
		}
	}
	static void update(int node, int s, int e, int idx) {
		if(idx < s || e < idx) return;
		if(s == e) {
			tree[node]++;
			return;
		}
		int mid = (s + e) / 2;
		update(2 * node, s, mid, idx);
		update(2 * node + 1, mid + 1, e, idx);
		tree[node] = tree[2 * node] + tree[2 * node + 1];
	}
	static long get(int node, int s, int e, int ts, int te) {
		if(te < s || e < ts) return 0;
		if(ts <= s && e <= te) return tree[node];
		int mid = (s + e) / 2;
		long left = get(2 * node, s, mid, ts, te);
		long right = get(2 * node + 1, mid + 1, e, ts, te);
		return left + right;
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		for(int test = 0; test < T; test++) {
			n = Integer.parseInt(br.readLine());
			size = 1 << ((int) Math.ceil(Math.log(n) / Math.log(2)) + 1);
			List<int[]> listX = new ArrayList<>(n);
			tree = new int[size];
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				listX.add(new int[] {x, y, 0});
			}
			makeOrder(listX);
			long answer = 0;
			for(int i = 0; i < n; i++) {
				int idx = order[i];
				answer += get(1, 0, n - 1, 0, idx);
				update(1, 0, n - 1, idx);
			}
			sb.append(answer).append('\n');
		}
		System.out.print(sb.toString());
		br.close();
	}
}
