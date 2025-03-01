import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static List<Integer>[] g;
	static int[] v;
	static int[][] c, f;
	
	static void bfs(int s, int e) {
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(s);
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			if(cur == e) break;
			for(int nxt : g[cur]) {
				if(v[nxt] == -1 && c[cur][nxt] - f[cur][nxt] > 0) {
					q.offer(nxt);
					v[nxt] = cur;
				}
			}
		}
	}
	
	static int solution(int s, int e) {
		int res = 0;
		int minFlow, city;
		while(true) {
			Arrays.fill(v, -1);
			bfs(s, e);
			if(v[e] == -1) break;
			
			minFlow = Integer.MAX_VALUE;
			city = e;
			while(city != s) {
				int before = v[city];
				minFlow = Math.min(minFlow, c[before][city] - f[before][city]);
				city = before;
			}
			
			city = e;
			while(city != s) {
				int before = v[city];
				f[before][city] += 1;
				f[city][before] -= 1;
				city = before;
			}
			
			res += minFlow;
		}
		return res;
	}
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        
        st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());
        g = new List[n + 1];
        v = new int[n + 1];
        c = new int[n + 1][n + 1];
        f = new int[n + 1][n + 1];
        for(int i = 1; i <= n; i++) {
        	g[i] = new ArrayList<>();
        }
        
        while(p-- > 0) {
        	st = new StringTokenizer(br.readLine(), " ");
        	int u = Integer.parseInt(st.nextToken());
        	int v = Integer.parseInt(st.nextToken());
        	g[u].add(v);
        	g[v].add(u);
        	c[u][v] = 1;
        }
        
        System.out.println(solution(1, 2));
        br.close();
    }
}
