import java.io.*;
import java.util.*;

public class Main {
    static class Point {
        int x, y, r;

        Point(int x, int y, int r) {
            this.x = x;
            this.y = y;
            this.r = r;
        }
    }

    static int n;
    static int[] parent;
    static Point[] point;

    static int find(int x) {
        if(parent[x] < 0) return x;
        return parent[x] = find(parent[x]);
    }

    static void union(int x, int y) {
        x = find(x);
        y = find(y);
        int u = Math.min(x, y);
        int v = Math.max(x, y);
        if(x == y) return;
        parent[v] = u;
    }

    static int dist(Point p1, Point p2) {
        return (p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while(T-- > 0) {
            n = Integer.parseInt(br.readLine());
            parent = new int[n];
            point = new Point[n];
            Arrays.fill(parent, -1);

            int x, y, r;
            for(int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                x = Integer.parseInt(st.nextToken());
                y = Integer.parseInt(st.nextToken());
                r = Integer.parseInt(st.nextToken());
                point[i] = new Point(x, y, r);
                for(int j = 0; j < i; j++) {
                    Point p = point[j];
                    if(dist(p, point[i]) <= (r + p.r) * (r + p.r)) {
                        union(i, j);
                    }
                }
            }

            int res = 0;
            for(int i = 0; i < n; i++) {
                if(parent[i] < 0) {
                    res++;
                }
            }

            sb.append(res).append('\n');
        }

        System.out.print(sb);
        br.close();
    }
}