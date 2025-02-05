import java.io.*;
import java.util.*;

public class Main {
    static int maxX;
    static final int X = 8191;
    static final int P = 1_000_000_007;

    static void init(String str) {
        maxX = 1;
        for(int i = 0; i < str.length(); i++) {
            maxX = maxX * X % P;
        }
    }

    static long hash(String str) {
        long res = 0;
        for(int i = 0; i < str.length(); i++) {
            res = (res + str.charAt(i)) % P;
            res = res * X % P;
        }
        return res;
    }

    static int rollingHash(String str1, String str2) {
        long h = hash(str2);
        long curH = 0;
        int idx = 0, cnt = 0, l = str2.length();
        while(idx < str1.length() - l + 1) {
            curH = hash(str1.substring(idx, idx + l));
            if(h == curH) {
                idx += l;
                cnt++;
            } else {
                idx++;
            }
        }
        return cnt;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str1 = br.readLine();
        String str2 = br.readLine();
        init(str2);
        System.out.println(rollingHash(str1, str2));
        br.close();
    }
}