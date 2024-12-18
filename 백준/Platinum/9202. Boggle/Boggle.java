import java.io.*;
import java.util.*;

public class Main {
	static int w;
	static char[][] map;
	static String[] dictionary;
	static Map<String, Integer> data;
	static boolean[] check;
	static Trie trie;
	
	static int[] score = {0, 0, 0, 1, 1, 2, 3, 5, 11};
	static int[] dr = {-1, 0, 1, 0, -1, -1, 1, 1};
	static int[] dc = {0, -1, 0, 1, -1, 1, -1, 1};
	
	static class Node {
		Map<Character, Node> children = new HashMap<>();
		boolean isEnd = false;
	}
	
	static class Trie {
		Node root = new Node();
		
		void insert(String str) {
			Node node = root;
			for(int i = 0; i < str.length(); i++) {
				char key = str.charAt(i);
				node.children.putIfAbsent(key, new Node());
				node = node.children.get(key);
			}
			node.isEnd = true;
		}
		
		boolean find(String str) {
			Node node = root;
			for(int i = 0; i < str.length(); i++) {
				char key = str.charAt(i);
				if(!node.children.containsKey(key)) return false;
				node = node.children.get(key);
			}
			if(!node.isEnd || check[data.get(str)]) return true;
			check[data.get(str)] = true;
			return true;
		}
	}
	
	static void dfs(int r, int c, int d, int v, String str) {
		if(d == 8 || !trie.find(str)) return;
		for(int i = 0; i < 8; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if(nr < 0 || nr >= 4 || nc < 0 || nc >= 4) continue;
			int ni = 4 * nr + nc;
			if((v & (1 << ni)) == 0) {
				dfs(nr, nc, d + 1, v | (1 << ni), str + map[nr][nc]);
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		w = Integer.parseInt(br.readLine());
		map = new char[4][4];
		dictionary = new String[w];
		data = new HashMap<>();
		check = new boolean[w];
		trie = new Trie();
		for(int i = 0; i < w; i++) {
			dictionary[i] = br.readLine();
		}
		
		Arrays.sort(dictionary, (s1, s2) -> s1.length() == s2.length() ? s1.compareTo(s2) : s2.length() - s1.length());
		for(int i = 0; i < w; i++) {
			data.put(dictionary[i], i);
			trie.insert(dictionary[i]);
		}
		br.readLine();
		
		int b = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		while(b-- > 0) {
			for(int i = 0; i < 4; i++) {
				map[i] = br.readLine().toCharArray();
			}
			Arrays.fill(check, false);
			for(int i = 0; i < 4; i++) {
				for(int j = 0; j < 4; j++) {
					dfs(i, j, 0, 1 << (4 * i + j), map[i][j] + "");
				}
			}
			int s = 0, idx = -1, cnt = 0;
			for(int i = 0; i < w; i++) {
				if(check[i]) {
					if(idx == -1) idx = i;
					s += score[dictionary[i].length()];
					cnt++;
				}
			}
			
			sb.append(s).append(' ').append(dictionary[idx]).append(' ').append(cnt).append('\n');
			if(b > 0) br.readLine();
		}
		
		System.out.print(sb);
		br.close();
	}
}