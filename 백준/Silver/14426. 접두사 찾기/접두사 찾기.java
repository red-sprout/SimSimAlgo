import java.io.*;
import java.util.*;

public class Main {
	static class Node {
		Map<Character, Node> next = new HashMap<>();
	}
	
	static class Trie {
		Node root = new Node();
		public void add(String str) {
			Node node = root;
			for(int i = 0; i < str.length(); i++) {
				char c = str.charAt(i);
				node.next.putIfAbsent(c, new Node());
				node = node.next.get(c);
			}
		}
		
		public boolean find(String str) {
			Node node = root;
			for(int i = 0; i < str.length(); i++) {
				char c = str.charAt(i);
				if(!node.next.containsKey(c)) return false;
				node = node.next.get(c);
			}
			return true;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine(), " ");
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		Trie trie = new Trie();
		
		while(n-- > 0) trie.add(br.readLine());
		
		int res = 0;
		while(m-- > 0) {
			if(trie.find(br.readLine())) res++;
		}
		
		System.out.println(res);
		br.close();
	}
}