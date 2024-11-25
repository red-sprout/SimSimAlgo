import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];
        int min = Integer.MAX_VALUE, max = -1;
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                min = Math.min(min, map[i][j]);
                max = Math.max(max, map[i][j]);
            }
        }
        int time = Integer.MAX_VALUE, height = 0;
        for(int h = max; h >= min; h--) {
            int t = 0;
            int cnt = B;
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < M; j++) {
                    int dh = Math.abs(h - map[i][j]);
                    if(h < map[i][j]) {
                        t += 2 * dh;
                        cnt += dh;
                    } else {
                        t += dh;
                        cnt -= dh;
                    }
                }
            }
            if(cnt >= 0 && t < time) {
                time = t;
                height = h;
            }
        }
        System.out.println(time + " " + height);
        br.close();
    }
}