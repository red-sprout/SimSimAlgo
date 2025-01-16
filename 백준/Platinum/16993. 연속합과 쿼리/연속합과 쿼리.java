import java.io.*;
import java.util.*;

public class Main {
	static class Node {
		int left, right, mid, all;
		
		Node() {
			this(-1_000_000_000);
			all = 0;
		}
		
		Node(int val) {
			left = right = mid = all = val;
		}
	}
	
	static int n, size;
	static int[] arr;
	static Node[] tree;
	
	static Node merge(Node n1, Node n2) {
		Node node = new Node();
		node.left = Math.max(n1.left, n1.all + n2.left);
		node.right = Math.max(n2.right, n1.right + n2.all);
		node.mid = Math.max(Math.max(n1.mid, n2.mid), n1.right + n2.left);
		node.all = n1.all + n2.all;
		return node;
	}
	
	static void init(int node, int s, int e) {
		if(s == e) {
			tree[node] = new Node(arr[s]);
			return;
		}
		int mid = (s + e) >> 1;
		init(node << 1, s, mid);
		init(node << 1 | 1, mid + 1, e);
		tree[node] = merge(tree[node << 1], tree[node << 1 | 1]);
	}
	
	static int query(int s, int e) {
		return get(1, 1, n, s, e).mid;
	}
	
	static Node get(int node, int s, int e, int ts, int te) {
		if(e < ts || te < s) return new Node();
		if(ts <= s && e <= te) return tree[node];
		int mid = (s + e) >> 1;
		Node l = get(node << 1, s, mid, ts, te);
		Node r = get(node << 1 | 1, mid + 1, e, ts, te);
		return merge(l, r);
	}
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;
        
        n = Integer.parseInt(br.readLine());
        size = 1 << ((int) Math.ceil(Math.log(n) / Math.log(2)) + 1);
        arr = new int[n + 1];
        tree = new Node[size];
        
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
        	sb.append(query(i, j)).append('\n');
        }
        
        System.out.print(sb);
        br.close();
    }
}
