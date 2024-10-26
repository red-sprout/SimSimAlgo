package year2024.month10.fourth;

import java.io.*;
import java.util.*;

public class Main_bj_1275_커피숍2 {
	static int N, Q, len, size;
	static long[] arr, tree;
	static void update(int node, int s, int e, int idx, long val) {
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
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		len = 1 << (int) Math.ceil(Math.log(N) / Math.log(2));
		size = len << 1;
		arr = new long[N + 1];
		tree = new long[size];
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			update(1, 1, N, i, arr[i]);
		}
		for(int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			sb.append(get(1, 1, N, Math.min(x, y), Math.max(x, y))).append('\n');
			int a = Integer.parseInt(st.nextToken());
			long b = Long.parseLong(st.nextToken());
			arr[a] = b;
			update(1, 1, N, a, b);
		}
		System.out.println(sb.toString().trim());
		br.close();
	}
}
