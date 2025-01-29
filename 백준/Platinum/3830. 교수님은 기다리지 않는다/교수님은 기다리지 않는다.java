import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[] p;
    static long[] cost;

    static int find(int x) {
        if(x == p[x]) return x;
        int parent = find(p[x]);
        cost[x] += cost[p[x]];
        return p[x] = parent;
    }

    static void union(int x, int y, long w) {
        int px = find(x);
        int py = find(y);
        if(px == py) return;

        if(x < y) {
            p[py] = px;
            cost[py] = cost[x] - cost[y] + w;
        } else {
            p[px] = py;
            cost[px] = cost[y] - cost[x] - w;
        }
    }

    static String query(int x, int y) {
        int px = find(x);
        int py = find(y);
        if(px != py) return "UNKNOWN";
        return String.valueOf(cost[y] - cost[x]);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;

        while(true) {
            st = new StringTokenizer(br.readLine(), " ");
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            if(n == 0 && m == 0) break;

            p = new int[n + 1];
            cost = new long[n + 1];
            for(int i = 1; i <= n; i++) {
                p[i] = i;
            }

            String cmd;
            int a, b;
            long w;
            while(m-- > 0) {
                st = new StringTokenizer(br.readLine(), " ");
                cmd = st.nextToken();
                switch(cmd) {
                    case "!":
                        a = Integer.parseInt(st.nextToken());
                        b = Integer.parseInt(st.nextToken());
                        w = Long.parseLong(st.nextToken());
                        union(a, b, w);
                        break;
                    case "?":
                        a = Integer.parseInt(st.nextToken());
                        b = Integer.parseInt(st.nextToken());
                        sb.append(query(a, b)).append('\n');
                        break;
                }
            }
        }

        System.out.print(sb);
        br.close();
    }
}
