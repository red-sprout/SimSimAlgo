import java.io.*;
import java.util.*;

public class Main {
    static class Info implements Comparable<Info> {
        String type; // WARP, EXIT
        int a, b, t;

        Info(int x, int t) {
            this.type = "EXIT";
            this.a = x;
            this.t = t;
        }

        Info(int a, int b, int t) {
            this.type = "WARP";
            this.a = a;
            this.b = b;
            this.t = t;
        }

        @Override
        public int compareTo(Info i) {
            return Integer.compare(this.t, i.t);
        }
    }

    static int n, m;
    static int[] par;
    static boolean[] isExit;

    static void init() {
        par = new int[n + 1];
        isExit = new boolean[n + 1];
        for(int i = 1; i <= n; ++i) {
            par[i] = i;
        }
    }

    static int find(int x) {
        if(x == par[x]) return x;
        return par[x] = find(par[x]);
    }

    static boolean union(int x, int y) {
        if(x == y) return false;

        boolean exit = isExit[x] || isExit[y];
        if(x > y) {
            par[x] = y;
            isExit[x] = isExit[y] = exit;
        } else {
            par[y] = x;
            isExit[x] = isExit[y] = exit;
        }

        return true;
    }

    static int setWarp(int a, int b, int t) {
        a = find(a);
        b = find(b);

        if(isExit[a] && isExit[b]) return 0;
        if(!union(a, b)) return 0;
        return t;
    }

    static int setExit(int x, int t) {
        x = find(x);

        if(isExit[x]) return 0;

        isExit[x] = true;
        return t;
    }

    static int solution(PriorityQueue<Info> infos) {
        int res = 0;
        init();
        while(!infos.isEmpty()) {
            Info info = infos.poll();
            if(info.type.equals("WARP")) {
                res += setWarp(info.a, info.b, info.t);
            } else {
                res += setExit(info.a, info.t);
            }
        }
        return res;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        PriorityQueue<Info> infos = new PriorityQueue<>();
        for(int i = 0; i < m; ++i) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            infos.add(new Info(a, b, t));
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; ++i) {
            int t = Integer.parseInt(st.nextToken());
            infos.add(new Info(i, t));
        }

        System.out.println(solution(infos));
        br.close();
    }
}
