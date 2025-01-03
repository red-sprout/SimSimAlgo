import java.io.*;
import java.util.*;

public class Main {
	static int n, m, k;
	static List<Integer> list;
	static int[] parent;
	
	static int find(int x) {
		if(parent[x] == x) return x;
		return parent[x] = find(parent[x]);
	}
	
	static void union(int x, int y) {
		x = find(x);
		y = find(y);
		if(x == y) return;
		
		if(x > y) {
			parent[y] = x;
		} else {
			parent[x] = y;
		}
	}
	
	static int query(int x) {
		int left = -1;
		int right = m + 1;
		while(left + 1 < right) {
			int mid = (left + right) / 2;
			if(list.get(mid) > x) {
				right = mid;
			} else {
				left = mid;
			}
		}
		int idx = find(right);
		union(idx, idx + 1);
		return list.get(idx);
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		list = new ArrayList<>(m);
		parent = new int[m + 1];
		
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < m; i++) {
			list.add(Integer.parseInt(st.nextToken()));
			parent[i] = i;
		}
		Collections.sort(list);
		
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < k; i++) {
			sb.append(query(Integer.parseInt(st.nextToken()))).append('\n');
		}
		
		System.out.print(sb);
		br.close();
	}
}