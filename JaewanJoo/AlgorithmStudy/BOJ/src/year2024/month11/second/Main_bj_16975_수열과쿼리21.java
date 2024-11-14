package year2024.month11.second;

import java.io.*;
import java.util.*;

public class Main_bj_16975_수열과쿼리21 {
	static int N, M, size;
	static long[] arr, tree, lazy;
	static void init() {
		init(1, 0, N - 1);
	}
	static void init(int node, int start, int end) {
		if(start == end) {
			tree[node] = arr[start];
			return;
		}
		int mid = (start + end) / 2;
		init(2 * node, start, mid);
		init(2 * node + 1, mid + 1, end);
		tree[node] = tree[2 * node] + tree[2 * node + 1]; 
	}
	static void update(int i, int j, long k) {
		updateTree(1, 0, N - 1, i - 1, j - 1, k);
	}
	static void updateLazy(int node, int start, int end) {
		if(lazy[node] == 0) return;
		tree[node] += (end - start + 1) * lazy[node];
		if(start != end) {
			lazy[2 * node] += lazy[node];
			lazy[2 * node + 1] += lazy[node];
		}
		lazy[node] = 0;
	}
	static void updateTree(int node, int start, int end, int ts, int te, long diff) {
		updateLazy(node, start, end);
		if(te < start || end < ts) return;
		if(ts <= start && end <= te) {
			tree[node] += (end - start + 1) * diff;
			if(start != end) {
				lazy[2 * node] += diff;
				lazy[2 * node + 1] += diff;
			}
			return;
		}
		int mid = (start + end) / 2;
		updateTree(2 * node, start, mid, ts, te, diff);
		updateTree(2 * node + 1, mid + 1, end, ts, te, diff);
		tree[node] = tree[2 * node] + tree[2 * node + 1];
	}
	static long query(int x) {
		return get(1, 0, N - 1, x - 1);
	}
	static long get(int node, int start, int end, int idx) {
		updateLazy(node, start, end);
		if(idx < start || end < idx) return 0;
		if(start == end) return tree[node];
		int mid = (start + end) / 2;
		long left = get(2 * node, start, mid, idx);
		long right = get(2 * node + 1, mid + 1, end, idx);
		return left + right;
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		size = 1 << ((int) Math.ceil(Math.log(N) / Math.log(2)) + 1);
		st = new StringTokenizer(br.readLine(), " ");
		arr = new long[N];
		tree = new long[size];
		lazy = new long[size];
		for(int i = 0; i < N; i++) {
			arr[i] = Long.parseLong(st.nextToken());
		}
		init();
		M = Integer.parseInt(br.readLine());
		for(int q = 0; q < M; q++) {
			st = new StringTokenizer(br.readLine(), " ");
			int num = Integer.parseInt(st.nextToken());
			if(num == 1) {
				int i = Integer.parseInt(st.nextToken());
				int j = Integer.parseInt(st.nextToken());
				long k = Long.parseLong(st.nextToken());
				update(i, j, k);
			} else {
				int x = Integer.parseInt(st.nextToken());
				sb.append(query(x)).append('\n');
			}
		}
		System.out.print(sb.toString());
		br.close();
	}
}
