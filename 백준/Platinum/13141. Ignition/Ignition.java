import java.io.*;
import java.util.*;

public class Main {
	static int n, m;
	static int[][] dist, g;
	
	static final int MAX = 1_000_000_000;
	
	static void initDist() {
		for(int k = 1; k <= n; k++) {
			for(int i = 1; i <= n; i++) {
				for(int j = 1; j <= n; j++) {
					dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
				}
			}
		}
	}
	
	static double ignition() {
        double time = MAX;
        for(int i = 1; i <= n; i++) {
        	double maxTime = 0;
        	for(int j = 1; j <= n; j++) {
        		for(int k = 1; k <= n; k++) {
        			if(g[j][k] == -1) continue;
        			int remain = g[j][k] - (dist[i][k] - dist[i][j]);
        			if(remain > 0) maxTime = Math.max(maxTime, remain / 2.0 + dist[i][k]);        				
        		}
        	}
        	time = Math.min(time, maxTime);
        }
        return time;
	}
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        
        st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        dist = new int[n + 1][n + 1];
        g = new int[n + 1][n + 1];
        
        for(int i = 0; i <= n; i++) {
        	for(int j = 0; j <= n; j++) {
        		dist[i][j] = MAX;
        		g[i][j] = -1;
        		if(i == j) dist[i][j] = 0;
        	}
        }
        
        for(int i = 0; i < m; i++) {
        	st = new StringTokenizer(br.readLine());
        	int s = Integer.parseInt(st.nextToken());
        	int e = Integer.parseInt(st.nextToken());
        	int l = Integer.parseInt(st.nextToken());
        	dist[s][e] = Math.min(dist[s][e], l);
        	dist[e][s] = Math.min(dist[e][s], l);
        	g[s][e] = Math.max(g[s][e], l);
        	g[e][s] = Math.max(g[e][s], l);
        }
        
        initDist();
        System.out.printf("%.1f\n", ignition());
        
        br.close();
    }
}
