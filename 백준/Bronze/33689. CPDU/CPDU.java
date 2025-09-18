import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String str;
        int cnt = 0;
        for(int i = 0; i < n; ++i) {
            str = br.readLine();
            if(str.charAt(0) == 'C') ++cnt;
        }
        System.out.println(cnt);
        br.close();
    }
}
