import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] a = new int[n];
        for(int i = 0; i < n; ++i) {
            a[i] = Integer.parseInt(br.readLine());
        }

        for(int i = 1; i <= m; ++i) {
            for(int j = 0; j < n - 1; ++j) {
                if(a[j] % i > a[j + 1] % i) {
                    int tmp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = tmp;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; ++i) {
            sb.append(a[i]).append("\n");
        }

        System.out.print(sb);
        br.close();
    }
}
