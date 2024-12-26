import java.io.*;

public class Main {
	static int N, size;
	static int[] order, tree;
	
	static void init(int node, int s, int e) {
		if(s == e) {
			tree[node] = 1;
			return;
		}
		int mid = (s + e) / 2;
		init(2 * node, s, mid);
		init(2 * node + 1, mid + 1, e);
		tree[node] = tree[2 * node] + tree[2 * node + 1];
	}
	
	static void update(int node, int s, int e, int idx, int val) {
		if(e < idx || idx < s) return;
		if(s == e) {
			tree[node] += val;
			return;
		}
		int mid = (s + e) / 2;
		update(2 * node, s, mid, idx, val);
		update(2 * node + 1, mid + 1, e, idx, val);
		tree[node] = tree[2 * node] + tree[2 * node + 1];
	}
	
	static int getKth(int node, int s, int e, int k) {
		if(s == e) return s;
		int left = tree[2 * node];
		int mid = (s + e) / 2;
		if(left >= k) return getKth(2 * node, s, mid, k);
		return getKth(2 * node + 1, mid + 1, e, k - left);
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		size = 1 << ((int) Math.ceil(Math.log(N) / Math.log(2)) + 1);
		order = new int[N + 1];
		tree = new int[size];
		init(1, 1, N);
		
		for(int i = 1; i <= N; i++) {
			int k = Integer.parseInt(br.readLine());
			int idx = getKth(1, 1, N, k + 1);
			update(1, 1, N, idx, -1);
			order[idx] = i;
		}
		
		for(int i = 1; i <= N; i++) {		
			sb.append(order[i]).append('\n');
		}
		
		System.out.print(sb);
		br.close();
	}
}