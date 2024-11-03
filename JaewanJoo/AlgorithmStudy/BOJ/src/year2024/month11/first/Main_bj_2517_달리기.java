package year2024.month11.first;

import java.io.*;
import java.util.*;

public class Main_bj_2517_달리기 {
	static int N, len, size;
	static int[] tree;
	static void update(int node, int s, int e, int idx, int val) {
		if(idx < s || e < idx) return;
		if(s == e) {
			tree[node] += val;
			return;
		}
		int mid = (s + e) / 2;
		update(2 * node, s , mid, idx, val);
		update(2 * node + 1, mid + 1, e, idx, val);
		tree[node] = tree[2 * node] + tree[2 * node + 1];
	}
	static int get(int node, int s, int e, int ts, int te) {
		if(te < s || e < ts) return 0;
		if(ts <= s && e <= te) return tree[node];
		int mid = (s + e) / 2;
		int left = get(2 * node, s, mid, ts, te);
		int right = get(2 * node + 1, mid + 1, e, ts, te);
		return left + right;
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		len = 1 << (int)Math.ceil(Math.log(N + 1) / Math.log(2));
		size = len << 1;
		int[] arr = new int[N + 1];
		tree = new int[size];
		for(int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		int[] copy = Arrays.copyOf(arr, arr.length);
		Arrays.sort(copy);
		Map<Integer, Integer> map = new HashMap<>();
		for(int i = 1; i <= N; i++) {
			map.put(copy[i], i);
		}
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i <= N; i++) {
			int key = arr[i];
			int idx = map.get(key);
			sb.append(get(1, 1, N, idx, N) + 1).append('\n');
			update(1, 1, N, idx, 1);
		}
		System.out.println(sb.toString().trim());
		br.close();
	}
}
