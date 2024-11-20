import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] parent;
    static int find(int i) {
        if (i == parent[i])
            return i;
        return parent[i] = find(parent[i]);
    }
    static boolean union(int i, int j) {
        i = find(i);
        j = find(j);
        if (i == j)
            return false;
        if (i > j) {
            parent[i] = j;
        } else {
            parent[j] = i;
        }
        return true;
    }
    static class Planet {
        int id, x, y, z;

        Planet(int id, int x, int y, int z) {
            this.id = id;
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }
    static class Edge implements Comparable<Edge>{
        int u, v, d;

        Edge(int u, int v, int d) {
            this.u = u;
            this.v = v;
            this.d = d;
        }

        @Override
        public int compareTo(Edge e) {
            return Integer.compare(d, e.d);
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        N = Integer.parseInt(br.readLine());
        parent = new int[N];
        for (int i = 0; i < N; i++) {
            parent[i] = i;
        }
        ArrayList<Planet> pList = new ArrayList<>();
        ArrayList<Edge> eList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            pList.add(new Planet(i, x, y, z));
        }
        Collections.sort(pList, (o1, o2) -> Integer.compare(o1.x, o2.x));
        for(int i = 1; i < N; i++) {
            int u = pList.get(i - 1).id;
            int v = pList.get(i).id;
            int d = pList.get(i).x - pList.get(i - 1).x;
            eList.add(new Edge(u, v, d));
        }
        Collections.sort(pList, (o1, o2) -> Integer.compare(o1.y, o2.y));
        for (int i = 1; i < N; i++) {
            int u = pList.get(i - 1).id;
            int v = pList.get(i).id;
            int d = pList.get(i).y - pList.get(i - 1).y;
            eList.add(new Edge(u, v, d));
        }
        Collections.sort(pList, (o1, o2) -> Integer.compare(o1.z, o2.z));
        for (int i = 1; i < N; i++) {
            int u = pList.get(i - 1).id;
            int v = pList.get(i).id;
            int d = pList.get(i).z - pList.get(i - 1).z;
            eList.add(new Edge(u, v, d));
        }
        Collections.sort(eList);
        int cnt = 0, mst = 0;
        for (Edge e : eList) {
            if (union(e.u, e.v)) {
                cnt++;
                mst += e.d;
            }
            if (cnt == N - 1)
                break;
        }
        System.out.println(mst);
        br.close();
    }
}