import java.io.*;
import java.util.*;

public class Main {
    static int n, q, root = 1;

    static long s(int v) {
        long res = 0;
        int i = 0;
        while(true) {
            int l = v * (1 << i);
            int r = Math.min(n, l + (1 << i) - 1);
            if(l > r) break;
            res += (long) (r + l) * (r - l + 1) / 2;
            i += 1;
        }
        return res;
    }

    static int lca(int a, int b) {
        int da = (int) Math.floor(Math.log(a) / Math.log(2));
        int db = (int) Math.floor(Math.log(b) / Math.log(2));
        int u, v, d;
        if(da > db) {
            u = a; v = b; d = da - db;
        } else {
            u = b; v = a; d = db - da;
        }

        while(d-- > 0) u >>= 1;
        if(u == v) return u;
        while(u != v) {
            u >>= 1;
            v >>= 1;
        }
        return u;
    }

    static void cmd1(int v) {
        root = v;
    }

    static long cmd2(int v) {
        if(root == v) {
            return s(1);
        } else if(lca(root, v) == v) {
            if(lca(root, v << 1) == v << 1) {
                return s(1) - s(v << 1);
            } else {
                return s(1) - s(v << 1 | 1);
            }
        } else {
            return s(v);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        int cmd, v;
        StringBuilder sb = new StringBuilder();
        while(q-- > 0) {
            st = new StringTokenizer(br.readLine());
            cmd = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            if(cmd == 1) {
                cmd1(v);
            } else {
                sb.append(cmd2(v)).append("\n");
            }
        }

        System.out.print(sb);
        br.close();
    }
}
