import java.io.*;
import java.util.*;

public class Main {
	static int n, size;
	static long[] arr;
	static long[] tree;
	static long[][] lazy;
	
	static final int MOD = 1_000_000_007;
	
	static void init(int node, int s, int e) {
		if(s == e) {
			tree[node] = arr[s];
			return;
		}
		int mid = (s + e) >> 1;
		init(node << 1, s, mid);
		init(node << 1 | 1, mid + 1, e);
		tree[node] = (tree[node << 1] + tree[node << 1 | 1]) % MOD;
	}
	
	static void updateLazy(int node, int s, int e) {
		if(lazy[node][0] == 0 && lazy[node][1] == 1) return;
		long a = lazy[node][0], b = lazy[node][1];
		tree[node] = ((e - s + 1) * a + tree[node] * b) % MOD;
		if(s != e) {
			lazy[node << 1][0] = (a + lazy[node << 1][0] * b) % MOD;
			lazy[node << 1][1] = (lazy[node << 1][1] * b) % MOD;
			lazy[node << 1 | 1][0] = (a + lazy[node << 1 | 1][0] * b) % MOD;
			lazy[node << 1 | 1][1] = (lazy[node << 1 | 1][1] * b) % MOD;
		}
		lazy[node][0] = 0;
		lazy[node][1] = 1;
	}
	
	static void updateTree(int node, int s, int e, int ts, int te, int sum, int mul) {
		updateLazy(node, s, e);
		if(e < ts || te < s) return;
		if(ts <= s && e <= te) {
			lazy[node][0] = lazy[node][0] * mul % MOD;
			lazy[node][0] = (lazy[node][0] + sum) % MOD;
			lazy[node][1] = lazy[node][1] * mul % MOD;
			updateLazy(node, s, e);
			return;
		}
		int mid = (s + e) >> 1;
		updateTree(node << 1, s, mid, ts, te, sum, mul);
		updateTree(node << 1 | 1, mid + 1, e, ts, te, sum, mul);
		tree[node] = (tree[node << 1] + tree[node << 1 | 1]) % MOD;
	}
	
	static void update(int q, int x, int y, int v) {
		switch(q) {
		case 1:
			updateTree(1, 1, n, x, y, v, 1);
			break;
		case 2:
			updateTree(1, 1, n, x, y, 0, v);
			break;
		case 3:
			updateTree(1, 1, n, x, y, v, 0);
			break;
		}
	}
	
	static long get(int node, int s, int e, int ts, int te) {
		updateLazy(node, s, e);
		if(e < ts || te < s) return 0;
		if(ts <= s && e <= te) return tree[node];
		int mid = (s + e) >> 1;
		long left = get(node << 1, s, mid, ts, te);
		long right = get(node << 1 | 1, mid + 1, e, ts, te);
		return (left + right) % MOD;
	}
	
	static long query(int x, int y) {
		return get(1, 1, n, x, y);
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		n = Integer.parseInt(br.readLine());
		size = 1 << ((int) Math.ceil(Math.log(n) / Math.log(2)) + 1);
		arr = new long[n + 1];
		tree = new long[size];
		lazy = new long[size][2];
		for(int i = 0; i < size; i++) {
			lazy[i][1] = 1;
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 1; i <= n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		init(1, 1, n);
		int m = Integer.parseInt(br.readLine());
		int cmd, x, y, v;
		StringBuilder sb = new StringBuilder();
		while(m-- > 0) {
			st = new StringTokenizer(br.readLine(), " ");
			cmd = Integer.parseInt(st.nextToken());
			if(cmd == 4) {
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());
				sb.append(query(x, y)).append('\n');
			} else {
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());
				v = Integer.parseInt(st.nextToken());
				update(cmd, x, y, v);
			}
		}
		
		System.out.print(sb);
		br.close();
	}
}
