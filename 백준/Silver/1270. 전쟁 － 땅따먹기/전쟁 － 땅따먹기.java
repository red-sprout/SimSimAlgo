import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st;
        Map<Long, Integer> mp = new HashMap<>();

        int n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while(n-- > 0) {
            long num = 0;
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            boolean flag = false;

            for(int i = 0; i < t; ++i) {
                long cur = Long.parseLong(st.nextToken());
                mp.put(cur, mp.getOrDefault(cur, 0) + 1);
                if(mp.get(cur) > t / 2) {
                    num = cur;
                    flag = true;
                }
            }

            sb.append(flag ? num : "SYJKGW").append('\n');
            mp.clear();
        }

        System.out.print(sb);
        br.close();
    }
}