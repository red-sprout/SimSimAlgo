import java.io.*;
import java.util.*;

public class Main {
    static class Village implements Comparable<Village> {
        long x, a, sum;

        public Village(long x, long a) {
            this.x = x;
            this.a = a;
            this.sum = 0;
        }

        @Override
        public int compareTo(Village o) {
            return Long.compare(this.x, o.x);
        }
    }

    static Village[] village;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int n = Integer.parseInt(br.readLine());
        village = new Village[n];
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            long x = Long.parseLong(st.nextToken());
            long a = Long.parseLong(st.nextToken());
            village[i] = new Village(x, a);
        }

        Arrays.sort(village);
        long total = 0;
        for(int i = 0; i < n; i++) {
            total += village[i].a;
            village[i].sum = total;
        }

        long res = 0;
        long mid = (total + 1) / 2;
        for(Village v : village) {
            if(v.sum >= mid) {
                res = v.x;
                break;
            }
        }

        System.out.println(res);
        br.close();
    }
}