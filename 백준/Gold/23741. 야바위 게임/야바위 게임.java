import java.io.*;
import java.util.*;

public class Main {
    static int n, m, x, y;
    static List<Integer>[] g;
    static boolean[][] v;
    static final int MAX = 1_000_000_000;

    static void dfs(int node, int cnt) {
        for(int nxt : g[node]) {
            if(!v[nxt][(cnt + 1) % 2]) {
                v[nxt][(cnt + 1) % 2] = true;
                dfs(nxt, cnt + 1);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());

        g = new List[n + 1];
        v = new boolean[n + 1][2];
        for(int i = 1; i <= n; i++) {
            g[i] = new ArrayList<>();
        }

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            g[u].add(v);
            g[v].add(u);
        }

        dfs(x, 0);
        boolean flag = false;
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= n; i++) {
            if(v[i][y % 2]) {
                flag = true;
                sb.append(i).append(' ');
            }
        }

        System.out.println(flag ? sb.toString() : -1);
        br.close();
    }
}