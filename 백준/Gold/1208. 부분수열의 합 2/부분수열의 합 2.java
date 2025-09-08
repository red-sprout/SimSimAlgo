import java.io.*;
import java.util.*;

public class Main {
    static int n, s;
    static long[] p;

    static void setGroup(int cur, int e, long sum, List<Long> g) {
        if(cur == e) {
            g.add(sum);
            return;
        }

        setGroup(cur + 1, e, sum, g);
        setGroup(cur + 1, e,sum + p[cur], g);
    }

    static int lowerBound(List<Long> g, long val) {
        int l = -1, r = g.size(), m;
        while(l + 1 < r) {
            m = (l + r) / 2;
            if(val <= g.get(m)) {
                r = m;
            } else {
                l = m;
            }
        }
        return r;
    }

    static int upperBound(List<Long> g, long val) {
        int l = -1, r = g.size(), m;
        while(l + 1 < r) {
            m = (l + r) / 2;
            if(val < g.get(m)) {
                r = m;
            } else {
                l = m;
            }
        }
        return r;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        p = new long[n];
        List<Long> g1 = new ArrayList<>();
        List<Long> g2 = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; ++i) {
            p[i] = Integer.parseInt(st.nextToken());
        }

        setGroup(0, n / 2, 0, g1);
        setGroup(n / 2, n, 0, g2);

        long res = 0;
        Collections.sort(g2);
        for (long sum : g1) {
            long val = s - sum;
            int l = lowerBound(g2, val);
            int u = upperBound(g2, val);
            if(l < g2.size() && g2.get(l) == val) res += u - l;
        }

        System.out.println(s == 0 ? res - 1 : res);
        br.close();
    }
}
