import java.io.*;
import java.util.*;

public class Main {
    static int n, l, r, x;
    static int[] a;

    static boolean validate(int v) {
        int easy = -1, diff = -1, sum = 0;
        for (int i = 0; i < n; i++) {
            if ((v & (1 << i)) == 0) continue;
            if (easy == -1) easy = a[i];
            diff = a[i];
            sum += a[i];
        }
        return l <= sum && sum <= r && diff - easy >= x;
    }

    static int dfs(int cur, int v) {
        if (cur == n) return validate(v) ? 1 : 0;
        int res = 0;
        res += dfs(cur + 1, v);
        res += dfs(cur + 1, v | (1 << cur));
        return res;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());

        a = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(a);
        System.out.println(dfs(0, 0));
    }
}
