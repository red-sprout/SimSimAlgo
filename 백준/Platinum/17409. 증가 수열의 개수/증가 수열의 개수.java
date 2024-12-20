import java.io.*;
import java.util.*;

public class Main {
	static int N, K, size;
	static int[][] dp;
	static final int CONST = 1_000_000_007;
	
	static void update(int[] tree, int node, int s, int e, int idx, int val) {
		if(e < idx || idx < s) return;
		if(s == e) {
			tree[node] = val;
			return;
		}
		int mid = (s + e) / 2;
		update(tree, 2 * node, s, mid, idx, val);
		update(tree, 2 * node + 1, mid + 1, e, idx, val);
		tree[node] = (tree[2 * node] + tree[2 * node + 1]) % CONST;
	}
	
	static int get(int[] tree, int node, int s, int e, int ts, int te) {
		if(e < ts || te < s) return 0;
		if(ts <= s && e <= te) return tree[node];
		int mid = (s + e) / 2;
		int left = get(tree, 2 * node, s, mid, ts, te);
		int right = get(tree, 2 * node + 1, mid + 1, e, ts, te);
		return (left + right) % CONST;
	}
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        
        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        size = 1 << ((int) Math.ceil(Math.log(N) / Math.log(2)) + 1);
        dp = new int[K + 1][size];
        
        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < N; i++) {
        	int a = Integer.parseInt(st.nextToken());
        	update(dp[1], 1, 1, N, a, 1);
        	for(int j = 2; j <= K; j++) {
        		update(dp[j], 1, 1, N, a, get(dp[j - 1], 1, 1, N, 1, a - 1));
        	}
        }
        
        System.out.println(get(dp[K], 1, 1, N, 1, N));
        br.close();
    }
}