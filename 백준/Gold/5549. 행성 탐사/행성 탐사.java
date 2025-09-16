import java.io.*;
import java.util.*;

public class Main {
    static int m, n;
    static int[][] jungle, ocean, ice;
    static StringBuilder sb;

    static void init() {
        for(int i = 1; i <= m; ++i) {
            for(int j = 1; j <= n; ++j) {
                jungle[i][j] += jungle[i - 1][j] + jungle[i][j - 1] - jungle[i - 1][j - 1];
                ocean[i][j] += ocean[i - 1][j] + ocean[i][j - 1] - ocean[i - 1][j - 1];
                ice[i][j] += ice[i - 1][j] + ice[i][j - 1] - ice[i - 1][j - 1];
            }
        }
    }

    static void query(int r1, int c1, int r2, int c2) {
        sb.append(jungle[r2][c2] - jungle[r1 - 1][c2] - jungle[r2][c1 - 1] + jungle[r1 - 1][c1 - 1]).append(" ");
        sb.append(ocean[r2][c2] - ocean[r1 - 1][c2] - ocean[r2][c1 - 1] + ocean[r1 - 1][c1 - 1]).append(" ");
        sb.append(ice[r2][c2] - ice[r1 - 1][c2] - ice[r2][c1 - 1] + ice[r1 - 1][c1 - 1]).append("\n");
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        jungle = new int[m + 1][n + 1];
        ocean = new int[m + 1][n + 1];
        ice = new int[m + 1][n + 1];
        sb = new StringBuilder();

        int k = Integer.parseInt(br.readLine());
        for(int i = 1; i <= m; ++i) {
            String row = br.readLine();
            for(int j = 1; j <= n; ++j) {
                char c = row.charAt(j - 1);
                switch(c) {
                    case 'J':
                        jungle[i][j] = 1;
                        break;
                    case 'O':
                        ocean[i][j] = 1;
                        break;
                    default:
                        ice[i][j] = 1;
                }
            }
        }

        init();
        int r1, c1, r2, c2;
        while(k-- > 0) {
            st = new StringTokenizer(br.readLine());
            r1 = Integer.parseInt(st.nextToken());
            c1 = Integer.parseInt(st.nextToken());
            r2 = Integer.parseInt(st.nextToken());
            c2 = Integer.parseInt(st.nextToken());
            query(r1, c1, r2, c2);
        }

        System.out.print(sb);
        br.close();
    }
}
