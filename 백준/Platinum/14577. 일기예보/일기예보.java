import java.io.*;
import java.util.*;

public class Main {
	static class Node {
		long val = 0;
		int l = -1, r = -1;
	}
	
	static int N, M, size;
	static final long MAX_VALUE = (long) 1e18;
	static final int MAX_SIZE = 3500000;
	static long[] arr;
	static Node[] tree;
	
	static void update(long idx, long val) {
		update(0, 0, MAX_VALUE, idx, val);
	}
	
	static long getTotal(long l, long r) {
		return getTotal(0, 0, MAX_VALUE, l, r);
	}
	
	static long getTth(long t) {
		return getTth(0, 0, MAX_VALUE, t);
	}
	
	static void update(int node, long s, long e, long idx, long val) {
		if(s == e) {
			tree[node].val += val;
			return;
		}
		long mid = (s + e) / 2;
		if(idx <= mid) {
			if(tree[node].l < 0) tree[node].l = size++;
			update(tree[node].l, s, mid, idx, val);
		} else {
			if(tree[node].r < 0) tree[node].r = size++;
			update(tree[node].r, mid + 1, e, idx, val);
		}
		int left = tree[node].l;
		int right = tree[node].r;
		long lval = left < 0 ? 0 : tree[left].val;
		long rval = right < 0 ? 0 : tree[right].val;
		tree[node].val = lval + rval;
	}
	
	static long getTotal(int node, long s, long e, long ts, long te) {
		if(node < 0) return 0;
		if(e < ts || te < s) return 0;
		if(ts <= s && e <= te) return tree[node].val;
		long mid = (s + e) / 2;
		long left = getTotal(tree[node].l, s, mid, ts, te);
		long right = getTotal(tree[node].r, mid + 1, e, ts, te);
		return left + right;
	}
	
    static long getTth(int node, long s, long e, long t) {
        if (s == e) return s;
        long mid = (s + e) / 2;
        long lval = (tree[node].l < 0) ? 0 : tree[tree[node].l].val;
        if (lval >= t) return getTth(tree[node].l, s, mid, t);
        return getTth(tree[node].r, mid + 1, e, t - lval);
    }
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		size = 1;
		arr = new long[N + 1];
		tree = new Node[MAX_SIZE];
		for(int i = 0; i < MAX_SIZE; i++) {
			tree[i] = new Node();
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			update(arr[i], 1);
		}
		
		int q, idx;
		long val, l, r, t;
		while(M-- > 0) {
			st = new StringTokenizer(br.readLine(), " ");
			q = Integer.parseInt(st.nextToken());
			switch(q) {
			case 1:
				idx = Integer.parseInt(st.nextToken());
				val = Long.parseLong(st.nextToken());
				update(arr[idx], -1);
				arr[idx] += val;
				update(arr[idx], 1);
				break;
			case 2:
				idx = Integer.parseInt(st.nextToken());
				val = Long.parseLong(st.nextToken());
				update(arr[idx], -1);
				arr[idx] -= val;
				update(arr[idx], 1);
				break;
			case 3:
				l = Long.parseLong(st.nextToken());
				r = Long.parseLong(st.nextToken());
				sb.append(getTotal(l, r)).append('\n');
				break;
			case 4:
				t = Long.parseLong(st.nextToken());
				sb.append(getTth(N - t + 1)).append('\n');
				break;
			}
		}
		
		System.out.print(sb);
		br.close();
	}
}