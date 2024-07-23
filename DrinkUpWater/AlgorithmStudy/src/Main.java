import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	private static int n, m, v;
	private static int[][] dfs, bfs;
	private static boolean[] visited;
	private static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		v = Integer.parseInt(st.nextToken());

		dfs = new int[n+1][n+1];
		bfs = new int[n+1][n+1];
		visited = new boolean[n+1];
		
		for (int i = 0 ; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			dfs[a][b] = dfs[b][a] = 1;
			bfs[a][b] = bfs[b][a] = 1;
		}
		
		dfs(v);
		sb.append('\n');
		visited = new boolean[n+1];
		bfs(v);
		System.out.println(sb);
		
	}

	private static void dfs(int v) {
		visited[v] = true;
		sb.append(v + " ");
		
		for (int i = 1; i <= n; i++) {
			if (dfs[v][i] == 1 && !visited[i]) {
				dfs(i);
			}
		}
	}
	
	private static void bfs(int v) {
		Queue<Integer> queue = new LinkedList<>();
		visited[v] = true;
		queue.add(v);
		
		while(!queue.isEmpty()) {
			int tmp = queue.poll();
			sb.append(tmp + " ");
			
			for (int i = 1; i <= n; i++) {
				if (bfs[tmp][i] == 1 && !visited[i]) {
					visited[i] = true;
					queue.add(i);
				}
			}
		}
	}
}
