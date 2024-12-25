import java.io.*;
import java.util.*;

public class Main {
	static int N, M, size;
	static int[][] tree;
	
	static void update(int r, int c, int val) {
		updateRow(1, 1, N, r, c, val);
	}
	
	static int query(int r1, int c1, int r2, int c2) {
		int rl = Math.min(r1, r2);
		int cl = Math.min(c1, c2);
		int rh = Math.max(r1, r2);
		int ch = Math.max(c1, c2);
		return getRow(1, 1, N, rl, cl, rh, ch);
	}
	
	static void updateRow(int node, int s, int e, int r, int c, int val) {
		if(e < r || r < s) return;
		if(s == e) {
			updateCol(1, 1, N, node, c, val);
			return;
		}
		int mid = (s + e) / 2;
		updateRow(2 * node, s, mid, r, c, val);
		updateRow(2 * node + 1, mid + 1, e, r, c, val);
	}
	
	static void updateCol(int node, int s, int e, int rnode, int c, int val) {
		if(e < c || c < s) return;
		if(s == e) {
			tree[rnode][node] = val;
			for(int i = rnode >> 1; i >= 1; i >>= 1) {
				tree[i][node] = tree[2 * i][node] + tree[2 * i + 1][node];
			}
			return;
		}
		int mid = (s + e) / 2;
		updateCol(2 * node, s, mid, rnode, c, val);
		updateCol(2 * node + 1, mid + 1, e, rnode, c, val);
		tree[rnode][node] = tree[rnode][2 * node] + tree[rnode][2 * node + 1];
		for(int i = rnode >> 1; i >= 1; i >>= 1) {
			tree[i][node] = tree[2 * i][node] + tree[2 * i + 1][node];
		}
	}
	
	static int getRow(int node, int s, int e, int r1, int c1, int r2, int c2) {
		if(e < r1 || r2 < s) return 0;
		if(r1 <= s && e <= r2) return getCol(1, 1, N, node, c1, c2);
		int mid = (s + e) / 2;
		int left = getRow(2 * node, s, mid, r1, c1, r2, c2);
		int right = getRow(2 * node + 1, mid + 1, e, r1, c1, r2, c2);
		return left + right;
	}
	
	static int getCol(int node, int s, int e, int rnode, int c1, int c2) {
		if(e < c1 || c2 < s) return 0;
		if(c1 <= s && e <= c2) return tree[rnode][node];
		int mid = (s + e) / 2;
		int left = getCol(2 * node, s, mid, rnode, c1, c2);
		int right = getCol(2 * node + 1, mid + 1, e, rnode, c1, c2);
		return left + right;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		size = 1 << ((int) Math.ceil(Math.log(N) / Math.log(2)) + 1);
		tree = new int[size][size];
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 1; j <= N; j++) {
				int val = Integer.parseInt(st.nextToken());
				update(i, j, val);
			}
		}
		
		int r, c, val, r1, c1, r2, c2;
		while(M-- > 0) {
			st = new StringTokenizer(br.readLine(), " ");
			int q = Integer.parseInt(st.nextToken());
			switch(q) {
			case 0:
				r = Integer.parseInt(st.nextToken());
				c = Integer.parseInt(st.nextToken());
				val = Integer.parseInt(st.nextToken());
				update(r, c, val);
				break;
			case 1:
				r1 = Integer.parseInt(st.nextToken());
				c1 = Integer.parseInt(st.nextToken());
				r2 = Integer.parseInt(st.nextToken());
				c2 = Integer.parseInt(st.nextToken());
				sb.append(query(r1, c1, r2, c2)).append('\n');
				break;
			}
		}
		
		System.out.print(sb);
		br.close();
	}
}