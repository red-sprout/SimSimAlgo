import java.io.*;
import java.util.*;

public class Main {

    static int n, k;
    static int[][] lecture;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        lecture = new int[n][3];

        for(int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            int sum = 0;
            for(int j = 0; j < 3; ++j) {
                lecture[i][j] = Integer.parseInt(st.nextToken());
                sum += lecture[i][j];
            }
            for(int j = 0; j < 3; ++j) {
                lecture[i][j] = sum - lecture[i][j];
            }
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        int res = 0;
        for(int j = 0; j < 3; ++j) {
            pq.clear();
            int sum = 0;
            for(int i = 0; i < n; ++i) {
                pq.add(lecture[i][j]);
            }
            for(int i = 0; i < k; ++i) {
                sum += pq.poll();
            }
            res = Math.max(res, sum);
        }

        System.out.println(res);
        br.close();
    }
}
