import java.io.*;
import java.util.*;

public class Main {
    static int n, m, t, s, g, h, gh;
    static List<int[]>[] graph;
    static int MAX = 2_000_001;
    static int getLocalMinimum(int[] dist, boolean[] visited, int start, int end) {
        Arrays.fill(dist, MAX);
        Arrays.fill(visited, false);
        return dijkstra(dist, visited, start, end);
    }
    static int dijkstra(int[] dist, boolean[] visited, int start, int end) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        dist[start] = 0;
        pq.offer(new int[] {start, dist[start]});
        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            int x = cur[0]; int d = cur[1];
            if(x == end) break;
            if(visited[x]) continue;
            visited[x] = false;
            for(int[] edge : graph[x]) {
                if(!visited[edge[0]] && dist[edge[0]] > dist[x] + edge[1]) {
                    dist[edge[0]] = dist[x] + edge[1];
                    pq.offer(new int[] {edge[0], dist[edge[0]]});
                }
            }
        }
        return dist[end];
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while(T-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            t = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine(), " ");
            s = Integer.parseInt(st.nextToken());
            g = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            graph = new List[n + 1];
            for(int i = 1; i <= n; i++) {
                graph[i] = new ArrayList<>();
            }
            gh = MAX;
            for(int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                graph[a].add(new int[] {b, d});
                graph[b].add(new int[] {a, d});
                if((a == g && b == h) || (a == h && b == g)) gh = Math.min(gh, d);
            }
            int[] dist = new int[n + 1];
            boolean[] visited = new boolean[n + 1];
            List<Integer> list = new ArrayList<>();
            for(int i = 0; i < t; i++) {
                int x = Integer.parseInt(br.readLine());
                int d1 = getLocalMinimum(dist, visited, s, x);
                int d2 = getLocalMinimum(dist, visited, s, g) + gh + getLocalMinimum(dist, visited, h, x);
                if(d1 == d2) {
                    list.add(x);
                    continue;
                }
                int d3 = getLocalMinimum(dist, visited, s, h) + gh + getLocalMinimum(dist, visited, g, x);
                if(d1 == d3) {
                    list.add(x);
                    continue;
                }
            }
            Collections.sort(list);
            for(int val : list) {
                sb.append(val).append(' ');
            }
            sb.append('\n');
        }
        System.out.print(sb);
        br.close();
    }
}