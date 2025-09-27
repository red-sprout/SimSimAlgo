import java.io.*;
import java.util.*;

public class Main {
    static int n, k;
    static long[] a, b;

    static int upperBound(long[] arr, long x) {
        int l = -1, r = n, m;
        while(l + 1 < r) {
            m = (l + r) >> 1;
            if(arr[m] > x) {
                r = m;
            } else {
                l = m;
            }
        }
        return l;
    }

    static boolean validate(long x) {
        int res = 0;
        for(int i = 0; i < n; ++i) {
            long target = x / a[i];
            res += upperBound(b, target) + 1;
        }
        return res >= k;
    }

    static long solution() {
        Arrays.sort(a);
        Arrays.sort(b);
        long l = 0, r = a[n - 1] * b[n - 1] + 1, m;
        while(l + 1 < r) {
            m = (l + r) >> 1;
            if(validate(m)) {
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
        k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        a = new long[n];
        for(int i = 0; i < n; ++i) {
            a[i] = Long.parseLong(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        b = new long[n];
        for(int i = 0; i < n; ++i) {
            b[i] = Long.parseLong(st.nextToken());
        }

        System.out.println(solution());
        br.close();
    }
}
