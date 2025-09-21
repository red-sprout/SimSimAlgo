import java.io.*;
import java.util.*;

public class Main {
    static int minVal = Integer.MAX_VALUE, maxVal = 0;

    static int stoi(String s) {
        return Integer.parseInt(s);
    }

    static String itos(int i) {
        return String.valueOf(i);
    }

    static int cal(String s) {
        int res = 0;
        for(int i = 0; i < s.length(); ++i) {
            int num = s.charAt(i) - '0';
            if((num | 1) == num) ++res;
        }
        return res;
    }

    static void dfs(String s, int val) {
        int res = cal(s);

        if(s.length() == 1) {
            minVal = Math.min(minVal, res + val);
            maxVal = Math.max(maxVal, res + val);
            return;
        }

        if(s.length() == 2) {
            int i1 = stoi(s.substring(0, 1));
            int i2 = stoi(s.substring(1));
            dfs(itos(i1 + i2), val + res);
            return;
        }

        for(int i = 1; i < s.length(); ++i) {
            for(int j = i + 1; j < s.length(); ++j) {
                int i1 = stoi(s.substring(0, i));
                int i2 = stoi(s.substring(i, j));
                int i3 = stoi(s.substring(j));
                dfs(itos(i1 + i2 + i3), val + res);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String n = br.readLine();
        dfs(n, 0);
        System.out.println(minVal + " " + maxVal);
        br.close();
    }
}
