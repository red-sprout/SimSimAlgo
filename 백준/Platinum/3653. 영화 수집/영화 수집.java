import java.io.*;
import java.util.*;

public class Main {
	static int n, m, size, top;
	static int[] arr, pos, tree;
	
	static void init(int node, int s, int e) {
		if(s == e) {
			tree[node] = arr[s];
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
	
	static int get(int node, int s, int e, int ts, int te) {
		if(e < ts || te < s) return 0;
		if(ts <= s && e <= te) return tree[node];
		int mid = (s + e) / 2;
		return get(2 * node, s, mid, ts, te) + get(2 * node + 1, mid + 1, e, ts, te);
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		while(T-- > 0) {
			st = new StringTokenizer(br.readLine(), " ");
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			size = 1 << ((int)(Math.ceil(Math.log(n + m) / Math.log(2))) + 1);
			top = m - 1;
			arr = new int[n + m];
			for(int i = m; i < n + m; i++) {
				arr[i] = 1;
			}
			pos = new int[n + 1];
			for(int i = 1; i < n + 1; i++) {
				pos[i] = i + m - 1;
			}
			tree = new int[size];
			
			init(1, 0, n + m - 1);
			st = new StringTokenizer(br.readLine(), " ");
			for(int i = 0; i < m; i++) {
				int q = Integer.parseInt(st.nextToken());
				update(1, 0, n + m - 1, pos[q], -1);
				sb.append(get(1, 0, n + m - 1, 0, pos[q])).append(' ');
				pos[q] = top--;
				update(1, 0, n + m - 1, pos[q], 1);
			}
			sb.append('\n');
		}
		
		System.out.print(sb);
		br.close();
	}
}
