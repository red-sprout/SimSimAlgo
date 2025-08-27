import java.io.*;
import java.util.*;

public class Main {
    static int ptr, n, q;
    static long[] data;

    static void cmd1(int i, int x) {
        data[(ptr + i - 1) % n] += x;
    }

    static void cmd2(int s) {
        ptr = (ptr + n - s) % n;
    }

    static void cmd3(int s) {
        ptr = (ptr + s) % n;
    }

    static void print() {
        StringBuilder sb = new StringBuilder();
        for(int i = ptr; i < n; ++i) sb.append(data[i]).append(' ');
        for(int i = 0; i < ptr; ++i) sb.append(data[i]).append(' ');
        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        ptr = 0;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());
        data = new long[n];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; ++i) data[i] = Integer.parseInt(st.nextToken());

        int cmd, idx, x, s;
        while(q-- > 0) {
            st = new StringTokenizer(br.readLine());
            cmd = Integer.parseInt(st.nextToken());
            switch(cmd) {
                case 1:
                    idx = Integer.parseInt(st.nextToken());
                    x = Integer.parseInt(st.nextToken());
                    cmd1(idx, x);
                    break;
                case 2:
                    s = Integer.parseInt(st.nextToken());
                    cmd2(s);
                    break;
                case 3:
                    s = Integer.parseInt(st.nextToken());
                    cmd3(s);
                    break;
            }
        }

        print();
        br.close();
    }
}