import java.io.*;
import java.util.*;

public class Main {
    static long N, K;
    static long height(long val) {
        return (long)(Math.ceil(Math.log(val) / Math.log(K)));
    }
    static long getParent(long val) {
        return (val + (K - 1) * (val % K)) / K;
    }
    static long lca(long x, long y) {
        long d;
        long n1, n2;
        long hx = height(x), hy = height(y);
        if(hx > hy) {
            d = hx - hy;
            n1 = x;
            n2 = y;
        } else {
            d = hy - hx;
            n1 = y;
            n2 = x;
        }
        while(d-- > 0) {
            n1 = getParent(n1);
        }
        while(n1 != n2) {
            n1 = getParent(n1);
            n2 = getParent(n2);
        }
        return n1;
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        st = new StringTokenizer(br.readLine(), " ");
        N = Long.parseLong(st.nextToken());
        K = Long.parseLong(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            long x = Long.parseLong(st.nextToken());
            long y = Long.parseLong(st.nextToken());
            if(K == 1) {
                sb.append(Math.abs(x - y)).append('\n');
            } else {
                x = x * (K - 1) + 1;
                y = y * (K - 1) + 1;
                long t = lca(x, y);
                long hx = height(x), hy = height(y), ht = height(t);
                sb.append(hx + hy - 2 * ht).append('\n');
            }
        }
        System.out.print(sb);
        br.close();
    }
}