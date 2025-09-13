import java.io.*;
import java.util.*;

public class Main {
    static Map<Integer, Integer> infoA, infoB;
    static final int INF = 1_000_000_000;

    static int calc(int n) {
        int res = 0;
        while(n > 0) {
            int mod = n % 10;
            res += mod * mod;
            n /= 10;
        }
        return res;
    }

    static void setInfo(Map<Integer, Integer> info, int n) {
        info.clear();
        int idx = 0;
        while(!info.containsKey(n)) {
            info.put(n, ++idx);
            n = calc(n);
        }
    }

    static int solution(int a, int b) {
        setInfo(infoA, a);
        setInfo(infoB, b);
        int res = INF;
        for(int key : infoA.keySet()) {
            if(infoB.containsKey(key)) {
                res = Math.min(res, infoA.get(key) + infoB.get(key));
            }
        }
        return res == INF ? 0 : res;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int a, b;
        StringBuilder sb = new StringBuilder();
        infoA = new HashMap<>();
        infoB = new HashMap<>();
        while(true) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            if(a == 0 && b == 0) break;
            sb.append(a).append(" ").append(b).append(" ").append(solution(a, b)).append("\n");
        }

        System.out.print(sb);
        br.close();
    }
}
