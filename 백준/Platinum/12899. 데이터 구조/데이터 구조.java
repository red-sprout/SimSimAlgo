import java.io.*;
import java.util.*;

public class Main {
	static int[] tree;
	static final int MAX = 2_000_001;
	static void update(int node, int start, int end, int index) {
		if(index < start || end < index) return;
		tree[node] += 1;
		if(start == end) return;
		int mid = (start + end) / 2;
		update(2 * node, start, mid, index);
		update(2 * node + 1, mid + 1, end, index);
	}
	static int get(int node, int start, int end, int k) {
		tree[node] -= 1;
		if(start == end) return start;
		int mid = (start + end) / 2;
		if(tree[2 * node] < k) return get(2 * node + 1, mid + 1, end, k - tree[2 * node]);
		else return get(2 * node, start, mid, k);
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine());
		tree = new int[4 * MAX];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int T = Integer.parseInt(st.nextToken());
			int X = Integer.parseInt(st.nextToken());
			if(T == 1) {
				update(1, 1, MAX, X);
			} else {
				sb.append(get(1, 1, MAX, X)).append('\n');
			}
		}
		System.out.print(sb.toString());
		br.close();
	}
}