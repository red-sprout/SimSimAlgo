import java.io.*;
import java.util.*;

public class Main {
    static List<int[][]> list;

    static void init() {
        list = new ArrayList<>();
        list.add(new int[][] {
                {1, 1, 1, 1},
        });
        list.add(new int[][] {
                {1},
                {1},
                {1},
                {1},
        });
        list.add(new int[][] {
                {1, 1, 0},
                {0, 1, 1},
        });
        list.add(new int[][] {
                {0, 1},
                {1, 1},
                {1, 0},
        });
        list.add(new int[][] {
                {1, 1, 1},
                {0, 0, 1},
        });
        list.add(new int[][] {
                {0, 1},
                {0, 1},
                {1, 1},
        });
        list.add(new int[][] {
                {1, 0, 0},
                {1, 1, 1},
        });
        list.add(new int[][] {
                {1, 1},
                {1, 0},
                {1, 0},
        });
        list.add(new int[][] {
                {1, 1, 1},
                {0, 1, 0},
        });
        list.add(new int[][] {
                {0, 1},
                {1, 1},
                {0, 1},
        });
        list.add(new int[][] {
                {0, 1, 0},
                {1, 1, 1},
        });
        list.add(new int[][] {
                {1, 0},
                {1, 1},
                {1, 0},
        });
        list.add(new int[][] {
                {1, 1},
                {1, 1},
        });
    }

    static int getVal(int n, int[][] mp, int r, int c, int[][] p) {
        int res = 0;
        if(r + p.length > n || c + p[0].length > n) return Integer.MIN_VALUE;
        for(int i = 0; i < p.length; ++i) {
            for(int j = 0; j < p[i].length; ++j) {
                res += mp[r + i][c + j] * p[i][j];
            }
        }
        return res;
    }

    static int solution(int n, int[][] mp) {
        int res = Integer.MIN_VALUE;
        for(int[][] p : list) {
            for(int i = 0; i < n; ++i) {
                for(int j = 0; j < n; ++j) {
                    res = Math.max(res, getVal(n, mp, i, j, p));
                }
            }
        }
        return res;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st; init();

        int order = 0;
        StringBuilder sb = new StringBuilder();
        while(true) {
            int n = Integer.parseInt(br.readLine().trim());
            if(n == 0) break;

            int[][] mp = new int[n][n];
            for(int i = 0; i < n; ++i) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < n; ++j) {
                    mp[i][j] = Integer.parseInt(st.nextToken().trim());
                }
            }

            sb.append(++order).append(". ").append(solution(n, mp)).append("\n");
        }

        System.out.print(sb);
        br.close();
    }
}
