import java.util.*;
import java.io.*;

public class Main {
    static boolean meet(int m, int n, int[][] info) {
        boolean[] exists = new boolean[1 << 5];
        for(int i = 0; i < n; ++i) {
            int bit = 0;
            for(int j = 0; j < 5; ++j) {
                if(info[i][j] >= m) {
                    bit |= 1 << j;
                }
            }
            exists[bit] = true;
        }

        for(int i = 0; i < (1 << 5); ++i) {
            for(int j = 0; j < (1 << 5); ++j) {
                for(int k = 0; k < (1 << 5); ++k) {
                    if(exists[i] && exists[j] && exists[k] && (i | j | k) == (1 << 5) - 1) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    static int solution(int n, int[][] info) {
        int l = 0;
        int r = 1_000_000_001;
        int m;

        while(l + 1 < r) {
            m = (l + r) >> 1;
            if(meet(m, n, info)) {
                l = m;
            } else {
                r = m;
            }
        }

        return l;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[][] info = new int[n][5];
        for(int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 5; ++j) {
                info[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(solution(n, info));
        br.close();
    }
}
