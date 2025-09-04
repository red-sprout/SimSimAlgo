import java.io.*;
import java.util.*;

public class Main {
    static int n, q, root;
    static int[] dep;
    static long[] dp, sub;
    static int[][] par;
    static List<Integer>[] tree;

    static void dfs(int cur, int d) {
        dep[cur] = d;
        sub[cur] = 1;
        for(int nxt : tree[cur]) {
            if(dep[nxt] == 0) {
                dfs(nxt, d + 1);
                dp[cur] += sub[nxt] * (sub[cur] - 1);
                sub[cur] += sub[nxt];
                par[0][nxt] = cur;
            }
        }
    }

    static int lca(int a, int b, boolean isGetY) {
        int u, v, d;
        if(dep[a] > dep[b]) {
            u = a;
            v = b;
            d = dep[u] - dep[v];
        } else {
            u = b;
            v = a;
            d = dep[u] - dep[v];
        }

        if(isGetY) --d;
        for(int i = 18; i >= 0; --i) {
            if((d & (1 << i)) != 0) {
                u = par[i][u];
            }
        }

        if(isGetY || u == v) return u;

        for(int i = 18; i >= 0; --i) {
            if(par[i][u] != par[i][v]) {
                u = par[i][u];
                v = par[i][v];
            }
        }

        return par[0][u];
    }

    static void cmd1(int x) {
        root = x;
    }

    static long cmd2(int x) {
        if(root == x) {
            return dp[x] + n - 1 + (n - sub[x]) * (sub[x] - 1);
        } else if(x == lca(root, x, false)) {
            int y = lca(root, x, true);
            return dp[x] + n - sub[y] - 1 + (n - sub[x] - sub[y]) * (sub[x] - sub[y] - 1);
        }
        return dp[x] + sub[x] - 1;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());
        root = 1;
        dep = new int[n + 1];
        dp = new long[n + 1];
        sub = new long[n + 1];
        par = new int[19][n + 1];
        tree = new List[n + 1];
        for(int i = 1; i <= n; ++i) {
            tree[i] = new ArrayList<>();
        }

        for(int i = 0; i < n - 1; ++i) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            tree[a].add(b);
            tree[b].add(a);
        }

        dfs(1, 1);
        for(int i = 1; i < 19; ++i) {
            for(int j = 1; j <= n; ++j) {
                par[i][j] = par[i - 1][par[i - 1][j]];
            }
        }

        int cmd, x;
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < q; ++i) {
            st = new StringTokenizer(br.readLine());
            cmd = Integer.parseInt(st.nextToken());
            x = Integer.parseInt(st.nextToken());
            if(cmd == 1) {
                cmd1(x);
            } else {
                sb.append(cmd2(x)).append('\n');
            }
        }

        System.out.print(sb);
        br.close();
    }
}
