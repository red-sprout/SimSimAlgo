package year2024.month11.first;

import java.io.*;
import java.util.*;

public class Main_bj_2268_수들의합7 {
	static long[] tree;
	static void update(int node, int s, int e, int idx, int val) {
		if(idx < s || e < idx) return;
		if(s == e) {
			tree[node] = val;
			return;
		}
		int mid = (s + e) / 2;
		update(2 * node, s, mid, idx, val);
		update(2 * node + 1, mid + 1, e, idx, val);
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
		st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		tree = new long[4 * N];
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int q = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if(q == 0) {
				sb.append(get(1, 1, N, Math.min(a, b), Math.max(a, b))).append('\n');
			} else {
				update(1, 1, N, a, b);
			}
		}
		System.out.print(sb.toString());
		br.close();
	}
}
