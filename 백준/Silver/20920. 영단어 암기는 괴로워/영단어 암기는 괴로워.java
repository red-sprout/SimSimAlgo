import java.io.*;
import java.util.*;

public class Main {
	static class Node implements Comparable<Node> {
		String s;
		int v;
		
		public Node(String s, int v) {
			this.s = s;
			this.v = v;
		}

		@Override
		public int compareTo(Node n) {
			if(n.v == this.v) {
				if(n.s.length() == this.s.length()) {
					return this.s.compareTo(n.s);
				} else {
					return n.s.length() - this.s.length();
				}
			}
			return n.v - this.v;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		HashMap<String, Integer> cnt = new HashMap<>();
		while(n-- > 0) {
			String str = br.readLine();
			if(str.length() >= m) {
				cnt.put(str, cnt.getOrDefault(str, 0) + 1);
			}
		}
		
		List<Node> list = new ArrayList<>();
		for(String s : cnt.keySet()) {
			list.add(new Node(s, cnt.get(s)));
		}
		
		Collections.sort(list);
		
		for(Node node : list) {
			sb.append(node.s).append('\n');
		}
		
		System.out.print(sb);
		br.close();
	}
}