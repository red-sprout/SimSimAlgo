import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] h;

    static int twoPointer(int i, int j, int val) {
        int res = val, l = i + 1, r = n - 1;
        int cur = h[i] + h[j];
        while(l < r) {
            if(r == j || r == i) {
                --r;
                continue;
            }

            if(l == i || l == j) {
                ++l;
                continue;
            }

            int nxt = h[l] + h[r];
            res = Math.min(res, Math.abs(nxt - cur));

            if(nxt < cur) {
                ++l;
            } else if(nxt > cur) {
                --r;
            } else {
                break;
            }
        }
        return res;
    }

    static int solution() {
        int res = 2_000_000_001;
        for(int i = 0; i < n; ++i) {
            for(int j = i + 1; j < n; ++j) {
                res = twoPointer(i, j, res);
            }
        }
        return res;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        h = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; ++i) {
            h[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(h);
        System.out.println(solution());
        br.close();
    }
}
