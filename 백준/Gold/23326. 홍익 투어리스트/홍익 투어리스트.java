import java.io.*;
import java.util.*;

public class Main {
    static int n, p;
    static int[] arr;
    static TreeSet<Integer> set;

    static void init() {
        p = 1;
        set = new TreeSet<>();
        for(int i = 1; i <= n; i++) {
            if(arr[i] == 1) {
                set.add(i);
            }
        }
    }

    static void cmd1(int i) {
        if(arr[i] == 0) {
            arr[i] = 1;
            set.add(i);
        } else {
            arr[i] = 0;
            set.remove(i);
        }
    }

    static void cmd2(int x) {
        p += (x % n);
        if(p > n) {
            p -= n;
        }
    }

    static int cmd3() {
        if(set.isEmpty()) {
            return -1;
        }

        Integer res = set.ceiling(p);
        if(res == null) {
            res = set.first() + n;
        }

        return res - p;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        arr = new int[n + 1];
        int q = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        init();
        int cmd, i, x;
        StringBuilder sb = new StringBuilder();
        while(q-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            cmd = Integer.parseInt(st.nextToken());
            switch(cmd) {
                case 1:
                    i = Integer.parseInt(st.nextToken());
                    cmd1(i);
                    break;
                case 2:
                    x = Integer.parseInt(st.nextToken());
                    cmd2(x);
                    break;
                case 3:
                    sb.append(cmd3()).append('\n');
                    break;
            }
        }

        System.out.print(sb);
        br.close();
    }
}
