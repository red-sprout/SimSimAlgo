import java.io.*;
import java.util.*;

public class Main {
    static int findIdx(int n, String s, int idx, char c) {
        for(int i = idx; i < n; ++i) {
            if(s.charAt(i) == c) return i;
        }
        return n;
    }

    static int findYonsei(int n, String s) {
        int idx = 0;
        idx = findIdx(n, s, idx, 'Y');
        idx = findIdx(n, s, idx, 'O');
        idx = findIdx(n, s, idx, 'N');
        idx = findIdx(n, s, idx, 'S');
        idx = findIdx(n, s, idx, 'E');
        idx = findIdx(n, s, idx, 'I');
        return idx;
    }

    static int findKorea(int n, String s) {
        int idx = 0;
        idx = findIdx(n, s, idx, 'K');
        idx = findIdx(n, s, idx, 'O');
        idx = findIdx(n, s, idx, 'R');
        idx = findIdx(n, s, idx, 'E');
        idx = findIdx(n, s, idx, 'A');
        return idx;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        int n = s.length();
        int yIdx = findYonsei(n, s);
        int kIdx = findKorea(n, s);

        System.out.println(yIdx > kIdx ? "KOREA" : "YONSEI");
        br.close();
    }
}