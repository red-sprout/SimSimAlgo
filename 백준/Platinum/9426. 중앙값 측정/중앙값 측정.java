import java.io.*;
import java.util.*;

public class Main {
	static int n, k, size;
	static int[] arr, tree;
	
	static final int MAX = 65536;
	
	static void update(int node, int s, int e, int idx, int val) {
		if(e < idx || idx < s) return;
		if(s == e) {
			tree[node] += val;
			return;
		}
		int mid = (s + e) >> 1;
		update(node << 1, s, mid, idx, val);
		update(node << 1 | 1, mid + 1, e, idx, val);
		tree[node] = tree[node << 1] + tree[node << 1 | 1];
	}
	
	static int get(int node, int s, int e, int t) {
		if(s == e) return s;
		int left = tree[node << 1];
		int mid = (s + e) >> 1;
		if(t <= left) {
			return get(node << 1, s, mid, t);
		} else {
			return get(node << 1 | 1, mid + 1, e, t - left);
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		size = 1 << (int)(Math.ceil(Math.log(MAX) / Math.log(2)) + 1);
		arr = new int[n];
		tree = new int[size];
		
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		for(int i = 0; i < k; i++) {
			update(1, 0, MAX - 1, arr[i], 1);
		}
		
		long res = get(1, 0, MAX - 1, (k + 1) / 2);
		for(int i = k; i < n; i++) {
			update(1, 0, MAX - 1, arr[i - k], -1);
			update(1, 0, MAX - 1, arr[i], 1);
			res += get(1, 0, MAX - 1, (k + 1) / 2);
		}
		
		System.out.println(res);
		br.close();
	}
}
