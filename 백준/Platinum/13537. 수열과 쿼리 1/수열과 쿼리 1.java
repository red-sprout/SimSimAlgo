import java.io.*;
import java.util.*;

public class Main {
	static int n, size;
	static int[] arr;
	static int[][] tree;
	
	static int[] merge(int[] n1, int[] n2) {
		int[] res = new int[n1.length + n2.length];
		int i = 0, j = 0, idx = 0;
		
		while(i < n1.length && j < n2.length) {
			if(n1[i] < n2[j]) res[idx++] = n1[i++];
			else res[idx++] = n2[j++];
		}
		
		while(i < n1.length) {
			res[idx++] = n1[i++];
		}
		
		while(j < n2.length) {
			res[idx++] = n2[j++];
		}
		
		return res;
	}
	
	static void init(int node, int s, int e) {
		if(s == e) {
			tree[node] = new int[] {arr[s]};
			return;
		}
		int mid = (s + e) >> 1;
		init(node << 1, s, mid);
		init(node << 1 | 1, mid + 1, e);
		tree[node] = merge(tree[node << 1], tree[node << 1 | 1]);
	}
	
	static int upperBound(int[] res, int val) {
		int left = -1;
		int right = res.length;
		while(left + 1 < right) {
			int mid = (left + right) >> 1;
			if(res[mid] > val) {
				right = mid;
			} else {
				left = mid;
			}
		}
		return right;
	}
	
	static int query(int i, int j, int k) {
		return get(1, 1, n, i, j, k);
	}
	
	static int get(int node, int s, int e, int ts, int te, int k) {
		if(e < ts || te < s) return 0;
		if(ts <= s && e <= te) return tree[node].length - upperBound(tree[node], k);
		int mid = (s + e) >> 1;
		int left = get(node << 1, s, mid, ts, te, k);
		int right = get(node << 1 | 1, mid + 1, e, ts, te, k);
		return left + right;
	}
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;
        
        n = Integer.parseInt(br.readLine());
        size = 1 << ((int) Math.ceil(Math.log(n) / Math.log(2)) + 1);
        arr = new int[n + 1];
        tree = new int[size][];
        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 1; i <= n; i++) {
        	arr[i] = Integer.parseInt(st.nextToken());
        }
        
        init(1, 1, n);
        int m = Integer.parseInt(br.readLine());
        while(m-- > 0) {
        	st = new StringTokenizer(br.readLine(), " ");
        	int i = Integer.parseInt(st.nextToken());
        	int j = Integer.parseInt(st.nextToken());
        	int k = Integer.parseInt(st.nextToken());
        	sb.append(query(i, j, k)).append('\n');
        }
        
        System.out.print(sb);
        br.close();
    }
}
