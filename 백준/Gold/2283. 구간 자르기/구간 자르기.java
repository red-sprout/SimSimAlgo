import java.io.*;
import java.util.*;

public class Main {
    static final int SZ = 1_000_002;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        long k = Long.parseLong(st.nextToken());
        int[] arr = new int[SZ];

        for(int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            ++arr[l + 1];
            --arr[r + 1];
        }

        for(int i = 1; i < SZ; ++i) {
            arr[i] += arr[i - 1];
        }

        int a = 0, b = 0;
        long sum = 0;
        boolean flag = false;

        while(a < SZ - 1) {
            if(sum == k) {
                flag = true;
                break;
            }

            if(sum < k) {
                if(b == SZ - 1) break;
                sum += arr[b++];
            } else {
                sum -= arr[a++ + 1];
            }
        }

        System.out.println(flag ? a + " " + (b - 1) : 0 + " " + 0);
        br.close();
    }
}
