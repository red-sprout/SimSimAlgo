import java.io.*;
import java.util.*;

public class Main {
    static List<Long> getDistanceList(long x, long y, List<long[]> xyList) {
        List<Long> res = new ArrayList<>();
        for (long[] xy : xyList) {
            res.add(Math.abs(x - xy[0]) + Math.abs(y - xy[1]));
        }
        Collections.sort(res);
        return res;
    }

    static long[] solution(int n, List<long[]> xyList) {
        long[] res = new long[n + 1];
        Arrays.fill(res, Long.MAX_VALUE);
        List<Long> xList = new ArrayList<>();
        List<Long> yList = new ArrayList<>();
        for (long[] xy : xyList) {
            xList.add(xy[0]);
            yList.add(xy[1]);
        }

        for (long x : xList) {
            for (long y : yList) {
                List<Long> distanceList = getDistanceList(x, y, xyList);
                long ans = 0;
                for (int k = 0; k <= n; ++k) {
                    res[k] = Math.min(res[k], ans);
                    if (k < n)
                        ans += distanceList.get(k);
                }
            }
        }

        return res;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        long x, y;
        List<long[]> xyList = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            x = Long.parseLong(st.nextToken());
            y = Long.parseLong(st.nextToken());
            xyList.add(new long[] { x, y });
        }

        StringBuilder sb = new StringBuilder();
        long[] ans = solution(n, xyList);
        for (int i = 1; i <= n; ++i) {
            sb.append(ans[i]).append(' ');
        }
        System.out.println(sb.toString().trim());
        br.close();
    }
}
