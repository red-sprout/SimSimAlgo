import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        long[] x = new long[n];
        long sum = 0;
        long sumsq = 0;

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; ++i) {
            x[i] = Integer.parseInt(st.nextToken());
            sum += x[i];
            sumsq += x[i] * x[i];
        }

        System.out.println((sum * sum - sumsq) / 2);
        br.close();
    }
}
