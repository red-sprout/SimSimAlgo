import java.io.*;
import java.util.*;

public class Main {
	static class Node {
		int v;
		Node l, r;
	}
	static Node root;
	static int N, K;
	static void update(Node node, int s, int e, int i, int v) {
		if(s == e) {
			node.v = v; return;
		}
		int m = (s + e) / 2;
		if(i <= m) {
			if(node.l == null) node.l = new Node();
			update(node.l, s, m, i, v);
		} else {
			if(node.r == null) node.r = new Node();
			update(node.r, m + 1, e, i, v);
		}
		int l = node.l == null ? 1 : node.l.v;
		int r = node.r == null ? 1 : node.r.v;
		node.v = result(l, r);
	}
	static int result(int l, int r) {
		if(l * r > 0) return 1;
		if(l * r < 0) return -1;
		return 0;
	}
	static int get(Node node, int s, int e, int ts, int te) {
		if(node == null) return 1;
		if(te < s || e < ts) return 1;
		if(ts <= s && e <= te) return node.v;
		int mid = (s + e) / 2;
		int l = get(node.l, s, mid, ts, te);
		int r = get(node.r, mid + 1, e, ts, te);
		return result(l, r);
	}
	public static void main(String[] args) {
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in));) {
			while(true) {
				st = new StringTokenizer(br.readLine(), " ");
				N = Integer.parseInt(st.nextToken());
				K = Integer.parseInt(st.nextToken());
				root = new Node();
				st = new StringTokenizer(br.readLine(), " ");
				for(int i = 1; i <= N; i++) {
					int val = Integer.parseInt(st.nextToken());
					update(root, 1, N, i, val);
				}
				while(K-- > 0) {
					st = new StringTokenizer(br.readLine(), " ");
					char q = st.nextToken().charAt(0);
					if(q == 'C') {
						int i = Integer.parseInt(st.nextToken());
						int v = Integer.parseInt(st.nextToken());
						update(root, 1, N, i, v);
					} else {
						int i = Integer.parseInt(st.nextToken());
						int j = Integer.parseInt(st.nextToken());
						char ans = '0';
						int result = get(root, 1, N, i, j);
						if(result > 0) ans = '+';
						else if(result < 0) ans = '-';
						sb.append(ans);
					}
				}
				sb.append('\n');
			}			
		} catch(Exception e) {
			System.out.print(sb);
		}
	}
}