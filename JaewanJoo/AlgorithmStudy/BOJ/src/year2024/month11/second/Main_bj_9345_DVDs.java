package year2024.month11.second;

import java.io.*;
import java.util.*;

public class Main_bj_9345_DVDs {
	static int N, K, len, size;
	static int[] minArr, maxArr, minTree, maxTree;
	static void init() {
		init(minTree, minArr, 1, 0, N - 1);
		init(maxTree, maxArr, 1, 0, N - 1);
	}
	static void init(int[] tree, int[] arr, int node, int s, int e) {
		if(s == e) {
			tree[node] = arr[s];
			return;
		}
		int mid = (s + e) / 2;
		init(tree, arr, 2 * node, s, mid);
		init(tree, arr, 2 * node + 1, mid + 1, e);
		tree[node] = Math.min(tree[2 * node], tree[2 * node + 1]);
	}
	static void update(int A, int B) {
		int valA = minArr[A];
		int valB = minArr[B];
		minArr[A] = valB;
		minArr[B] = valA;
		maxArr[A] = -valB;
		maxArr[B] = -valA;
		update(minTree, 1, 0, N - 1, A, valB);
		update(minTree, 1, 0, N - 1, B, valA);
		update(maxTree, 1, 0, N - 1, A, -valB);
		update(maxTree, 1, 0, N - 1, B, -valA);
	}
	static void update(int[] tree, int node, int s, int e, int idx, int val) {
		if(idx < s || e < idx) return;
		if(s == e) {
			tree[node] = val;
			return;
		}
		int mid = (s + e) / 2;
		update(tree, 2 * node, s, mid, idx, val);
		update(tree, 2 * node + 1, mid + 1, e, idx, val);
		tree[node] = Math.min(tree[2 * node], tree[2 * node + 1]);
	}
	static boolean query(int A, int B) {
		int min = get(minTree, 1, 0, N - 1, A, B);
		int max = -get(maxTree, 1, 0, N - 1, A, B);
		return min == A && max == B;
	}
	static int get(int[] tree, int node, int s, int e, int ts, int te) {
		if(te < s || e < ts) return Integer.MAX_VALUE;
		if(ts <= s && e <= te) return tree[node];
		int mid = (s + e) / 2;
		int left = get(tree, 2 * node, s, mid, ts, te);
		int right = get(tree, 2 * node + 1, mid + 1, e, ts, te);
		return Math.min(left, right);
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		for(int test = 0; test < T; test++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			len = 1 << (int) Math.ceil(Math.log(N) / Math.log(2));
			size = len << 1;
			minArr = new int[N];
			maxArr = new int[N];
			minTree = new int[size];
			maxTree = new int[size];
			for(int i = 0; i < N; i++) {
				minArr[i] = i;
				maxArr[i] = -i;
			}
			init();
			for(int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int Q = Integer.parseInt(st.nextToken());
				int A = Integer.parseInt(st.nextToken());
				int B = Integer.parseInt(st.nextToken());
				if(Q == 0) {
					update(A, B);
				} else {
					sb.append(query(A, B) ? "YES" : "NO").append('\n');
				}
			}
		}
		System.out.print(sb.toString());
		br.close();
	}
}
