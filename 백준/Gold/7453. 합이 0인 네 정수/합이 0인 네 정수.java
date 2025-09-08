import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static long[] A, B, C, D, ab, cd;

    static int lowerBound(long val) {
        int l = -1, r = n * n, m;
        while(l + 1 < r) {
            m = (l + r) / 2;
            if(cd[m] < val) {
                l = m;
            } else {
                r = m;
            }
        }
        return r;
    }

    static int upperBound(long val) {
        int l = -1, r = n * n, m;
        while(l + 1 < r) {
            m = (l + r) / 2;
            if(cd[m] <= val) {
                l = m;
            } else {
                r = m;
            }
        }
        return r;
    }

    static long solution() {
        ab = new long[n * n];
        cd = new long[n * n];
        int idx = 0;
        for(int i = 0; i < n; ++i) {
            for(int j = 0; j < n; ++j) {
                ab[idx] = A[i] + B[j];
                cd[idx] = C[i] + D[j];
                ++idx;
            }
        }
        Arrays.sort(ab);
        Arrays.sort(cd);

        long res = 0;
        for(int i = 0; i < n * n; ++i) {
            long op = -ab[i];
            int l = lowerBound(op);
            int u = upperBound(op);
            if (l < n * n && cd[l] == op) {
                res += u - l;
            }
        }

        return res;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        A = new long[n];
        B = new long[n];
        C = new long[n];
        D = new long[n];

        for(int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            A[i] = Integer.parseInt(st.nextToken());
            B[i] = Integer.parseInt(st.nextToken());
            C[i] = Integer.parseInt(st.nextToken());
            D[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solution());
        br.close();
    }
}
