import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[] parent;

    static int find(int x) {
        if(parent[x] < 0) return x;
        return parent[x] = find(parent[x]);
    }

    static boolean union(int x, int y) {
        x = find(x);
        y = find(y);
        if(x == y) return false;
        parent[Math.max(x, y)] = Math.min(x, y);
        return true;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        parent = new int[n + 1];
        Arrays.fill(parent, -1);

        List<int[]> list = new ArrayList<>();
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            list.add(new int[] {a, b, c});
        }

        Collections.sort(list, (o1, o2) -> o1[2] - o2[2]);

        long mst = 0;
        int max = 0;
        for(int[] cur : list) {
            if(!union(cur[0], cur[1])) continue;
            mst += cur[2];
            max = cur[2];
        }

        System.out.println(mst - max);
        br.close();
    }
}
