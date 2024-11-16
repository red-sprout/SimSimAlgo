import java.io.*;
import java.util.*;

public class Main {
	static int n, m, size;
	static int[] arr, tree, lazy;
	static void init(int node, int s, int e) {
		if(s == e) {
			tree[node] = arr[s];
			return;
		}
		int mid = (s + e) / 2;
		init(2 * node, s, mid);
		init(2 * node + 1, mid + 1, e);
		tree[node] = tree[2 * node] ^ tree[2 * node + 1];
	}
	static void update(int ts, int te, int val) {
		updateTree(1, 0, n - 1, ts, te, val);
	}
	static void updateLazy(int node, int s, int e) {
		if(lazy[node] == 0) return;
		if((e - s + 1) % 2 == 1) tree[node] ^= lazy[node];
		if(s != e) {
			lazy[2 * node] ^= lazy[node];
			lazy[2 * node + 1] ^= lazy[node];
		}
		lazy[node] = 0;
	}
	static void updateTree(int node, int s, int e, int ts, int te, int val) {
		updateLazy(node, s, e);
		if(te < s || e < ts) return;
		if(ts <= s && e <= te) {
			if((e - s + 1) % 2 == 1) tree[node] ^= val;
			if(s != e) {
				lazy[2 * node] ^= val;
				lazy[2 * node + 1] ^= val;
			}
			return;
		}
		int mid = (s + e) / 2;
		updateTree(2 * node, s, mid, ts, te, val);
		updateTree(2 * node + 1, mid + 1, e, ts, te, val);
		tree[node] = tree[2 * node] ^ tree[2 * node + 1];
	}
	static int get(int ts, int te) {
		return getTree(1, 0, n - 1, ts, te);
	}
	static int getTree(int node, int s, int e, int ts, int te) {
		updateLazy(node, s, e);
		if(te < s || e < ts) return 0;
		if(ts <= s && e <= te) return tree[node];
		int mid = (s + e) / 2;
		int left = getTree(2 * node, s, mid, ts, te);
		int right = getTree(2 * node + 1, mid + 1, e, ts, te);
		return left ^ right;
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		n = Integer.parseInt(br.readLine());
		size = 1 << ((int) Math.ceil(Math.log(n) / Math.log(2)) + 1);
		arr = new int[n];
		tree = new int[size];
		lazy = new int[size];
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		init(1, 0, n - 1);
		m = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int order = 0; order < m; order++) {
			st = new StringTokenizer(br.readLine(), " ");
			int q = Integer.parseInt(st.nextToken());
			int i = Integer.parseInt(st.nextToken());
			int j = Integer.parseInt(st.nextToken());
			if(q == 1) {
				int k = Integer.parseInt(st.nextToken());
				update(i, j, k);
			} else {
				sb.append(get(i, j)).append('\n');
			}
		}
		System.out.print(sb);
		br.close();
	}
}