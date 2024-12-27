import java.io.*;
import java.util.*;

public class Main {
	static int n, m, size;
	static int[] arr;
	static int[][] t;
	
	static void init() {
		initMin(t[0], 1, 1, n);
		initMax(t[1], 1, 1, n);
	}
	
	static void initMin(int[] tree, int node, int s, int e) {
		if(s == e) {
			tree[node] = arr[s];
			return;
		}
		int mid = (s + e) >> 1;
		initMin(tree, 2 * node, s, mid);
		initMin(tree, 2 * node + 1, mid + 1, e);
		tree[node] = Math.min(tree[2 * node], tree[2 * node + 1]);
	}
	
	static void initMax(int[] tree, int node, int s, int e) {
		if(s == e) {
			tree[node] = arr[s];
			return;
		}
		int mid = (s + e) >> 1;
		initMax(tree, 2 * node, s, mid);
		initMax(tree, 2 * node + 1, mid + 1, e);
		tree[node] = Math.max(tree[2 * node], tree[2 * node + 1]);
	}
	
	static int getMin(int a, int b) {
		return getMin(t[0], 1, 1, n, a, b);
	}
	
	static int getMax(int a, int b) {
		return getMax(t[1], 1, 1, n, a, b);
	}
	
	static int getMin(int[] tree, int node, int s, int e, int ts, int te) {
		if(e < ts || te < s) return Integer.MAX_VALUE;
		if(ts <= s && e <= te) return tree[node];
		int mid = (s + e) >> 1;
		int left = getMin(tree, 2 * node, s, mid, ts, te);
		int right = getMin(tree, 2 * node + 1, mid + 1, e, ts, te);
		return Math.min(left, right);
	}
	
	static int getMax(int[] tree, int node, int s, int e, int ts, int te) {
		if(e < ts || te < s) return 0;
		if(ts <= s && e <= te) return tree[node];
		int mid = (s + e) >> 1;
		int left = getMax(tree, 2 * node, s, mid, ts, te);
		int right = getMax(tree, 2 * node + 1, mid + 1, e, ts, te);
		return Math.max(left, right);
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		size = 1 << ((int) Math.ceil(Math.log(n) / Math.log(2)) + 1);
		arr = new int[n + 1];
		t = new int[2][size];
		for(int i = 1; i < n + 1; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		init();
		while(m-- > 0) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			sb.append(getMin(a, b)).append(' ').append(getMax(a, b)).append('\n');
		}
		
		System.out.print(sb);
		br.close();
	}
}