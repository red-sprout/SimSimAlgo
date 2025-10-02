import java.io.*;
import java.util.*;

public class Main {
    static int[] cnt = new int[26];

    static int[] solution(String w, int k) {
        if(k == 1) return new int[] {1, 1};

        int l = 0, r = 0, len = w.length();
        Arrays.fill(cnt, 0);
        ++cnt[w.charAt(0) - 'a'];

        for(int i = 0; i < len; ++i) {
            ++cnt[w.charAt(i) - 'a'];
        }

        int min = 1_000_000_000;
        int max = -1;
        for(int i = 0; i < len; ++i) {
            if(cnt[w.charAt(i) - 'a'] < k) continue;
            int tmp = 1;
            for(int j = i + 1; j < len; ++j) {
                if(w.charAt(i) == w.charAt(j)) ++tmp;
                if(tmp == k) {
                    min = Math.min(min, j - i + 1);
                    max = Math.max(max, j - i + 1);
                    break;
                }
            }
        }

        if(min == 1_000_000_000 || max == -1) {
            return new int[] {-1, -1};
        } else {
            return new int[] {min, max};
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while(t-- > 0) {
            String w = br.readLine();
            int k = Integer.parseInt(br.readLine());
            int[] res = solution(w, k);
            if(res[0] == -1) {
                sb.append(-1).append("\n");
            } else {
                sb.append(res[0]).append(" ").append(res[1]).append("\n");
            }
        }

        System.out.print(sb);
        br.close();
    }
}
