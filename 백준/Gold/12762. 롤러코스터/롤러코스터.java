import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        int[] dpL = new int[n];
        int[] dpR = new int[n];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; ++i) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i < n; ++i) {
            dpL[i] = 1;
            for(int j = 0; j < i; ++j) {
                if(arr[j] > arr[i]) {
                    dpL[i] = Math.max(dpL[i], dpL[j] + 1);
                }
            }
        }

        for(int i = n - 1; i >= 0; --i) {
            dpR[i] = 1;
            for(int j = n - 1; j > i; --j) {
                if(arr[i] < arr[j]) {
                    dpR[i] = Math.max(dpR[i], dpR[j] + 1);
                }
            }
        }

        int res = 1;
        for(int i = 0; i < n; ++i) {
            res = Math.max(res, dpL[i] + dpR[i] - 1);
        }

        System.out.println(res);
        br.close();
    }
}
