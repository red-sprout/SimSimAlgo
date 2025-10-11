import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        String[] strArr = new String[n];
        for(int i = 0; i < n; ++i) {
            strArr[i] = br.readLine();
        }

        List<Character> list = new ArrayList<>();
        for(char c = 'a'; c <= 'z'; ++c) {
            int cnt = 0;
            for(String str : strArr) {
                if(str.charAt(0) == c) ++cnt;
            }
            if(cnt >= 5) list.add(c);
        }

        if(list.isEmpty()) {
            System.out.println("PREDAJA");
        } else {
            StringBuilder sb = new StringBuilder();
            for(char c : list) {
                sb.append(c);
            }
            System.out.println(sb);
        }
        
        br.close();
    }
}
