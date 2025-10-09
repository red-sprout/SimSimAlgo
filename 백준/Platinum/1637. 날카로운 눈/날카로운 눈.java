import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static long[] a, b, c;

    static long cnt(long m) {
        long sum = 0;
        for(int i = 0; i < n; ++i) {
            if(m < a[i]) continue;

            long h = Math.min(m, c[i]);
            if(h < a[i]) continue;

            sum += Math.max(0, (h - a[i]) / b[i] + 1);
        }
        return sum;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        a = new long[n];
        b = new long[n];
        c = new long[n];
        for(int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            a[i] = Integer.parseInt(st.nextToken());
            c[i] = Integer.parseInt(st.nextToken());
            b[i] = Integer.parseInt(st.nextToken());
        }

        long l = 0, r = (1L << 32), m;
        while(l + 1 < r) {
            m = (l + r) >> 1;
            if((cnt(m) & 1) != 0) {
                r = m;
            } else {
                l = m;
            }
        }

        System.out.println((cnt(r) & 1) != 0 ? r + " " + (cnt(r) - cnt(l)) : "NOTHING");
        br.close();
    }
}
