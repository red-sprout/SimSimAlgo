import java.io.*;
import java.util.*;

public class Main {
	static long[] arr, tree;
	static void init(int node, int start, int end) {
		if(start == end) {
			tree[node] = arr[start];
			return;
		}
		int mid = (start + end) / 2;
		init(2 * node, start, mid);
		init(2 * node + 1, mid + 1, end);
		tree[node] = tree[2 * node] + tree[2 * node + 1];
	}
	static long get(int node, int start, int end, int left, int right) {
		if(right < start || left > end) return 0;
		if(left <= start && end <= right) return tree[node];
		int mid = (start + end) / 2;
		long lsum = get(2 * node, start, mid, left, right);
		long rsum = get(2 * node + 1, mid + 1, end, left, right);
		return lsum + rsum;
	}
	static void updateTree(int node, int start, int end, int index, long diff) {
		if(index < start || end < index) return;
		tree[node] = tree[node] + diff;
		if(start != end) {			
			int mid = (start + end) / 2;
			updateTree(2 * node, start, mid, index, diff);
			updateTree(2 * node + 1, mid + 1, end, index, diff);
		}
	}
	static void update(int node, int start, int end, int index, long val) {
		long diff = val - arr[index];
		arr[index] = val;
		updateTree(node, start, end, index, diff);
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int H = (int) (Math.ceil(Math.log(N) / Math.log(2)));
		arr = new long[N + 1];
		tree = new long[1 << (H + 1)];
		for(int i = 1; i <= N; i++) {
			arr[i] = Long.parseLong(br.readLine());
		}
		init(1, 1, N);
		for(int i = 0; i < M + K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			switch(a) {
			case 1:
				long val = Long.parseLong(st.nextToken());
				update(1, 1, N, b, val);
				break;
			case 2:
				int c = Integer.parseInt(st.nextToken());
				sb.append(get(1, 1, N, b, c)).append('\n');
				break;
			}
		}
		System.out.println(sb.toString());
		br.close();
	}
}