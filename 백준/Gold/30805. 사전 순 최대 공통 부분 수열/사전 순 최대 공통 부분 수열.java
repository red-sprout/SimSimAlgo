import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[] a, b;

    static int[] getIdx(int ia, int ib) {
        int[] idx = { -1, -1 };
        int val = -1;
        for(int i = ia; i < n; i++) {
            for(int j = ib; j < m; j++) {
                if(a[i] == b[j] && val < a[i]) {
                    idx[0] = i;
                    idx[1] = j;
                    val = a[i];
                }
            }
        }
        return idx;
    }

    static List<Integer> getList() {
        List<Integer> list = new ArrayList<>();
        int ia = 0, ib = 0;

        while(ia < n && ib < m) {
            int[] idx = getIdx(ia, ib);
            if(idx[0] == -1) {
                break;
            }
            list.add(a[idx[0]]);
            ia = idx[0] + 1;
            ib = idx[1] + 1;
        }

        return list;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        n = Integer.parseInt(br.readLine());
        a = new int[n];
        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        m = Integer.parseInt(br.readLine());
        b = new int[m];
        st = new StringTokenizer(br.readLine(), " ");
        for(int j = 0; j < m; j++) {
            b[j] = Integer.parseInt(st.nextToken());
        }

        List<Integer> list = getList();

        StringBuilder sb = new StringBuilder();
        sb.append(list.size()).append('\n');
        for(int val : list) {
            sb.append(val).append(' ');
        }

        if(!list.isEmpty()) {
            sb.append('\n');
        }

        System.out.print(sb);
        br.close();
    }
}
