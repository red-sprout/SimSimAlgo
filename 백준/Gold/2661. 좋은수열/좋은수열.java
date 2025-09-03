import java.io.*;
import java.util.Arrays;

public class Main {
    static int n;
    static boolean flag = false;

    static boolean checkBad(int[] res, int end) {
        for(int i = 0; i < end; ++i) {
            for(int j = i; j < end; ++j) {
                if((end - 1) - (j + 1) < j - i) break;
                boolean same = true;
                for(int k = 0; k <= (j - i); ++k) {
                    if(res[j + 1 + k] != res[i + k]) {
                        same = false;
                        break;
                    }
                }
                if(same) return true;
            }
        }
        return false;
    }

    static void dfs(int cur, int[] res) {
        for(int i = 0; i <= cur; ++i) {
            if(checkBad(res, i)) return;
        }

        if(cur == n) {
            flag = true;
            return;
        }

        for(int i = 1; i <= 3; ++i) {
            res[cur] = i;
            dfs(cur + 1, res);
            if(flag) return;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        int[] res = new int[n];
        dfs(0, res);

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; ++i) {
            sb.append(res[i]);
        }
        System.out.println(sb);

        br.close();
    }
}
