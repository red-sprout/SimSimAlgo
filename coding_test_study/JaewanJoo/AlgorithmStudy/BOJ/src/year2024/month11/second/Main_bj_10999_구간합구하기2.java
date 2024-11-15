package year2024.month11.second;

import java.io.*;
import java.util.*;

public class Main_bj_10999_구간합구하기2 {
	static int N, M, K, len, size;
	static long[] tree, lazy;
	static void update(int b, int c, long val) {
		int ts = Math.min(b - 1, c - 1);
		int te = Math.max(b - 1, c - 1);
		updateTree(1, 0, N - 1, ts, te, val);
	}
	static void updateLazy(int node, int s, int e) {
		if(lazy[node] == 0) return;
		tree[node] += (e - s + 1) * lazy[node];
		if(s != e) {
			lazy[2 * node] += lazy[node];
			lazy[2 * node + 1] += lazy[node];
		}
		lazy[node] = 0;
	}
	static void updateTree(int node, int s, int e, int ts, int te, long val) {
		updateLazy(node, s, e);
		if(te < s || e < ts) return;
		if(ts <= s && e <= te) {
			tree[node] += (e - s + 1) * val;
			if(s != e) {
				lazy[2 * node] += val;
				lazy[2 * node + 1] += val;
			}
			return;
		}
		int mid = (s + e) / 2;
		updateTree(2 * node, s, mid, ts, te, val);
		updateTree(2 * node + 1, mid + 1, e, ts, te, val);
		tree[node] = tree[2 * node] + tree[2 * node + 1];
	}
	static long query(int b, int c) {
		int ts = Math.min(b - 1, c - 1);
		int te = Math.max(b - 1, c - 1);
		return get(1, 0, N - 1, ts, te);
	}
	static long get(int node, int s, int e, int ts, int te) {
		updateLazy(node, s, e);
		if(te < s || e < ts) return 0;
		if(ts <= s && e <= te) return tree[node];
		int mid = (s + e) / 2;
		long left = get(2 * node, s, mid, ts, te);
		long right = get(2 * node + 1, mid + 1, e, ts, te);
		return left + right;
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		len = 1 << (int) Math.ceil(Math.log(N) / Math.log(2));
		size = len << 2;
		tree = new long[size];
		lazy = new long[size];
		for(int i = 1; i <= N; i++) {
			long val = Long.parseLong(br.readLine());
			update(i, i, val);
		}
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < M + K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			if(a == 1) {
				long d = Long.parseLong(st.nextToken());
				update(b, c, d);
			} else {
				sb.append(query(b, c)).append('\n');
			}
		}
		System.out.print(sb.toString());
		br.close();
	}
}
