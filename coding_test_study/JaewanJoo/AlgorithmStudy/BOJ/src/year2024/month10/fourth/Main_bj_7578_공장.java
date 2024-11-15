package year2024.month10.fourth;

import java.io.*;
import java.util.*;

public class Main_bj_7578_공장 {
	static int N, len, size;
	static int[] A;
	static long[] tree;
	static Map<Integer, Integer> map;
	static void update(int node, int s, int e, int idx) {
		if(idx < s || e < idx) return;
		tree[node] += 1;
		if(s == e) return;
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
		N = Integer.parseInt(br.readLine());
		A = new int[N];
		map = new HashMap<>();
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
			map.put(A[i], i);
		}
		len = 1 << (int) Math.ceil(Math.log(N) / Math.log(2));
		size = len << 1;
		tree = new long[size];
		st = new StringTokenizer(br.readLine(), " ");
		long answer = 0;
		for(int i = 0; i < N; i++) {
			int key = Integer.parseInt(st.nextToken());
			int idx = map.get(key);
			answer += get(1, 0, N - 1, idx + 1, N - 1);
			update(1, 0, N - 1, idx);
		}
		System.out.println(answer);
		br.close();
	}
}
