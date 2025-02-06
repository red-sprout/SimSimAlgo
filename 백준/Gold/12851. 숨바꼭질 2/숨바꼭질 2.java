import java.io.*;
import java.util.*;

public class Main {
    static class Edge implements Comparable<Edge> {
        int f, s;

        Edge(int f, int s) {
            this.f = f;
            this.s = s;
        }

        @Override
        public String toString() {
            return "[" + f + ", " + s + "]";
        }

        @Override
        public int compareTo(Edge o) {
            return this.s - o.s;
        }
    }
    static int[] dist = new int[200_001];
    static int solution(int n, int k) {
        Arrays.fill(dist, Integer.MAX_VALUE);
        PriorityQueue<Edge> q = new PriorityQueue<>();
        dist[n] = 0;
        q.offer(new Edge(n, 0));
        int cnt = 0;
        while(!q.isEmpty()) {
            Edge cur = q.poll();
            if(dist[k] < cur.s) break;
            if(cur.f == k) {
                cnt++;
                continue;
            }

            if(cur.f > 0 && dist[cur.f - 1] >= cur.s + 1) {
                dist[cur.f - 1] = cur.s + 1;
                q.offer(new Edge(cur.f - 1, cur.s + 1));
            }

            if(cur.f < 200_000 && dist[cur.f + 1] >= cur.s + 1) {
                dist[cur.f + 1] = cur.s + 1;
                q.offer(new Edge(cur.f + 1, cur.s + 1));
            }

            if(cur.f <= 100_000 && dist[cur.f * 2] >= cur.s + 1) {
                dist[cur.f * 2] = cur.s + 1;
                q.offer(new Edge(cur.f * 2, cur.s + 1));
            }
        }
        return cnt;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int cnt = solution(n, k);
        System.out.println(dist[k]);
        System.out.println(cnt);
        br.close();
    }
}