import java.io.*;
import java.util.*;

public class Main {
	static int n, m, size, cnt;
	static long[] tree, lazy;
	static int[] start, end;
	static List<Integer>[] graph;
	
	static void dfs(int cur) {
		start[cur] = ++cnt;
		for(int nxt : graph[cur]) {
			dfs(nxt);
		}
		end[cur] = cnt;
	}
	
	static void update(int i, int w) {
		updateTree(1, 1, n, start[i], end[i], w);
	}
	
	static void updateLazy(int node, int s, int e) {
		if(lazy[node] == 0) return;
		tree[node] += (e - s + 1) * lazy[node];
		if(s != e) {
			lazy[2 * node] += lazy[node];
			lazy[2 * node + 1] += lazy[node];
		}
		lazy[node] = 0;
	}
	
	static void updateTree(int node, int s, int e, int ts, int te, long val) {
		updateLazy(node, s, e);
		if(e < ts || te < s) return;
		if(ts <= s && e <= te) {
			tree[node] += (e - s + 1) * val;
			if(s != e) {
				lazy[2 * node] += val;
				lazy[2 * node + 1] += val;
			}
			return;
		}
		int mid = (s + e) / 2;
		updateTree(2 * node, s, mid, ts, te, val);
		updateTree(2 * node + 1, mid + 1, e, ts, te, val);
		tree[node] = tree[2 * node] + tree[2 * node + 1];
	}
	
	static long query(int i) {
		return get(1, 1, n, start[i]);
	}
	
	static long get(int node, int s, int e, int idx) {
		updateLazy(node, s, e);
		if(e < idx || idx < s) return 0;
		if(s == e) return tree[node];
		int mid = (s + e) / 2;
		long left = get(2 * node, s, mid, idx);
		long right = get(2 * node + 1, mid + 1, e, idx);
		return left + right;
	}
	
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        
        st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        size = 1 << ((int) Math.ceil(Math.log(n) / Math.log(2)) + 1);
        cnt = 0;
        tree = new long[size];
        lazy = new long[size];
        start = new int[n + 1];
        end = new int[n + 1];
        graph = new List[n + 1];
        for(int i = 1; i <= n; i++) {
        	graph[i] = new ArrayList<>();
        }
        
        int root = -1;
        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 1; i <= n; i++) {
        	int parent = Integer.parseInt(st.nextToken());
        	if(parent == -1) {
        		root = i;
        		continue;
        	}
        	graph[parent].add(i);
        }
        
        dfs(root);
        
        int q, i, w;
        StringBuilder sb = new StringBuilder();
        while(m-- > 0) {
        	st = new StringTokenizer(br.readLine(), " ");
        	q = Integer.parseInt(st.nextToken());
        	switch(q) {
        	case 1:
        		i = Integer.parseInt(st.nextToken());
        		w = Integer.parseInt(st.nextToken());
        		update(i, w);
        		break;
        	case 2:
        		i = Integer.parseInt(st.nextToken());
        		sb.append(query(i)).append('\n');
        		break;
        	}
        }
        
        System.out.print(sb);
        br.close();
    }
}