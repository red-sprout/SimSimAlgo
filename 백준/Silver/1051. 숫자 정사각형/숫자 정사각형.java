import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        
        int[][] map = new int[n][m];
        for(int i = 0; i < n; i++) {
            String row = br.readLine();
            for(int j = 0; j < m; j++) {
                map[i][j] = row.charAt(j) - '0';
            }
        }
        
        int max = 0;
        for(int l = 1; l <= Math.min(n, m); l++) {
            for(int i = 0; i <= n - l; i++) {
                for(int j = 0; j <= m - l; j++) {
                    if(map[i][j] == map[i + l - 1][j]
                       && map[i][j] == map[i][j + l - 1] 
                       && map[i][j] == map[i + l - 1][j + l - 1]) {
                        max = l * l;
                    }
                }
            }
        }
        
        System.out.println(max);
        br.close();
    }
}