import java.io.*;
import java.util.*;

public class Main {
    static class Edge {
        int id, u, v;

        Edge(int id, int u, int v) {
            this.id = id;
            this.u = u;
            this.v = v;
        }
    }

    static int n, m;
    static int[] par;
    static Set<Integer>[] edgeSet;
    static List<Integer>[] g;
    static List<Edge> edges;
    static Map<Integer, Edge> map;

    static int find(int x) {
        if(par[x] == x) return x;
        return par[x] = find(par[x]);
    }

    static boolean union(int id, int x, int y) {
        x = find(x);
        y = find(y);
        if(x == y) return false;
        if(edgeSet[x].size() > edgeSet[y].size()) {
            par[y] = x;
            for(int e : edgeSet[y]) edgeSet[x].add(e);
            edgeSet[x].add(id);
            edgeSet[y].clear();
        } else {
            par[x] = y;
            for(int e : edgeSet[x]) edgeSet[y].add(e);
            edgeSet[y].add(id);
            edgeSet[x].clear();
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        par = new int[n + 1];
        edgeSet = new Set[n + 1];
        g = new List[n + 1];
        edges = new ArrayList<>();
        map = new HashMap<>();
        for(int i = 1; i <= n; ++i) {
            par[i] = i;
            edgeSet[i] = new HashSet<>();
            g[i] = new ArrayList<>();
        }

        for(int i = 1; i <= m; ++i) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            Edge e = new Edge(i, Math.min(u, v), Math.max(u, v));
            edges.add(e);
            map.put(i, e);
        }

        br.close();

        if(n <= 2) {
            System.out.println(-1);
            return;
        }

        int cnt = 0;
        Collections.sort(edges, (e1, e2) -> e1.u == e2.u ? e1.v - e2.v : e1.u - e2.u);
        for(Edge e : edges) {
            if(cnt == n - 2) break;
            if(union(e.id, e.u, e.v)) ++cnt;
        }

        if(cnt != n - 2) {
            System.out.println(-1);
            return;
        }

        int t1 = 0, t2 = 0;
        for(int i = 1; i <= n; ++i) {
            if(par[i] != i) continue;
            if(t1 == 0) t1 = i;
            else if(t2 == 0) t2 = i;
            else {
                System.out.println(-1);
                return;
            }
        }

        if(edgeSet[t1].size() == edgeSet[t2].size()) {
            System.out.println(-1);
            return;
        }

        if(edgeSet[t1].size() < edgeSet[t2].size()) {
            int tmp = t1;
            t1 = t2;
            t2 = tmp;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(edgeSet[t1].size() + 1).append(' ').append(edgeSet[t2].size() + 1).append('\n');

        TreeSet<Integer> resultSet = new TreeSet<>();
        resultSet.add(t1);
        for(int id : edgeSet[t1]) {
            resultSet.add(map.get(id).u);
            resultSet.add(map.get(id).v);
        }
        for(int i : resultSet) {
            sb.append(i).append(' ');
        }
        sb.append('\n');

        for(int e : edgeSet[t1]) {
            sb.append(e).append(' ');
        }
        sb.append('\n');

        resultSet.clear();
        resultSet.add(t2);
        for(int id : edgeSet[t2]) {
            resultSet.add(map.get(id).u);
            resultSet.add(map.get(id).v);
        }
        for(int i : resultSet) {
            sb.append(i).append(' ');
        }
        sb.append('\n');

        for(int e : edgeSet[t2]) {
            sb.append(e).append(' ');
        }
        sb.append('\n');

        System.out.print(sb);
    }
}