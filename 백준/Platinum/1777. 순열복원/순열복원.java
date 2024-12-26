import java.io.*;
import java.util.*;

public class Main {
	static int N, size;
	static int[] arr, tree;
	
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
		int mid = (s + e) / 2;
		if(tree[2 * node] >= k) return getKth(2 * node, s, mid, k);
		return getKth(2 * node + 1, mid + 1, e, k - tree[2 * node]);
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		size = 1 << ((int) Math.ceil(Math.log(N) / Math.log(2)) + 1);
		arr = new int[N + 1];
		tree = new int[size];
		
		init(1, 1, N);
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] order = new int[N + 1];
		for(int i = N; i >= 1; i--) {
			int idx = getKth(1, 1, N, i - arr[i]);
			update(1, 1, N, idx, -1);
			order[idx] = i;
		}
		
		for(int i = 1; i <= N; i++) {
			sb.append(order[i]).append(' ');
		}
		
		System.out.println(sb);
		br.close();
	}
}