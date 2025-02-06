import java.io.*;
import java.util.*;

public class Main {
    static final int MOD = 1_000_000_007;

    static int gcd(int a, int b) {
        int c = a % b;
        while(c != 0) {
            a = b;
            b = c;
            c = a % b;
        }
        return b;
    }

    static long pow(int x, int y) {
        if(y == 0) return 1;
        if(y == 1) return x;
        long half = pow(x, y / 2);
        if(y % 2 == 0) {
            return half * half % MOD;
        } else {
            return half * half % MOD * x % MOD;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int m = Integer.parseInt(br.readLine());
        long res = 0;

        while(m-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            int n = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int g = gcd(Math.max(n, s), Math.min(n, s));
            n /= g;
            s /= g;
            res = (res + s * pow(n, MOD - 2) % MOD) % MOD;
        }

        System.out.println(res);
        br.close();
    }
}
