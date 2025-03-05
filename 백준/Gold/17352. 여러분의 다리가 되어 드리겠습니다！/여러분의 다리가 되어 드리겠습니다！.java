import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static int[] parent;
	
	static int find(int x) {
		if(parent[x] < 0) return x;
		return parent[x] = find(parent[x]);
	}
	
	static void union(int x, int y) {
		x = find(x);
		y = find(y);
		parent[x] = y;
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		n = Integer.parseInt(br.readLine());
		parent = new int[n + 1];
		Arrays.fill(parent, -1);
		for(int i = 0; i < n - 2; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			union(x, y);
		}
		
		List<Integer> res = new ArrayList<>();
		for(int i = 1; i <= n; i++) {
			if(parent[i] < 0) res.add(i);
		}
		
		System.out.println(res.get(0) + " " + res.get(1));
		br.close();
	}
}