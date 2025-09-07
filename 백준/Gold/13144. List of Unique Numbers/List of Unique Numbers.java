import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; ++i) arr[i] = Integer.parseInt(st.nextToken());

        int l = 0, r = 0;
        long res = 0;
        int[] cnt = new int[100001];
        while(l < n) {
            while(r < n) {
                if(cnt[arr[r]] == 1) break;
                ++cnt[arr[r++]];
            }
            res += r - l;
            cnt[arr[l++]]--;
        }

        System.out.println(res);
        br.close();
    }
}
