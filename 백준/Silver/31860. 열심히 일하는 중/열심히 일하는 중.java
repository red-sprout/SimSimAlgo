import java.io.*;
import java.util.*;

public class Main {
    static int n, m, k;
    static PriorityQueue<Integer> pq;

    static int query(int sati) {
        if(pq.isEmpty()) return sati / 2;

        int p = pq.poll();
        if(p - m > k) pq.add(p - m);

        return p + sati / 2;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        pq = new PriorityQueue<>(Collections.reverseOrder());
        for(int i = 0; i < n; ++i) {
            int work = Integer.parseInt(br.readLine());
            if(work > k) pq.add(work);
        }

        int day = 0, sati = 0;
        StringBuilder sb = new StringBuilder();
        while(!pq.isEmpty()) {
            ++day;
            sati = query(sati);
            sb.append(sati).append('\n');
        }

        System.out.println(day);
        System.out.print(sb);
        br.close();
    }
}
