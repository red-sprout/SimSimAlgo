import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine(), " ");
        long x = Long.parseLong(st.nextToken());
        long k = Long.parseLong(st.nextToken());

        int cnt = Long.toBinaryString(k).length();
        int idx = 0;
        long res = 0;
        for(int i = 0; i < cnt; ) {
            if((x & (1L << idx)) == 0) {
                if((k & (1L << i)) != 0) res |= 1L << idx;
                i++;
            }
            idx++;
        }
        System.out.println(res);
        br.close();
    }
}