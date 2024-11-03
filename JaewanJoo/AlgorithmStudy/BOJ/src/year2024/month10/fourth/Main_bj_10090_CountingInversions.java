package year2024.month10.fourth;

import java.io.*;
import java.util.*;

public class Main_bj_10090_CountingInversions {
	static int n;
	static long[] tree;
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
		StringTokenizer st = null;
		n = Integer.parseInt(br.readLine());
		tree = new long[4 * n];
		long answer = 0;
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			int idx = Integer.parseInt(st.nextToken());
			answer += idx - 1 - get(1, 1, n, 1, idx - 1);
			update(1, 1, n, idx);
		}
		System.out.println(answer);
		br.close();
	}
}
