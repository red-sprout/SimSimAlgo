import java.io.*;
import java.util.*;

public class Main {
	static class Node {
		int idx;
		boolean color = false;
		
		public Node(int idx) {
			this.idx = idx;
		}
	}
	
	static List<Node> g[];
	static Node[] nodeList;
	static boolean[] v;
	static boolean flag;
	
	static void paint(Node node) {
		Queue<Node> q = new ArrayDeque<>();
		v[node.idx] = true;
		q.offer(node);
		
		while(!q.isEmpty()) {
			Node now = q.poll();
			
			for(Node nxt : g[now.idx]) {
				boolean nc = !now.color;
				if(v[nxt.idx]) {
					if(nxt.color == now.color) {
						flag = false;
						return;
					}
					continue;
				}
				
				v[nxt.idx] = true;
				nxt.color = nc;
				q.offer(nxt);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		g = new List[n + 1];
		nodeList = new Node[n + 1];
		v = new boolean[n + 1];
		v[0] = true;
		
		for(int i = 0; i <= n; i++) {
			g[i] = new ArrayList<>();
			nodeList[i] = new Node(i);
		}
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			g[n1].add(nodeList[n2]);
			g[n2].add(nodeList[n1]);
		}
		
		flag = true;
		for(int i = 1; i <= n; i++) {
			if(v[i]) continue;
			paint(nodeList[i]);
			if(!flag) break;
		}
		
		System.out.println(flag ? 1 : 0);
		br.close();
	}
}