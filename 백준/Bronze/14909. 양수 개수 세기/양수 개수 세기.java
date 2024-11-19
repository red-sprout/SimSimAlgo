import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        st = new StringTokenizer(br.readLine(), " ");
        int cnt = 0;
        while(st.hasMoreTokens()) {
            int val = Integer.parseInt(st.nextToken());
            cnt += val > 0 ? 1 : 0;
        }
        System.out.println(cnt);
        br.close();
    }
}
