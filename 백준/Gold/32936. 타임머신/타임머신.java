import java.io.*;
import java.util.*;

public class Main {
    static int n, m, a, b, c;
    static List<Integer>[] g;
    static int[] dist;
    static boolean [] vis;
    static final int INF = 1_000_000_007;

    static void bfs(int s) {
        Queue<Integer> q = new ArrayDeque<>();
        Arrays.fill(dist, INF);
        Arrays.fill(vis, false);

        q.add(s);
        dist[s] = 0;
        vis[s] = true;

        while(!q.isEmpty()) {
            int cur = q.poll();
            for(int nxt : g[cur]) {
                if(!vis[nxt]) {
                    dist[nxt] = dist[cur] + 1;
                    vis[nxt] = true;
                    q.add(nxt);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        g = new List[n + 1];
        for(int i = 1; i <= n; ++i) {
            g[i] = new ArrayList<>();
        }

        for(int i = 0; i < m; ++i) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            g[u].add(v);
        }

        dist = new int[n + 1];
        vis = new boolean[n + 1];
        int oa, on, ba, bn;

        bfs(1);
        oa = dist[a];
        on = dist[n];

        bfs(b);
        ba = dist[a];
        bn = dist[n];

        if (oa != INF && bn != INF) {
            on = Math.min(on, oa + bn - c);
        }
        
        if (oa != INF && bn != INF && ba != INF && ba < c) {
            System.out.println("-inf");
        } else if (on != INF) {
            System.out.println(on);
        } else {
            System.out.println("x");
        }

        br.close();
    }
}
